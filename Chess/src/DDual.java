import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    public void start() {
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

        Board Chessboard = Controller.createBoard(); //Make the board
        System.out.println("");
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
                String[] sToken = s.split(",");
                if(turn!=1) {
                	//////////////////////////////////
                	int r = Controller.rankToInd(sToken[0]);
                	int c = Controller.fileToInd(sToken[0]);
                	if(Controller.satisfiesPromo(Chessboard.GBoard[r][c],sToken[1]))
                		Controller.processPromo(Chessboard.GBoard[r][c], Chessboard);
	            	Chessboard = Controller.processMove(sToken[0], sToken[1], Chessboard, whiteTurn);
	            	Chessboard.printBoard();
                	//////////////////////////////////
                }
            if(whiteTurn){
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

                if(Chessboard.isCheckmate(king, whiteTurn)){
                    System.out.println("Checkmate.");
                    System.out.println("Black Wins");
                    System.exit(0);
                }
                else if(Chessboard.isStalemate(king, whiteTurn)){
                    System.out.println("Stalemate");
                    System.out.println("Draw");
                    System.exit(0);
                }
                else if(Chessboard.isCheck(king.getPosition(), whiteTurn)){
                    System.out.println("White king at " + king.getPosition() + " is in CHECK!");
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

                if(Chessboard.isCheckmate(king, whiteTurn)){
                    System.out.println("체크메이트");
                    System.out.println("백 승리");
                    System.exit(0);
                }
                else if(Chessboard.isStalemate(king, whiteTurn)){
                    System.out.println("스틸메이트");
                    System.out.println("무승부");
                    System.exit(0);
                }
                else if(Chessboard.isCheck(king.getPosition(), whiteTurn)){
                    System.out.println("Black king at " + king.getPosition() + " is in CHECK!");
                }
                else{

                }

            }

            //Turn prompt

            if(whiteTurn){
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
                if(inputTokens[0].equalsIgnoreCase("-h")){
//                    System.out.println("");
//                    System.out.println("There are 5 valid inputs at the turn prompt:\n");
//                    System.out.println("1) <FileRank> <FileRank> e.g. d2 d3 to move the piece at d2 to d3");
//                    System.out.println("2) <FileRank> <FileRank> \"draw?\" e.g. d2 d3 draw? to propose a draw");
//                    System.out.println("3) \"resign\" to resign from the game");
//                    System.out.println("4) -h to see this help menu again");
//                    System.out.println("5) <FileRank> <FileRank> <Piece> e.g. g7 g8 N to promote a pawn to a unit other than queen");
//                    System.out.println("");
                    System.out.println("");
                    System.out.println("There are 4 valid inputs at the turn prompt:\n");
                    System.out.println("1) <FileRank> <FileRank> e.g. d2 d3 to move the piece at d2 to d3");
                    System.out.println("2) Draw to propose a draw");
                    System.out.println("3) Surrender to resign from the game");
                    System.out.println("4) -h to see this help menu again");
                    System.out.println("5) <FileRank> <FileRank> <Piece> e.g. g7 g8 N to promote a pawn to a unit other than queen");
                    System.out.println("");
                    continue;
                }
                
                
                else if(inputTokens[0].equalsIgnoreCase("draw") || inputTokens[0].equalsIgnoreCase("dr")
                		|| inputTokens[0].equalsIgnoreCase("무승부")){
                    if(whiteTurn){
                        System.out.println("White has proposed a draw.");
                        System.out.println("Black, please enter \"draw\" to accept or any other input to continue the game: ");
                        Scanner inputDraw = new Scanner(System.in);
                        String userInputDraw = inputDraw.nextLine();
                        userInputDraw = userInputDraw.trim();
                        if(userInputDraw.equalsIgnoreCase("draw")){
                            System.out.println("Draw");
                            System.exit(0);
                        }
                        else{ //Draw rejected. Perform entered move and continue game
                            System.out.println("Black did not accept the draw");
                            boolean canMove = Controller.isValidMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);
                            if(canMove == false){
                                continue;
                            }
                            else{
                            	//////////////////////////////////
			                	int r = Controller.rankToInd(inputTokens[0]);
			                	int c = Controller.fileToInd(inputTokens[0]);
			                	if(Controller.satisfiesPromo(Chessboard.GBoard[r][c],inputTokens[1]))
			                		Controller.processPromo(Chessboard.GBoard[r][c], Chessboard);
                                Chessboard = Controller.processMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);
                                Chessboard.printBoard();
                                whiteTurn = !whiteTurn;
                                continue;
			                	//////////////////////////////////
                            }
                        }
                    }
                    else{
                        System.out.println("Black has proposed a draw.");
                        System.out.println("White, please enter \"draw\" to accept or any other input to continue the game: ");
                        Scanner inputDraw = new Scanner(System.in);
                        String userInputDraw = inputDraw.nextLine();
                        userInputDraw = userInputDraw.trim();
                        if(userInputDraw.equalsIgnoreCase("draw")){
                            System.out.println("Draw");
                            System.exit(0);
                        }
                        else{ //Draw rejected
                            System.out.println("White did not accept the draw");
                            boolean canMove = Controller.isValidMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);
                            if(canMove == false){
                                continue;
                            }
                            else{
                            	////////////////////////
			                	int r = Controller.rankToInd(inputTokens[0]);
			                	int c = Controller.fileToInd(inputTokens[0]);
			                	if(Controller.satisfiesPromo(Chessboard.GBoard[r][c],inputTokens[1]))
			                		Controller.processPromo(Chessboard.GBoard[r][c], Chessboard);
                                Chessboard = Controller.processMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);
                                Chessboard.printBoard();
                                whiteTurn = !whiteTurn;
                                continue;
			                	////////////////////////
                            }
                        }
                    }
                }
            
                
                
                else if(inputTokens[0].equalsIgnoreCase("surrender") || inputTokens[0].equalsIgnoreCase("gg")
                		|| inputTokens[0].equalsIgnoreCase("항복") || inputTokens[0].equalsIgnoreCase("지지")
                				|| inputTokens[0].contains("ㅈㅈ")){
                    if(whiteTurn){
                        System.out.println("백 기권");
                        System.out.println("흑 승리");
                        System.exit(0);
                    }
                    else{
                        System.out.println("흑 기권");
                        System.out.println("백 승리");
                        System.exit(0);
                    }
                }else if(inputTokens[0].equalsIgnoreCase("Exit")) {
                	Menu kenu=new Menu();
                	kenu.mainMenu();
                }
                else{
                    System.out.println("유효하지 않은 입력입니다.");
                    continue;
                }
            }
            else if(inputTokens.length == 2){ //If the user entered 2 tokens, it must be a move entry. check if valid then if valid, perform move
                boolean canMove = Controller.isValidMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);
                if(canMove == false){
                    continue;
                }
                else{
                	////////////////////////
                	int r = Controller.rankToInd(inputTokens[0]);
                	int c = Controller.fileToInd(inputTokens[0]);
                	if(Controller.satisfiesPromo(Chessboard.GBoard[r][c], inputTokens[1]))
                		Controller.processPromo(Chessboard.GBoard[r][c], Chessboard);
                    Chessboard = Controller.processMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);
                    Chessboard.printBoard();
                    whiteTurn = !whiteTurn;
                    continue;
                	////////////////////////
                }
            }
            else if(inputTokens.length == 3)
            { //If user entered 3 tokens, it could be draw proposal or promotion
                // CASE: Promotion
                System.out.println("프로모션할 기물을 선택해주세요.");
                if(inputTokens[2].equalsIgnoreCase("R") || inputTokens[2].equalsIgnoreCase("Rook") || inputTokens[2].equalsIgnoreCase("룩") ||
                        inputTokens[2].equalsIgnoreCase("K") || inputTokens[2].equalsIgnoreCase("King") || inputTokens[2].equalsIgnoreCase("킹") ||
                        inputTokens[2].equalsIgnoreCase("N") || inputTokens[2].equalsIgnoreCase("kNight") || inputTokens[2].equalsIgnoreCase("나이트") ||
                        inputTokens[2].equalsIgnoreCase("B") || inputTokens[2].equalsIgnoreCase("Bishop") || inputTokens[2].equalsIgnoreCase("비숍") ||
                        inputTokens[2].equalsIgnoreCase("Q") || inputTokens[2].equalsIgnoreCase("Queen") || inputTokens[2].equalsIgnoreCase("퀸")){
                    boolean canMove = Controller.isValidMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);

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
                    	////////////////////////
                    	int r = Controller.rankToInd(inputTokens[0]);
                    	int c = Controller.fileToInd(inputTokens[0]);
                    	if(Controller.satisfiesPromo(Chessboard.GBoard[r][c], inputTokens[1]))
                    		Controller.processPromo(Chessboard.GBoard[r][c], Chessboard);
                        Chessboard = Controller.processMove(inputTokens[0], inputTokens[1], Chessboard, whiteTurn);
                        Chessboard.printBoard();
                        whiteTurn = !whiteTurn;
                        continue;
                    	////////////////////////
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
            while((turn-myColor)%2!=0)
        {
            	 try {
						Thread.sleep(500);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
            	BufferedReader br=null;
         		try {
         			br = new BufferedReader(new FileReader(file));
         		} catch (FileNotFoundException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
         		String line1="";
         	    try {
         			line1=br.readLine();
         			line1=br.readLine();
         			line1=br.readLine();
         		} catch (IOException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
         	   String[] sToken = line1.split(",");
            	 turn=Integer.parseInt(sToken[2]);
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
	    
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f, true));
        bufferedWriter.write(line1); //한줄 입력
        bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
        bufferedWriter.write(line2); //한줄 입력
        bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
		try {			
			bufferedWriter.write(move+","+turn);
			turn++;
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc2 = new Scanner(f);
		StringBuffer buffer2 = new StringBuffer();
		while (sc2.hasNextLine()) 
		{
			buffer2.append(sc2.nextLine()+System.lineSeparator());
		}
    }
    //fileBefore.equals(fileAfter) 수행 후, 둘이 다르다면 inputFile실행해서 가장 마지막 줄 가져옴.
    public String inputFile(File f) throws FileNotFoundException	//대전파일에서 읽어옴
    {
	    String lastLine = "";
		BufferedReader br;
		try 
		{
		    String sCurrentLine;

		    br = new BufferedReader(new FileReader(f));
		    sCurrentLine=br.readLine();
		    sCurrentLine=br.readLine();
		    sCurrentLine=br.readLine();
		        lastLine = sCurrentLine;
	
		} catch (IOException e) 
		{
		    e.printStackTrace();
		}
		return lastLine;
    }
    
    int getMyturn(File f) {	//내가 흑인지 백인지를 리턴
    	BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line1="";
		String line2="";
	    try {
			line1=br.readLine();
			line2=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(line1.equals(userID)) {
	    	return 1;
	    }else if(line2.equals(userID)) {
	    	return 2;
	    }else {
	    	BufferedReader brr;
	    	String line11="";
		    try {
		    	brr = new BufferedReader(new FileReader(f));
				line1=brr.readLine();
				line2=brr.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			BufferedWriter bufferedWriter=null;
			try {
				bufferedWriter = new BufferedWriter(new FileWriter(f, true));
				bufferedWriter.write(line11);
				bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
				bufferedWriter.write(userID); //한줄 입력
		        bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return 2;
	    }
		
    }
}
