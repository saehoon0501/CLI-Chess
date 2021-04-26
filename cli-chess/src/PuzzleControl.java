import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

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
        try{
            if(input.equalsIgnoreCase("White") || input.equalsIgnoreCase("Wt") || input.equals("백")){
                this.white = true;
            }else if (input.equalsIgnoreCase("Black") || input.equalsIgnoreCase("Bk") || input.equals("흑")) {
                this.white = false;
            }else if (input.equalsIgnoreCase("Help") || input.equalsIgnoreCase("H") || input.equalsIgnoreCase("도움")) {
                System.out.println("기물 선택 형식 : <비개행공백열0><기물지정자>");
                System.out.println("기물 배치 형식 : <비개행공백열0><좌표입력>");
                System.out.println("이니셜 R    P    B      Q     K    N");
                System.out.println("풀네임 Rook Pawn Bishop Queen King Knignt");
                System.out.println("한글   룩   폰    비숍    퀸     킹   나이트\n");
            }else if (input.equalsIgnoreCase("Back") || input.equalsIgnoreCase("Bc") || input.equalsIgnoreCase("뒤로")) {
                this.start();
            }else if (input.equalsIgnoreCase("Exit") || input.equalsIgnoreCase("Ex") || input.equalsIgnoreCase("종료")) {
                        //체스 메인 메뉴로 가는 코드
            }else if (input.equalsIgnoreCase("Next") || input.equalsIgnoreCase("Nx") || input.equalsIgnoreCase("다음")) {
                if(this.next()){
                    this.puzzleFile.getPuzzleBoard().makePieceList();
                    this.control = false;
                }else {
                    System.out.println("최소 승리 조건에 불충분");
                }
            }else if (input.substring(0,2).equalsIgnoreCase("삭제")){
                StringBuilder s = new StringBuilder(input);
                s.delete(0,2);
                input = s.toString().trim();
                this.delete(this.puzzleFile.getPuzzleBoard(), input.split(" "));
            } else if(input.length() > 4){
                if(input.substring(0,5).equalsIgnoreCase("Place")){//inputarray[0] = 기물, inputarray[1]= 위치로 가공
                    StringBuilder s = new StringBuilder(input);
                    s.delete(0,5);
                    s.trimToSize();
                    this.place(this.puzzleFile.getPuzzleBoard(), s.toString().trim().split(" "), this.white);
                }else if(input.substring(0,2).equalsIgnoreCase("Pl") ||input.substring(0,2).equalsIgnoreCase("배치")){
                    StringBuilder s = new StringBuilder(input);
                    s.delete(0,2);
                    System.out.println(s);
                    s.trimToSize();
                    this.place(this.puzzleFile.getPuzzleBoard(), s.toString().trim().split(" "), this.white);
                }else if (input.substring(0,6).equalsIgnoreCase("Delete")) {
                    StringBuilder s = new StringBuilder(input);
                    s.delete(0,6);
                    input = s.toString();
                    this.delete(this.puzzleFile.getPuzzleBoard(), input.trim().split(" "));
                }else if (input.substring(0,3).equalsIgnoreCase("Del")) {
                    StringBuilder s = new StringBuilder(input);
                    s.delete(0,3);
                    input = s.toString();
                    this.delete(this.puzzleFile.getPuzzleBoard(), input.trim().split(" "));
                }
            }else {
                System.out.println("존재하지 않는 명령어 입니다.");
                System.out.println("             명령어 표    ");
                System.out.println("        대소문자 구별 없음 ");
                System.out.println("Black White Delete Place Next Help");
                System.out.println(" Bk    Wt     Del   Pl    Nx    H");
                System.out.println(" 흑     백     삭제   배치   다음   도움\n");
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("존재하지 않는 명령어 입니다.");
            System.out.println("             명령어 표    ");
            System.out.println("        대소문자 구별 없음 ");
            System.out.println("Black White Delete Place Next Help");
            System.out.println(" Bk    Wt     Del   Pl    Nx    H");
            System.out.println(" 흑     백     삭제   배치   다음   도움\n");
        }

    }

    public void place(Board gameBoard, String inputarray[], boolean white){ //기물 배치 커맨드
        try{
            String piece = inputarray[0];

            if(inputarray[1].equals(" ") || inputarray[0].equals(" ")){
                System.out.println("기물 선택 형식 : <비개행공백열0><기물지정자>");
                System.out.println("기물 배치 형식 : <비개행공백열0><좌표입력>");
                System.out.println("이니셜 R    P    B      Q     K    N");
                System.out.println("풀네임 Rook Pawn Bishop Queen King Knignt");
                System.out.println("한글   룩   폰    비숍    퀸     킹   나이트\n");
            }
            if (inputarray[1].length() != 2 || (inputarray[1].substring(0,1).compareToIgnoreCase("h") > 0) || !Character.isLetter(inputarray[1].charAt(0))){
                System.out.println("기물 선택 형식 : <비개행공백열0><기물지정자>");
                System.out.println("기물 배치 형식 : <비개행공백열0><좌표입력>");
                System.out.println("이니셜 R    P    B      Q     K    N");
                System.out.println("풀네임 Rook Pawn Bishop Queen King Knignt");
                System.out.println("한글   룩   폰    비숍    퀸     킹   나이트\n");
            }else if((!Character.isDigit(inputarray[1].charAt(1))) ||
                    (Integer.parseInt(inputarray[1].substring(1,2)) > 8) ||
                    (Integer.parseInt(inputarray[1].substring(1,2)) < 1)){
                System.out.println("기물 선택 형식 : <비개행공백열0><기물지정자>");
                System.out.println("기물 배치 형식 : <비개행공백열0><좌표입력>");
                System.out.println("이니셜 R    P    B      Q     K    N");
                System.out.println("풀네임 Rook Pawn Bishop Queen King Knignt");
                System.out.println("한글   룩   폰    비숍    퀸     킹   나이트\n");
            } else{
                int j = Controller.fileToInd(inputarray[1]);
                int i = Controller.rankToInd(inputarray[1]);
                if (white){
                    Gamepiece temp = new Gamepiece();
                    temp.setPiece(piece);
                    if (temp.setPiece(piece)){
                        temp.setPlayer("w");
                        String pos = Controller.indToPos(i, j);
                        temp.setPosition(pos);
                        gameBoard.GBoard[i][j] = temp;
                        gameBoard.GBoard[i][j].createMoveSet(this.puzzleFile.getPuzzleBoard());
                    }else {
                        System.out.println("기물 선택 형식 : <비개행공백열0><기물지정자>");
                        System.out.println("기물 배치 형식 : <비개행공백열0><좌표입력>");
                        System.out.println("이니셜 R    P    B      Q     K    N");
                        System.out.println("풀네임 Rook Pawn Bishop Queen King Knignt");
                        System.out.println("한글   룩   폰    비숍    퀸     킹   나이트\n");
                    }
                }else{
                    Gamepiece temp = new Gamepiece();
                    temp.setPiece(piece);
                    if (temp.setPiece(piece)){
                        temp.setPlayer("b");
                        String pos = Controller.indToPos(i, j);
                        temp.setPosition(pos);
                        gameBoard.GBoard[i][j] = temp;
                        gameBoard.GBoard[i][j].createMoveSet(this.puzzleFile.getPuzzleBoard());
                    }else {
                        System.out.println("<비개행공백열0><기물지정자>");
                        System.out.println("이니셜 R    P    B      Q     K    N");
                        System.out.println("풀네임 Rook Pawn Bishop Queen King Knignt");
                        System.out.println("한글   룩   폰    비숍    퀸     킹   나이트");
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("존재하지 않는 명령어 입니다.");
        }


    }

    public void delete(Board gameBoard, String inputarray[]){ // 기물 삭제 커맨드
        if (inputarray[0].length() != 2 || (inputarray[0].substring(0,1).compareToIgnoreCase("h") > 0) || !Character.isLetter(inputarray[0].charAt(0))){
            System.out.println("유효하지 않은 좌표입니다");
        }else if((!Character.isDigit(inputarray[0].charAt(1))) ||
                (Integer.parseInt(inputarray[0].substring(1,2)) > 8) ||
                (Integer.parseInt(inputarray[0].substring(1,2)) < 1)){
            System.out.println("유효하지 않은 좌표입니다");
        }else {
            int i = Controller.rankToInd(inputarray[0]);
            int j = Controller.fileToInd(inputarray[0]);
            gameBoard.GBoard[i][j] = null;
        }
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
            if (piece.equalsIgnoreCase("♚")) {
                wKingCount = wKingCount + 1;
            } else if (piece.equalsIgnoreCase("♝")) {
                wBishopcount = wBishopcount + 1;
            } else if (piece.equalsIgnoreCase("♞")) {
                wKnightcount = wKnightcount + 1;
            } else {
                wElse = wElse + 1;
            }
        }

        for (String piece2 : this.puzzleFile.getPuzzleBoard().blackPieceList){
            if (piece2.equalsIgnoreCase("♚")){
                bKingCount = bKingCount + 1;
            }else if (piece2.equalsIgnoreCase("♝")){
                bBishopcount = bBishopcount + 1;
            }else if (piece2.equalsIgnoreCase("♞")){
                bKnightcount = bKnightcount + 1;
            }else {
                bElse = bElse + 1;
            }
        }

        if (wKingCount != 1 || bKingCount != 1){ //킹이 양쪽에 하나 존재 하지 않으면 모두 false
            return false;
        }else if (wElse == 0 && bElse == 0){ // 킹,나이트,비숍 제외 다른 기물이 없는 경우
            if (wBishopcount > 1 || bBishopcount > 1){ // 한쪽에 서로 칸 색이 다른 비숍 2개이면 ok
                if (diffcolor()){
                    return true;
                }
                return false;
            }else if((wBishopcount > 0 && wKnightcount >0) || (bBishopcount > 0 && bKnightcount > 0)){
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean diffcolor(){
        Gamepiece gamepiece1 = new Gamepiece();
        Gamepiece gamepiece2 = new Gamepiece();
        Gamepiece gamepiece3 = new Gamepiece();
        Gamepiece gamepiece4 = new Gamepiece();
        for(int i = 0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (this.puzzleFile.getPuzzleBoard().GBoard[i][j].getPiece().equals("♝︎") && this.puzzleFile.getPuzzleBoard().GBoard[i][j].getPlayer().equals("w︎")) {
                    if (i%2 == 0 && j%2 ==0){
                        gamepiece1 = this.puzzleFile.getPuzzleBoard().GBoard[i][j];
                    }else if(i%2 == 1 && j%2== 1){
                        gamepiece2 = this.puzzleFile.getPuzzleBoard().GBoard[i][j];
                    }
                }else if(this.puzzleFile.getPuzzleBoard().GBoard[i][j].getPiece().equals("♝︎") && this.puzzleFile.getPuzzleBoard().GBoard[i][j].getPlayer().equals("b")) {
                    if (i%2 == 0 && j%2 ==0){
                        gamepiece3 = this.puzzleFile.getPuzzleBoard().GBoard[i][j];
                    }else if(i%2 == 1 && j%2== 1){
                        gamepiece4 = this.puzzleFile.getPuzzleBoard().GBoard[i][j];
                    }

                }
            }
        }
        if (gamepiece1.getPiece() == gamepiece2.getPiece() || gamepiece3.getPiece() == gamepiece4.getPiece()){
            return true;
        }else {
            return false;
        }
    }

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
        this.control = true;
        Board reset = new Board();
        reset = reset.copyBoard(this.puzzleFile.getPuzzleBoard());
        String data ="";
        Board result = new Board();
        result = result.copyBoard(this.puzzleFile.getPuzzleBoard());
        Stack<Board> temp = new Stack<Board>();
        Stack<String> lastData = new Stack<String>();

        while (this.control){
            if(this.puzzleFile.getTheme() - count != 0 ) {
                System.out.println("퍼즐 정답 움직임을 설정해 주세요");
                if (white) {
                    int time = (this.puzzleFile.getTheme() - count);
                    System.out.println("\t\t\t\t 백 차례" + "남은 턴 횟수 :" + time);
                } else {
                    int time = (this.puzzleFile.getTheme() - count);
                    System.out.println("\t\t\t\t 흑 차례" + "남은 턴 횟수 :" + time);
                }
            }else{
                System.out.println("\t\t\t\t 턴 횟수 종료");
                System.out.println("Next 입력해 다음 단계 진행 해주세요.");
            }
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            input = input.trim();
            String moveToken[] = input.split(" ");
            try {
                if (input.equalsIgnoreCase("Reset") || input.equalsIgnoreCase("Re") || input.equals("초기화")) {
                    System.out.println("초기화 실행");
                    count = 0;
                    data ="";
                    white = false;
                    result = result.copyBoard(reset);
                    result.printBoard();
                }else if (input.equalsIgnoreCase("Last") || input.equalsIgnoreCase("L") || input.equals("다시")){
                    count = count - 1;
                    if (count < 0 ){
                        System.out.println("움직임을 먼저 설정해주세요.");
                        count = count + 1;
                    }else {
                        result = result.copyBoard(temp.pop());
                        result.printBoard();
                        data = lastData.pop();
                        white = !white;
                    }
                }else if (input.equalsIgnoreCase("Next") || input.equalsIgnoreCase("Nx") || input.equals("다음")){
                        if (result.isCheckmate(this.kingPos(result), false) && this.puzzleFile.getTheme() - count == 0){
                            this.control = false;
                        }else {
                            System.out.println(" 정답 움직임이 완료되지 않았습니다. ");
                            count = 0; // reset함.
                            data ="";
                            white = false;
                            result = result.copyBoard(reset);
                            result.printBoard();
                        }
                }else if(input.equalsIgnoreCase("Help") || input.equalsIgnoreCase("H") || input.equals("도움")){
                    System.out.println("           좌표 입력 규칙표               ");
                    System.out.println("<알파벳A~H><자연수1~8>" + "또는" + "<자연수1~8><알파벳A~H>");
                }else if(this.puzzleFile.getTheme()*2 - count != 0){
                    boolean canMove = Controller.isValidMove(moveToken[0], moveToken[1], result, white);
                    if(canMove == false){
                        continue;
                    }
                    Board last = new Board();
                    last = last.copyBoard(result);
                    lastData.push(data);
                    temp.push(last); // 이전값 저장
                    result = (Controller.processMove(moveToken[0], moveToken[1], result, white));
                    if (white) {
                        count++;
                    }
                    data = data + input +",";
                    this.puzzleFile.setPlaydata(data);
                    result.printBoard();
                    white = !white;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("존재하지 않는 명령어 입니다.");
            }
        }
        System.out.println(data);
        this.puzzleFile.setPuzzleBoard(result);
        this.puzzleFile.setPlaydata(data);
    }

    public Gamepiece kingPos(Board check){
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(check.GBoard[i][j] != null) {
                    if (check.GBoard[i][j].getPlayer().equals("b")) {
                        if (check.GBoard[i][j].getPiece().equals("♚")) {
                            return check.GBoard[i][j];
                        }
                    }
                }
            }
        }
        return null;
    }

    //4번째 파일 이름 선택 후 data output in String format using java.io
    public void setName(){
        this.control = true;
        while(this.control) {
            System.out.println("퍼즐 이름을 입력하세요");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            input = input.trim();
            String[] inputToken = input.split(" ");
            if (input == "") {
                System.out.println("길이가 1이상");
                System.out.println("첫 문자와 마지막 문자는 실상 문자");
                System.out.println("어떠한 개행도 전혀 들어가 있지 않음");
                continue;
            }

            for (String s : inputToken){
                if(s.equals(" ")){
                    System.out.println("길이가 1이상");
                    System.out.println("첫 문자와 마지막 문자는 실상 문자");
                    System.out.println("어떠한 개행도 전혀 들어가 있지 않음");
                    continue;
                }
            }

            this.puzzleFile.setName(input);
        }
    }

    //Puzzle Play 기능

}
