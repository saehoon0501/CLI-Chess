package chess;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class DDual {
    //임의의 사용자 아이디 값
    private String userID;
    private int turn =0;
    private int myColor=0;
    private File file;

    DDual(String i_id, String i_FileName) {
        file=new File(i_FileName);
        userID=i_id;
        turn=1;
        myColor=getMyturn(file);
    }
    public void start() throws IOException
    {
        String pos = "";
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            outputFile(file, pos);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedReader bufferedReader;
        String name1="";
        String name2 = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            name1=bufferedReader.readLine();
            name2 = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while(name2.equals("_"))
        {
            System.out.println("상대방을 기다리는중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Menu.clearScreen();
            bufferedReader = new BufferedReader(new FileReader(file));
            name1=bufferedReader.readLine();
            name2 = bufferedReader.readLine();
        }
        Board Chessboard = Controller.createBoard(); //Make the board
        System.out.println("");
        System.out.println(" "+name1+" vs "+name2);
        if(turn%2==0)
            System.out.println("   "+(turn-1)+"TURN - WHITE now");
        else
            System.out.println("   "+(turn-1)+"TURN - BLACK now");
        Chessboard.printBoard(); //Print the board and some information about the game
        System.out.println("Welcome to Chess! Input -h for usage or a valid move to start");
        System.out.println("Players: \'w\' = white | \'b\' = black");
        System.out.println("Pieces: \'K\' = King | \'Q\' = Queen | \'R\' = Rook | \'B\' = Bishop | \'N\' = Knight | \'P\' = Pawn");
        System.out.println("");
        boolean whiteTurn = true; //Turn boolean

        //User Interface
        boolean gameExit=true;
        while(gameExit){

            Gamepiece king = new Gamepiece();

            //Find the king and determine if checkmate, stalemate or check conditions exist
            //턴 읽어오기
            if((turn-myColor)%2==0){//자기턴일때

                String s="";
                try {
                    s = inputFile(file);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String[] foo = s.split(",");
                String[] sToken = foo[0].split(" ");
                if(turn!=1&& !sToken[0].equals(""))
                {
                    //////////////////////////////////
                    int r = Controller.rankToInd(sToken[0]);
                    int c = Controller.fileToInd(sToken[0]);
                    Chessboard = Controller.processMove(sToken[0], sToken[1], Chessboard, (turn%2==0));
                    System.out.println(" "+name1+" vs "+name2);
                    if(turn%2==0)
                        System.out.println("   "+(turn-1)+"TURN - WHITE now");
                    else
                        System.out.println("   "+(turn-1)+"TURN - BLACK now");
                    Chessboard.printBoard();
                    //////////////////////////////////
                }
                //바꿈 white turn
                if(turn%2==1){
                    for(int i = 0;i<8;i++){
                        for(int j = 0;j<8;j++){
                            if(Chessboard.GBoard[i][j] == null){
                                continue;
                            }
                            if(Chessboard.GBoard[i][j].getPlayer().equalsIgnoreCase("w") &&
                                    Chessboard.GBoard[i][j].getPiece().equalsIgnoreCase("♚")){
                                king = Chessboard.GBoard[i][j];
                                break;
                            }
                        }
                    }
                    if(Chessboard.isCheckmate(king, (turn%2==1))){
                        System.out.println("CHECKMATE!");
                        System.out.println("축하합니다! 당신은 승리했습니다!(exit를 입력해 메뉴로 돌아가기)");
                        //System.out.println("Checkmate.");
                        // System.out.println("Black Wins");
                        //System.exit(0);
                    }
                    else if(Chessboard.isStalemate(king, (turn%2==1))){
//            System.out.println("Stalemate");
//            System.out.println("Draw");
                        System.out.println("게임이 무승부로 끝났습니다(exit를 입력해 메뉴로 돌아가기)");
//            System.exit(0);
                    }
                    else if(Chessboard.isCheck(king.getPosition(), (turn%2==1))){
                        System.out.println("CHECK!");
                        //System.out.println("White king at " + king.getPosition() + " is in CHECK!");
                    }
                    else{

                    }
                }
                else{
                    for(int i = 0;i<8;i++){
                        for(int j = 0;j<8;j++){
                            if(Chessboard.GBoard[i][j] == null){
                                continue;
                            }
                            if(Chessboard.GBoard[i][j].getPlayer().equalsIgnoreCase("b") &&
                                    Chessboard.GBoard[i][j].getPiece().equalsIgnoreCase("♚")){
                                king = Chessboard.GBoard[i][j];
                                break;
                            }
                        }
                    }

                    if(Chessboard.isCheckmate(king, (turn%2==1))){
                        System.out.println("CHECKMATE!");
                        System.out.println("안축하합니다! 당신은 패배했습니다!(exit를 입력해 메뉴로 돌아가기)");
                        //System.out.println("체크메이트");
                        //System.out.println("백 승리");
                        //System.exit(0);
                    }
                    else if(Chessboard.isStalemate(king, (turn%2==1))){
                        //System.out.println("스틸메이트");
                        //System.out.println("무승부");
                        System.out.println("게임이 무승부로 끝났습니다(exit를 입력해 메뉴로 돌아가기)");
                        //System.exit(0);
                    }
                    else if(Chessboard.isCheck(king.getPosition(), (turn%2==1))){
                        System.out.println("CHECK!");
                        //System.out.println("Black king at " + king.getPosition() + " is in CHECK!");
                    }
                    else{

                    }

                }
                //Turn prompt
                //바꿈 white turn
                if((turn%2==1)){
                    System.out.println("White's turn, please enter move: ");
//                Controller.getRecommendation(Chessboard,whiteTurn);
                }
                else{
                    System.out.println("Black's turn, please enter move: ");
                }

                //Take in user input

                Scanner input = new Scanner(System.in);
                String userInput = input.nextLine();
                //대전파일 업데이트
                userInput = userInput.trim();
                userInput = userInput + " ";
                String[] inputTokens = userInput.split(" ");

                //If the user only entered one token, it could be help or resign






                if(inputTokens.length == 1){
                    if((turn-myColor)%2==0)
                    {
                        if(inputTokens[0].equalsIgnoreCase("Exit") || inputTokens[0].equalsIgnoreCase("Ex")|| inputTokens[0].equals("종료")){
                            Menu denu = new Menu();
                            denu.mainMenu();
                        }
                        else if(inputTokens[0].equalsIgnoreCase("surrender") || inputTokens[0].equalsIgnoreCase("gg")
                                || inputTokens[0].equalsIgnoreCase("항복") || inputTokens[0].equalsIgnoreCase("지지")
                                || inputTokens[0].contains("ㅈㅈ")){
                            if((turn%2==1)){
                                System.out.println("백 기권");
                                System.out.println("흑 승리");
                                System.exit(0);
                            }
                            else{
                                System.out.println("흑 기권");
                                System.out.println("백 승리");
                                System.exit(0);
                            }
                        }
                        else {
                            System.out.println("자신의 턴이 아닙니다.");
                            continue;
                        }
                    }
                    System.out.println("inputtoken0: "+inputTokens[0]);
                    if(inputTokens[0].equalsIgnoreCase(" ")){
                        System.out.println("유효하지 않은 입력입니다.");
                    }
                    else if(inputTokens[0].equalsIgnoreCase("Exit") || inputTokens[0].equalsIgnoreCase("Ex")|| inputTokens[0].equals("종료"))
                    {
                        Menu denu = new Menu();
                        denu.mainMenu();
                    }
                    else if(inputTokens[0].equalsIgnoreCase("Exit")) {
                        Menu kenu=new Menu();
                        kenu.mainMenu();
                    }
                    else{
                        System.out.println("유효하지 않은 입력입니다.");
                        continue;
                    }
                }
                else if(inputTokens.length == 2){
                    //If the user entered 2 tokens, it must be a move entry. check if valid then if valid, perform move
                    if((turn-myColor)%2!=0){
                        System.out.println("자신의 턴이 아닙니다.");
                        continue;
                    }
                    boolean canMove = Controller.isValidMove(inputTokens[0], inputTokens[1], Chessboard, (turn%2==1));
                    if(canMove == false){
                        continue;
                    }
                    else
                    {
                        int r = Controller.rankToInd(inputTokens[0]);
                        int c = Controller.fileToInd(inputTokens[0]);
                        if(Controller.satisfiesPromo(Chessboard.GBoard[r][c],inputTokens[1]))
                            Controller.processPromo(Chessboard.GBoard[r][c], Chessboard);
                        Chessboard = Controller.processMove(inputTokens[0], inputTokens[1], Chessboard, (turn%2==1));
                        appendLine(file, inputTokens[0]+" "+inputTokens[1]);
                        if(draw(Chessboard)){
                            System.out.println("승리를 위한 최소조건 불충분");
                            //플레이 종료 후 파일 삭제
                        }
                        System.out.println(" "+name1+" vs "+name2);
                        if(turn%2==1)
                            System.out.println("   "+(turn)+"TURN - WHITE now");
                        else
                            System.out.println("   "+(turn)+"TURN - BLACK now");
                        Chessboard.printBoard();
                        whiteTurn = !whiteTurn;
                        continue;
                    }
                }
                else if(inputTokens.length == 3){ //If user entered 3 tokens, it could be draw proposal or promotion
                    // CASE: Promotion
                    System.out.println("프로모션할 기물을 선택해주세요.");
                    if(inputTokens[2].equalsIgnoreCase("R") || inputTokens[2].equalsIgnoreCase("Rook") || inputTokens[2].equalsIgnoreCase("룩") ||
                            inputTokens[2].equalsIgnoreCase("K") || inputTokens[2].equalsIgnoreCase("King") || inputTokens[2].equalsIgnoreCase("킹") ||
                            inputTokens[2].equalsIgnoreCase("N") || inputTokens[2].equalsIgnoreCase("kNight") || inputTokens[2].equalsIgnoreCase("나이트") ||
                            inputTokens[2].equalsIgnoreCase("B") || inputTokens[2].equalsIgnoreCase("Bishop") || inputTokens[2].equalsIgnoreCase("비숍") ||
                            inputTokens[2].equalsIgnoreCase("Q") || inputTokens[2].equalsIgnoreCase("Queen") || inputTokens[2].equalsIgnoreCase("퀸")){
                        boolean canMove = Controller.isValidMove(inputTokens[0], inputTokens[1], Chessboard, (turn%2==1));

                        int pawnI = Controller.rankToInd(inputTokens[0]);
                        int pawnJ = Controller.fileToInd(inputTokens[0]);

                        //Check if trying to promote to king
                        if(inputTokens[2].equalsIgnoreCase("K")|| inputTokens[2].equalsIgnoreCase("King") || inputTokens[2].equalsIgnoreCase("킹")){
                            System.out.println("해당 기물로는 프로모션을 할 수 없습니다.");
                            continue;
                        }

                        //If trying to promote a piece that is not pawn, reprompt
                        if(!Chessboard.GBoard[pawnI][pawnJ].getPiece().equalsIgnoreCase("♟")){
                            System.out.println("해당 기물로는 프로모션을 할 수 없습니다.");
                            continue;
                        }

                        //See if the move to the far rank is valid then perform promotion if valid
                        if(canMove == false){
                            System.out.println("유효하지 않은 입력입니다.");
                            continue;
                        }
                        else{
                            int r = Controller.rankToInd(inputTokens[0]);
                            int c = Controller.fileToInd(inputTokens[0]);
                            if(Controller.satisfiesPromo(Chessboard.GBoard[r][c],inputTokens[1]))
                                Controller.processPromo(Chessboard.GBoard[r][c], Chessboard);
                            Chessboard = Controller.processMove(inputTokens[0], inputTokens[1], Chessboard, (turn%2==1));
                            appendLine(file, inputTokens[0]+" "+inputTokens[1]);
                            Chessboard.printBoard();
                            whiteTurn = !whiteTurn;
                            continue;
                        }
                    }
                    else{
                        System.out.println("유효하지 않은 입력입니다.");
                        continue;
                    }
                }
                else{
                    System.out.println("유효하지 않은 입력입니다.");
                    continue;
                }
            }
            if((turn-myColor)%2!=0)   //내 턴이 아니라 상대 플레이어 대기!(위에 작성함)
            {
//                try {
//                  Thread.sleep(500);
//               }catch(InterruptedException e) {
//                  e.printStackTrace();
//               }
                String lastLine=inputFile(file);
                String[] sToken = lastLine.split(",");
                turn=Integer.parseInt(sToken[1]);
                //일정 주기로 파일 턴 수 받아오다가 턴수가 바뀌면
            }
        }
    }
    public void outputFile(File f, String move) throws IOException //죄표를 대전파일에 저장
    {
        Scanner sc = new Scanner(f);
        StringBuffer buffer = new StringBuffer();
        BufferedReader br;
        br = new BufferedReader(new FileReader(f));
        String line1="";
        String line2="";
        line1=br.readLine();
        line2=br.readLine();
        br.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
        bufferedWriter.write(line1); //한줄 입력
        bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
        bufferedWriter.write(line2); //한줄 입력
        bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
        bufferedWriter.write(move+","+turn);
        turn++;
        bufferedWriter.close();

        Scanner sc2 = new Scanner(f);
        StringBuffer buffer2 = new StringBuffer();
        while (sc2.hasNextLine())
        {
            buffer2.append(sc2.nextLine()+System.lineSeparator());
        }
    }
    public void appendLine(File f, String move) throws IOException
    {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f,true));
        int numLine = 0;
        BufferedReader in = new BufferedReader(new FileReader("./DualMode/"+f.getName()));
        String str;
        while ((str = in.readLine()) != null)
        {
            numLine++;
        }
        turn++;
        bufferedWriter.write("\n"+move+","+turn);
        bufferedWriter.close();
    }
    //fileBefore.equals(fileAfter) 수행 후, 둘이 다르다면 inputFile실행해서 가장 마지막 줄 가져옴.
    public String inputFile(File f) throws FileNotFoundException   //대전파일에서 읽어옴
    {
        String lastLine="";
        BufferedReader in = new BufferedReader(new FileReader("./DualMode/"+f.getName()));
        String str;
        try {
            while ((str = in.readLine()) != null)
            {
                lastLine = str;
            }
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lastLine;
    }

    int getMyturn(File f) {   //내가 흑인지 백인지를 리턴, 그리고 만약에 2p가 안정해져있으면 자기 id 넣음
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String line1="";
        String line2="";
        String line3="";
        try {
            line1=br.readLine();
            line2=br.readLine();
            line3=br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(line1.equals(userID)) {   //내가 백 플레이어!
            return 1;
        }else if(line2.equals(userID)) {   //내가 흑 플레이어!
            return 0;
        }else {   //내가 방금 게임에 새로 참가한 흑 플레이어!
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
                bufferedWriter.write(line1);
                bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
                bufferedWriter.write(userID); //한줄 입력
                bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
                bufferedWriter.write(line3); //한줄 입력
                bufferedWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 0;
        }

    }
    public boolean draw(Board chessBoard){ //퍼즐 성립 승리 조건 6가지 확인 후 진행 여부 판단.
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

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { // 백에 승리조건에 불충족할 수 있는 기물들 수 체크
                if (chessBoard.GBoard[i][j] != null) {
                    if (chessBoard.GBoard[i][j].getPiece().equalsIgnoreCase("♚")) {
                        wKingCount = wKingCount + 1;
                    } else if (chessBoard.GBoard[i][j].getPiece().equalsIgnoreCase("♝")) {
                        wBishopcount = wBishopcount + 1;
                    } else if (chessBoard.GBoard[i][j].getPiece().equalsIgnoreCase("♞")) {
                        wKnightcount = wKnightcount + 1;
                    } else {
                        wElse = wElse + 1;
                    }
                }
            }
        }

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { // 흑에 승리조건에 불충족할 수 있는 기물들 수 체크
                if (chessBoard.GBoard[i][j] != null) {
                    if (chessBoard.GBoard[i][j].getPiece().equalsIgnoreCase("♚")) {
                        bKingCount = bKingCount + 1;
                    } else if (chessBoard.GBoard[i][j].getPiece().equalsIgnoreCase("♝")) {
                        bBishopcount = bBishopcount + 1;
                    } else if (chessBoard.GBoard[i][j].getPiece().equalsIgnoreCase("♞")) {
                        bKnightcount = bKnightcount + 1;
                    } else {
                        bElse = bElse + 1;
                    }
                }
            }
        }

        if (wKingCount != 1 || bKingCount != 1){ //킹이 양쪽에 하나 존재 하지 않으면 모두 false
            return false;
        }else if (wElse == 0 && bElse == 0){ // 킹,나이트,비숍 제외 다른 기물이 없는 경우
            if (wBishopcount > 1 || bBishopcount > 1){ // 한쪽에 서로 칸 색이 다른 비숍 2개이면 ok
                if (diffcolor(chessBoard)){
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
    public boolean diffcolor(Board chessBoard){// 한쪽이라도 비숍이 다른 색 칸에 하나씩 있는지 확인
        Gamepiece gamepiece1 = new Gamepiece();
        Gamepiece gamepiece2 = new Gamepiece();
        Gamepiece gamepiece3 = new Gamepiece();
        Gamepiece gamepiece4 = new Gamepiece();
        for(int i = 0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard.GBoard[i][j].getPiece().equals("♝︎") && chessBoard.GBoard[i][j].getPlayer().equals("w︎")) {
                    if (i%2 == 0 && j%2 ==0){
                        gamepiece1 = chessBoard.GBoard[i][j];
                    }else if(i%2 == 1 && j%2== 1){
                        gamepiece2 = chessBoard.GBoard[i][j];
                    }
                }else if(chessBoard.GBoard[i][j].getPiece().equals("♝︎") && chessBoard.GBoard[i][j].getPlayer().equals("b")) {
                    if (i%2 == 0 && j%2 ==0){
                        gamepiece3 = chessBoard.GBoard[i][j];
                    }else if(i%2 == 1 && j%2== 1){
                        gamepiece4 = chessBoard.GBoard[i][j];
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
}