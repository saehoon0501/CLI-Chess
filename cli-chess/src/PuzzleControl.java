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
        System.out.println("\t1. MateIn1");
        System.out.println("\t2. MateIn2");
        System.out.println("\t3. MateIn3");


        System.out.println("1~3 중 선택 : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.trim();
        
        if(input.equals("1") || input.equals("1.")) {
            this.puzzleFile.setTheme(1); //1단계
            this.createBoard();//2단계
            this.setPlay();
            this.puzzleFile.setName("0");//4단계
        }else if(input.equals("2") || input.equals("2.")) {
            this.puzzleFile.setTheme(2);
            this.createBoard();
            this.setPlay();
            this.puzzleFile.setName("0");
        }else if (input.equals("3") || input.equals("3.")) {
            this.puzzleFile.setTheme(3);
            this.createBoard();
            this.setPlay();
            this.puzzleFile.setName("0");
        }else if(input.equals("")){
            System.out.println("숫자 한자리만 입력하세요!");
            this.start();
        }else{
            System.out.println("1 2 3 중 하나만 입력하세요.");
            this.start();
        }
        //만들어진 puzzleFile output 실행
        }

    //2번째 퍼즐 보드 기물 선택 및 배치
    public void createBoard() throws IOException {
        Runtime.getRuntime().exec("clear");
        for(int i = 0;i < 8;i++){
            for(int j = 0; j < 8; j++){
                this.puzzleFile.getPuzzleBoard().GBoard[i][j] = null;
            }
        }
            while(this.control) {
                this.puzzleFile.getPuzzleBoard().printBoard();
                if (white){
                System.out.println("기물을 배치하세요! \t\t White side");
            }else{
                System.out.println("기물을 배치하세요! \t\t Black side");
            }
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                input = input.trim();
                this.command1(input);
            }
    }

    //기물 선택 배치 모드 각 command 실행 Place, White, Black, Delete, Back, Next, Help, Exit
    public void command1(String input) throws IOException {
        if(input.length() > 4){
            if(input.substring(0,5).equalsIgnoreCase("Place")){//inputarray[0] = 기물, inputarray[1]= 위치로 가공
                StringBuilder s = new StringBuilder(input);
                s.delete(0,5);
                s.trimToSize();
                this.place(this.puzzleFile.getPuzzleBoard(), s.toString().trim().split(" "), this.white);
            }else if (input.equalsIgnoreCase("Delete") || input.equalsIgnoreCase("Del") || input.equalsIgnoreCase("삭제")) {
                System.out.println(input);
                this.delete(this.puzzleFile.getPuzzleBoard(), input.split(" "));
            }
        }
        if(input.substring(0,2).equalsIgnoreCase("Pl")){
            StringBuilder s = new StringBuilder(input);
            s.delete(0,2);
            System.out.println(s);
            s.trimToSize();
            this.place(this.puzzleFile.getPuzzleBoard(), s.toString().trim().split(" "), this.white);
        }
        if(input.equalsIgnoreCase("White") || input.equalsIgnoreCase("Wt") || input.equals("백")){
            this.white = true;
        }else if (input.equalsIgnoreCase("Black") || input.equalsIgnoreCase("Bk") || input.equals("흑")) {
            this.white = false;
        }else if (input.equalsIgnoreCase("Help") || input.equalsIgnoreCase("H") || input.equalsIgnoreCase("도움")) {

        }else if (input.equalsIgnoreCase("Back") || input.equalsIgnoreCase("Bc") || input.equalsIgnoreCase("뒤로")) {
              this.start();
        }else if (input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("Ex") || input.equalsIgnoreCase("종료")) {

        }else if (input.equalsIgnoreCase("Next") || input.equalsIgnoreCase("Nx") || input.equalsIgnoreCase("다음")) {
            if(this.next()){
                    this.puzzleFile.getPuzzleBoard().makePieceList();
                this.control = false;
                }else {
                    System.out.println("최소 승리 조건에 불충분");
                this.createBoard();
                }
        }else if (input.substring(0,3).equalsIgnoreCase("Del") || input.equalsIgnoreCase("삭제")) {
            StringBuilder s = new StringBuilder(input);
            s.delete(0,3);
            input = s.toString().trim();
            this.delete(this.puzzleFile.getPuzzleBoard(), input.split(" "));
        }else{
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
        }
    }

    public void place(Board gameBoard, String inputarray[], boolean white){ //기물 배치 커맨드
        System.out.println("배치 실행됨");
        String piece = inputarray[0];
        int j = Controller.fileToInd(inputarray[1]);
        int i = Controller.rankToInd(inputarray[1]);
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
        int i = Controller.rankToInd(inputarray[0]);
        int j = Controller.fileToInd(inputarray[0]);

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
                bKingCount = bKingCount + 1;
            }else if (piece2.equalsIgnoreCase("B")){
                bBishopcount = bBishopcount + 1;
            }else if (piece2.equalsIgnoreCase("N")){
                bKnightcount = bKnightcount + 1;
            }else {
                bElse = bElse + 1;
            }
        }

        if (wKingCount != 1 || bKingCount != 1){ //킹이 양쪽에 하나 존재 하지 않으면 모두 false
            System.out.println(wKingCount);
            System.out.println(bKingCount);
            return false;}
//        }else if (wElse == 0 && bElse == 0){ // 킹,나이트,비숍 제외 다른 기물이 없는 경우
//            if (wBishopcount > 1 || bBishopcount > 1){ // 한쪽에 서로 칸 색이 다른 비숍 2개이면 ok
////                if (diffcolor()){
////                    return true;
////                }
//                return false;
//            }
//            return false;
//        }
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
    public void setPlay(){
        int count = 0;
        String data = this.puzzleFile.getPlaydata();
        while (this.puzzleFile.getTheme() - count != 0)
        {
            int time = this.puzzleFile.getTheme() - count;
            System.out.println("퍼즐 정답 움직임을 설정해 주세요");
            if(white){
                System.out.println("\t\t\t\t\t 백 차례" + "남은 턴 횟수 :" + time);
            }else{
                System.out.println("\t\t\t\t\t 흑 차례");
            }
            Scanner scanner = new Scanner(System.in);
            String move = scanner.nextLine();
            data = data +"," + move;
            String moveToken[] = move.split(" ");
            Board temp = this.puzzleFile.getPuzzleBoard();
            boolean canMove = Controller.isValidMove(moveToken[0], moveToken[1], temp, white);
                if(canMove == false){
                    continue;
                }
            temp = Controller.processMove(moveToken[0], moveToken[1], temp, white);
            temp.printBoard();
            white = !white;
            count++;
            continue;
        }
        this.puzzleFile.setPlaydata(data);
    }
    //4번째 파일 이름 선택 후 data output in String format using java.io


    //Puzzle Play 기능

}
