import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class PuzzleControl {
    private Puzzle puzzleFile;
    private boolean control;
    private boolean white;

    public PuzzleControl() {
        this.puzzleFile = new Puzzle();
        this.control = true;
        this.white = true;
    }
        //1번째 단계 승리조건(테마) 선택
    public void start() throws IOException {
        System.out.println("Welcome to Puzzle Maker\n");
        System.out.println("Choose Theme :");
        System.out.println("\tMateIn1");
        System.out.println("\tMateIn2");
        System.out.println("\tMateIn3");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input){
            case "1":
                this.puzzleFile.setTheme(1); //1단계
                this.createBoard();//2단계
                this.setPlay();//3단계
                this.puzzleFile.setName("0");//4단계
                break; // 만들어진 puzzleFile output
            case "2":
                this.puzzleFile.setTheme(2);
                this.createBoard();
                this.setPlay();
                this.puzzleFile.setName("0");
                break;
            case "3":
                this.puzzleFile.setTheme(3);
                this.createBoard();
                this.setPlay();
                this.puzzleFile.setName("0");
                break;
        }
        }

    //2번째 퍼즐 보드 기물 선택 및 배치
    public Board createBoard() throws IOException {
        Runtime.getRuntime().exec("clear");
        Board gameBoard = this.puzzleFile.getPuzzleBoard();
        for(int i = 0;i < 8;i++){
            for(int j = 0; j < 8; j++){
                gameBoard.GBoard[i][j] = null;
            }
        }
        while(this.control){
            gameBoard.printBoard();
            if (white){
                System.out.println("기물을 배치하세요! \t\t White 기물 배치");
            }else{
                System.out.println("기물을 배치하세요! \t\t Black 기물 배치");
            }
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String inputarray[] = input.split(" "); //inputarray[0] = 커맨드, inputarray[1] = 기물, inputarray[2],[3] = 위치
            this.command1(inputarray);
        }
        return gameBoard;
    }

    //기물 선택 배치 모드 각 command 실행 Place, White, Black, Delete, Back, Next, Help, Exit
    public void command1(String input[]) throws IOException {
        switch (input[0]){
            case"Place":
                this.place(this.puzzleFile.getPuzzleBoard(), input, this.white);
                break;
            case"White":
                this.white = true;
                break;
            case"Black":
                this.white = false;
                break;
            case"Delete":
                this.delete(this.puzzleFile.getPuzzleBoard(), input);
                break;
            case"Back":
                this.start();
                break;
            case"Next":
                if(this.next()){
                    this.setPlay();
                }else {
                    System.out.println("최소 승리 조건에 불충분");
                    this.createBoard();
                }
            case"Help":

            case"Exit":

        }
    }

    public void place(Board gameBoard, String inputarray[], boolean white){ //기물 배치 커맨드
        String piece = inputarray[1];
        int j = Integer.parseInt(inputarray[2]);
        int i = Integer.parseInt(inputarray[3]);
        if (white){
            Gamepiece temp = new Gamepiece();
            temp.setPiece(piece);
            temp.setPlayer("w");
            String pos = Controller.indToPos(i, j);
            temp.setPosition(pos);
            gameBoard.GBoard[i][j] = temp;
        }else{
            Gamepiece temp = new Gamepiece();
            temp.setPiece(piece);
            temp.setPlayer("b");
            String pos = Controller.indToPos(i, j);
            temp.setPosition(pos);
            gameBoard.GBoard[i][j] = temp;
        }
    }

    public void delete(Board gameBoard, String inputarray[]){ // 기물 삭제 커맨드
        int i = Integer.parseInt(inputarray[1]);
        int j = Integer.parseInt(inputarray[2]);

        gameBoard.GBoard[i][j] = null;
    }

    public boolean next(){ //퍼즐 성립 최소 조건 6가지 확인 후 진행 여부 판단.
        int wKingCount = 0;
        int wKnightcount = 0;
        int wBishopcount = 0;
        int wRookcount = 0;
        int wElse = 0;
        int bKingCount = 0;
        int bKnightcount = 0;
        int bBishopcount = 0;
        int bRookcount = 0;
        int bElse = 0;
        this.makePieceList();
        for(String piece : this.puzzleFile.getPuzzleBoard().whitePieceList) { // 백에 킹의 수가 2개 혹은 0보다 작으면 not valid
            if (piece.equalsIgnoreCase("K")) {
                wKingCount = wKingCount + 1;
            } else if (piece.equalsIgnoreCase("B")) {
                wBishopcount = wBishopcount + 1;
            } else if (piece.equalsIgnoreCase("N")) {
                wKnightcount = wKnightcount + 1;
            } else {
                wElse = wElse + 1;
            }
        }
        for (String piece2 : this.puzzleFile.getPuzzleBoard().blackPieceList){
            if (piece2.equalsIgnoreCase("K")){
                wKingCount = wKingCount + 1;
            }else if (piece2.equalsIgnoreCase("B")){
                wBishopcount = wBishopcount + 1;
            }else if (piece2.equalsIgnoreCase("N")){
                wKnightcount = wKnightcount + 1;
            }else {
                bElse = bElse + 1;
            }
        }

        if (wKingCount != 1 || bKingCount != 1){ //킹이 하나 존재 하지 않으면 모두 false
            return false;
        }
        if (wElse == 0 && bElse == 0){ // 킹,나이트,비숍 제외 다른 기물이 없는 경우
            if (wBishopcount > 1 || bBishopcount > 1){ // 한쪽에 서로 칸 색이 다른 비숍 2개이면 ok
//                if (diffcolor()){
//                    return true;
//                }
                return false;
            }
            return false;
        }
        return true;
    }

//    public boolean diffcolor(){
//        Gamepiece piece1 = new Gamepiece();
//        Gamepiece piece2 = new Gamepiece();
//        for(String piece : this.puzzleFile.getPuzzleBoard().whitePieceList){
//
//        }
//    }

    public void makePieceList(){
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                if(this.puzzleFile.getPuzzleBoard().GBoard[i][j] == null){
                    continue;
                }
                else{
                    if(this.puzzleFile.getPuzzleBoard().GBoard[i][j].getPlayer().equalsIgnoreCase("w")){
                        this.puzzleFile.getPuzzleBoard().whitePieceList.add(this.puzzleFile.getPuzzleBoard().GBoard[i][j].getPiece());
                    }
                    else{
                        this.puzzleFile.getPuzzleBoard().blackPieceList.add(this.puzzleFile.getPuzzleBoard().GBoard[i][j].getPiece());
                    }
                }
            }
        }
    }

    //3번째 움직임 설정
    public String setPlay(){

        return "2";
    }
    //4번째 파일 이름 선택 후 data output in String format using java.io

}
