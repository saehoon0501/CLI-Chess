import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleMain {
    private ArrayList<Puzzle> puzzles;
    private ArrayList <String> fName = new ArrayList<String>();

    public PuzzleMain() {
        this.puzzles = new ArrayList<Puzzle>(15);
    }

    public void start() throws IOException {
        System.out.println("Welcome to Puzzle Mode !");
        System.out.println("Please Select Number in below list"+"\n");
        printPuzzles(this.puzzles);

        System.out.println("Select :");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.matches("[0-9]+") || input.length()!=1)
        {
        	System.err.println("Wrong input! Select number from the list below");
        	System.out.println("Select :");
        	input = scanner.nextLine();
        }
        int inputNum = Integer.parseInt(input);
        if(inputNum ==1)
        {
            PuzzleControl puzzleControl = new PuzzleControl();
            puzzleControl.start();
        }
        else if(inputNum>=2 && inputNum<=15)
        {
        	//파일을 읽어와서 퍼즐 객체 생성
        	Puzzle puzzle = new Puzzle("PuzzleMode"+"/"+fName.get(inputNum-2));
        	puzzle.setHasBeenSolved(false);
        	if(puzzle.cleared.contains(Main.userID))
        		puzzle.setHasBeenSolved(true);
        	System.out.println(puzzle.isHasBeenSolved());
        	playPuzzle(puzzle);
        	
        }
    }

    public void printPuzzles(ArrayList<Puzzle> puzzles)
    {
        System.out.println("1." + "PuzzleMaker");
        int index=2;
        File dir = new File("PuzzleMode");
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isFile() && !file.getName().equals(".DS_Store")) 
            {
                System.out.println(index+"."+file.getName());
                fName.add(file.getName());
                index++;
            }
        }
    }
    public String playPuzzle(Puzzle myPuzzle) 
    {
    	Board boardOriginal = myPuzzle.getPuzzleBoard();
    	Board boardTemp = copyBoard(boardOriginal);
    	int turn = myPuzzle.getTheme() * 2;
    	int moveCount = 0;
    	boolean myWhite = false;
    	String[] moveInfo = myPuzzle.getPlaydata().split(",");
    	String movedStr = "";
    	String hintStr = "";
    	
    	while(turn > 0) {
        	System.out.println("Welcome to Puzzle Play\n");
        	System.out.println("Puzzle name: "+myPuzzle.getName());
        	String moveToken[];
        	
        	if(turn%2 == 0) {
        		moveToken = moveInfo[moveCount].split(" ");
            	boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);
            	myWhite = !myWhite;
            	moveCount++;
            	turn--;
        	}
        	
        	System.out.println(turn +" turn left!");
        	boardTemp.printBoard();
        	
        	// 이동 로그, 힌트 출력
        	System.out.print(movedStr);
        	System.out.print(hintStr);
        	//movedStr = "";
        	hintStr = "";
        	
        	System.out.print("\t>_");
        	
        	Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            input = input.trim();
            moveToken = input.split(" ");
            
            
            // 좌표 입력으로 인식 가능한지 판별
            if(moveToken.length == 2 && moveToken[0].length() == 2 && moveToken[1].length() == 2 &&
            		Controller.rankToInd(moveToken[0])!=-1 && Controller.fileToInd(moveToken[0])!=-1 &&
            		Controller.rankToInd(moveToken[1])!=-1 && Controller.fileToInd(moveToken[1])!=-1) {
            		Board boardSave = copyBoard(boardTemp);
            		if(Controller.isValidMove(moveToken[0], moveToken[1], boardTemp, myWhite))
            			boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);
            		else {
            			continue;
            		}
            		boardTemp.printBoard();
            		String[] answerToken = moveInfo[moveCount].split(" ");
            		if(moveToken[0].equalsIgnoreCase(answerToken[0]) && moveToken[1].equalsIgnoreCase(answerToken[1])) {
            			myWhite = !myWhite;
            			moveCount++;
            			turn--;
            		} else {
            			System.out.println("오답입니다.");
            			

            			boolean flag = true;
            			while(flag) {
            				System.out.println("명령어의 종류: replay, hint, solution, next, previous, exit");
                			System.out.println("위의 명령어 중 하나를 입력해주세요.");
                			System.out.print("\t>_");
            				String input2 = scanner.nextLine();
                			input2.trim();
                			if(input2.equalsIgnoreCase("replay")) {
                				boardTemp = copyBoard(boardSave);
                				flag = false;
                			} else if(input2.equalsIgnoreCase("hint")) {
                				boardTemp = copyBoard(boardSave);
                				moveToken = moveInfo[moveCount].split(" ");
//                				char pos1 = moveToken[0].charAt(0);
//                				char pos2 = moveToken[0].charAt(1);
//                				int pos2_int = Character.getNumericValue(pos2);
//                				String pieceType = boardTemp.GBoard[pos2_int-1][pos1-'a'].getPiece();
                				String pieceType = boardTemp.GBoard[Controller.rankToInd(moveToken[0])][Controller.fileToInd(moveToken[0])].getPiece();
                            	switch(pieceType) {
                            	case "♟":
                            		hintStr = "폰 움직이기\n";
                            		break;
                            	case "♜":
                            		hintStr = "룩 움직이기\n";
                            		break;
                            	case "♞":
                            		hintStr = "나이트 움직이기\n";
                            		break;
                            	case "♝︎":
                            		hintStr = "비숍 움직이기\n";
                            		break;
                            	case "♛":
                            		hintStr = "퀸 움직이기\n";
                            		break;
                            	case "♚":
                            		hintStr = "킹 움직이기\n";
                            		break;
                            	default:
                            		break;
                            	}
                            	flag = false;
                			} else if(input2.equalsIgnoreCase("solution")) {
                				boardTemp = copyBoard(boardSave);
                				moveToken = moveInfo[moveCount].split(" ");
                            	boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);
                            	myWhite = !myWhite;
                            	moveCount++;
                            	turn--;
                            	flag = false;
                			} else if(input2.equalsIgnoreCase("next")) {
                				System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                				String yn = scanner.nextLine();
                				yn.trim();
                				if(yn.equalsIgnoreCase("Y")) {
                					return ""; // 현재 파일의 이름에 대한 인자도 필요. 현재 인덱스가 0이면 이전파일 없다
                				} else if(yn.equalsIgnoreCase("N")){
                					
                				} else {
                					System.out.println("잘못된 입력입니다.");
                				}
                			} else if(input2.equalsIgnoreCase("previous")) {
                				System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                				String yn = scanner.nextLine();
                				yn.trim();
                				if(yn.equalsIgnoreCase("Y")) {
                					return ""; // 파일의 사이즈-1 = 자신의 인덱스 -> 다음파일 없다
                				} else if(yn.equalsIgnoreCase("N")){
                					
                				} else {
                					System.out.println("잘못된 입력입니다.");
                				}
                			} else if(input2.equalsIgnoreCase("exit")) {
                				System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                				String yn = scanner.nextLine();
                				yn.trim();
                				if(yn.equalsIgnoreCase("Y")) {
                					return ""; // 걍 종료하면 됨
                				} else if(yn.equalsIgnoreCase("N")){
                					
                				} else {
                					System.out.println("잘못된 입력입니다.");
                				}
                			} else {
                				System.out.println("잘못된 입력입니다.");
                			}
            			}
	
            		}
            		
            } else {
            	// 커맨드 입력으로 인식 가능한지 판별. 이 부분 switch 말고 if로 바꿔야함
            	switch(input) {
            	case "1":
            	case "1.":
            	case "replay":
            		boardTemp = copyBoard(boardOriginal);
    				continue;
            	case "2":
            	case "2.":
            	case "hint":
    				moveToken = moveInfo[moveCount].split(" ");
    				String pieceType = boardTemp.GBoard[Controller.rankToInd(moveToken[0])][Controller.fileToInd(moveToken[0])].getPiece();
                	switch(pieceType) {
                	case "♟":
                		hintStr = "폰 움직이기\n";
                		break;
                	case "♜":
                		hintStr = "룩 움직이기\n";
                		break;
                	case "♞":
                		hintStr = "나이트 움직이기\n";
                		break;
                	case "♝︎":
                		hintStr = "비숍 움직이기\n";
                		break;
                	case "♛":
                		hintStr = "퀸 움직이기\n";
                		break;
                	case "♚":
                		hintStr = "킹 움직이기\n";
                		break;
                	default:
                		break;
                	}
                	continue;
            	case "3":
            	case "3.":
            	case "solution":
            		moveToken = moveInfo[moveCount].split(" ");
                	boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);
                	myWhite = !myWhite;
                	moveCount++;
                	turn--;
                	continue;
            	case "4":
            	case "4.":
            	case "next":
            		System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
    				String yn = scanner.nextLine();
    				yn.trim();
    				if(yn.equalsIgnoreCase("Y")) {
    					return "";
    				} else if(yn.equalsIgnoreCase("N")){
    					
    				} else {
    					System.out.println("잘못된 입력입니다.");
    				}
            		break;
            	case "5":
            	case "5.":
            	case "previous":
            		System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
    				String yn2 = scanner.nextLine();
    				yn2.trim();
    				if(yn2.equalsIgnoreCase("Y")) {
    					return "";
    				} else if(yn2.equalsIgnoreCase("N")){
    					
    				} else {
    					System.out.println("잘못된 입력입니다.");
    				}
            		break;
            	case "6":
            	case "6.":
            	case "exit":
            		System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
    				String yn3 = scanner.nextLine();
    				yn3.trim();
    				if(yn3.equalsIgnoreCase("Y")) {
    					return "";
    				} else if(yn3.equalsIgnoreCase("N")){
    					
    				} else {
    					System.out.println("잘못된 입력입니다.");
    				}
            		break;
            	default:
            		System.out.println("잘못된 입력입니다."); // 인식할 수 없는 입력인 경우
            		break;
            	}

            }
    	}
    	boardTemp.printBoard();
    	System.out.println("축하합니다! 퍼즐을 푸는 것에 성공했습니다.");
		boolean flag = true;
		// 퍼즐 성공 후 커맨드
		while(flag) {
			System.out.println("명령어의 종류: replay, hint(선택불가), solution(선택불가), next, previous, exit");
			System.out.println("위의 명령어 중 하나를 입력해주세요.");
			System.out.print("\t>_");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			input.trim();
			if(input.equalsIgnoreCase("replay")) {
//				boardTemp = copyBoard(boardOriginal);
//				turn = myPuzzle.getTheme() * 2;
//		    	moveCount = 0;
//		    	myWhite = false; // 이 부분 next/previous랑 비슷하게 구현
//				flag = false;
				return "";
			} else if(input.equalsIgnoreCase("hint")) {
				System.out.println("현재 상태에서 수행할 수 없는 명령어입니다.");
			} else if(input.equalsIgnoreCase("solution")) {
				System.out.println("현재 상태에서 수행할 수 없는 명령어입니다.");
			} else if(input.equalsIgnoreCase("next")) {
				System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
				String yn = scanner.nextLine();
				yn.trim();
				if(yn.equalsIgnoreCase("Y")) {
					return "";
				} else if(yn.equalsIgnoreCase("N")){
					
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			} else if(input.equalsIgnoreCase("previous")) {
				System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
				String yn = scanner.nextLine();
				yn.trim();
				if(yn.equalsIgnoreCase("Y")) {
					return "";
				} else if(yn.equalsIgnoreCase("N")){
					
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			} else if(input.equalsIgnoreCase("exit")) {
				System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
				String yn = scanner.nextLine();
				yn.trim();
				if(yn.equalsIgnoreCase("Y")) {
					return "";
				} else if(yn.equalsIgnoreCase("N")){
					
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
		return ""; // 에러 때문에 작성. 실질적으로는 의미 없는 코드
    }
    
    // Board 객체 깊은 복사
    public Board copyBoard(Board b) {
    	Board nb = new Board();
    	nb.GBoard = new Gamepiece[8][8];
    	for(int i=0; i<8; i++) {
        	for(int j=0; j<8; j++) {
        		if(b.GBoard[i][j]!=null)
        			nb.GBoard[i][j] = copyGamepiece(b.GBoard[i][j]);
        	}
        }
        nb.lastPieceMoved = b.lastPieceMoved;
        nb.blackPieceList = new ArrayList<String>();
        for(int i=0; i<b.blackPieceList.size(); i++) {
        	nb.blackPieceList.add(b.blackPieceList.get(i));
        }
        nb.whitePieceList = new ArrayList<String>();
        for(int i=0; i<b.whitePieceList.size(); i++) {
        	nb.whitePieceList.add(b.whitePieceList.get(i));
        }
        nb.turn = b.turn;
        return nb;
    }
    
    // Gamepiece 객체 깊은 복사
    public Gamepiece copyGamepiece(Gamepiece p) {
        Gamepiece np = new Gamepiece();
        np.setPiece(p.getPiece());
        np.setPosition(p.getPosition());
        np.setPlayer(p.getPlayer());
        ArrayList<String> temp = new ArrayList<String>();
        for(int i=0; i<p.getMoves().size(); i++) {
        	temp.add(p.getMoves().get(i));
        }
        np.setMoves(temp);
        np.setMoveCount(p.getMoveCount());
        return np;
    }
    
    
    
}
