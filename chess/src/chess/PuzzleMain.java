package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PuzzleMain {
    private ArrayList<Puzzle> puzzles;
    private ArrayList <String> fName = new ArrayList<String>();
    private String [] validCommands = {"1","1.","replay","re","다시","다시플레이하기","2","2.","hint","ht","힌트","힌트보기","3","3.","solution","sol",
            "정답","정답보기","4","4.","next","nx","다음으로","다음퍼즐로이동","5","5.","previous","pr","이전으로","이전퍼즐로이동",
            "6","6.","exit","ex","종료","메뉴로돌아가기"};

    public PuzzleMain() {
        this.puzzles = new ArrayList<Puzzle>(15);
    }

    public void start() throws IOException {
        int p_index=-1;
        int pl_index=0;
        while(p_index!=0){
            printPuzzleList(pl_index);
            Scanner scanner = new Scanner(System.in);
            String p_input=scanner.nextLine();
            p_index=checkPInput(p_input, pl_index);
            switch(p_index) {
                case 1: //새 퍼즐 만들기
                    PuzzleControl puzzleControl = new PuzzleControl();
                    puzzleControl.start();
                    break;
                case 2:   //Next 출력
                    pl_index+=1;
                    break;
                case 3:   //Back 출력
                    pl_index-=1;
                    break;
                case 4:   //메인메뉴로 복귀
                    p_index=0;
                    break;
                case 5:   //2 이상이고 선택 가능한 자연수를 입력
                    p_input=p_input.replaceAll("\\p{Z}", "");   //입력에서 모든 공백 제거
                    //해당 파일을 읽어와 객체 생성및 구현(p_input에서 -2해야함, 클래스 내부에서 해당 이름으로 참가가 가능한지도 확인해야함)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    Puzzle puzzle = new Puzzle("PuzzleMode"+"/"+fName.get(Integer.parseInt(p_input)-2));
                    puzzle.setHasBeenSolved(false);
                    if(puzzle.cleared.contains(Main.userID))
                        puzzle.setHasBeenSolved(true);
                    //파일을 읽어와서 퍼즐 객체 생성(수정했어요)
                    String myFileName = "PuzzleMode"+"/"+fName.get(Integer.parseInt(p_input)-2);

                    while(!myFileName.equals("")) {

                        puzzle = new Puzzle(myFileName);
                        puzzle.setHasBeenSolved(false);
                        if(puzzle.cleared.contains(Main.userID))
                            puzzle.setHasBeenSolved(true);
                        //파일을 읽어와서 퍼즐 객체 생성(수정했어요)



                        String str = playPuzzle(puzzle, fName, myFileName);
                        if(!str.equals("")) {
                            myFileName = "PuzzleMode"+"/"+str;
                        }
                        else {
                            myFileName = "";
                        }
                    }
                    p_index=0;
                    break;
                case 6:   //잘못된 입력
                    break;
                default:
                    break;
            }
        }
        //-------------------------------------------------
        //printPuzzles(this.puzzles);
        //Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();
        //while(!input.matches("[0-9]+") || input.length()!=1)
        //{
        //   System.err.println("잘못된 입력입니다.");
        //   input = scanner.nextLine();
        //}
        //int inputNum = Integer.parseInt(input);
        //if(inputNum ==1)
        //{
        //   PuzzleControl puzzleControl = new PuzzleControl();
        //    puzzleControl.start();
        //}
        //else if(inputNum>=2 && inputNum<=15)
        //{
        //   //파일을 읽어와서 퍼즐 객체 생성
        //   Puzzle puzzle = new Puzzle("PuzzleMode"+"/"+fName.get(inputNum-2));
        //   puzzle.setHasBeenSolved(false);
        //   if(puzzle.cleared.contains(Main.userID))
        //      puzzle.setHasBeenSolved(true);
        //   //파일을 읽어와서 퍼즐 객체 생성(수정했어요)
        //   String myFileName = "PuzzleMode"+"/"+fName.get(inputNum-2);
        //
        //   while(!myFileName.equals("")) {
        //      String str = playPuzzle(puzzle, fName, myFileName);
        //      if(!str.equals("")) {
        //          myFileName = "PuzzleMode"+"/"+str;
        //       }
        //       else {
        //          myFileName = "";
        //       }
        //   }
        //
        //}
    }

    int checkPInput(String input, int pl_index) {   //퍼즐메뉴에서의 사용자 입력이 문법 규칙에 맞는지 확인
        String p_input=input.replaceAll("\\p{Z}", "");   //입력에서 모든 공백 제거
        if(p_input.equals("1")||p_input.equals("1.")||p_input.equals("makepuzzle"))
        {
            return 1;
        }else if(p_input.equals("Next")||p_input.equals("Nx")||p_input.equals("다음으로")||p_input.equals("다음페이지로이동"))
        {
            File pPath = new File("./PuzzleMode");   //파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
            File pFileList[]=pPath.listFiles();
            if((pl_index+1)*14<=pFileList.length) {
                return 2;
            }else {
                System.out.println("다음 페이지가 존재하지 않습니다.");
                return 6;
            }
        }else if(p_input.equals("Back")||p_input.equals("Bc")||p_input.equals("이전으로")||p_input.equals("이전페이지로이동"))
        {
            if((pl_index-1)>=0) {
                return 3;
            }else {
                System.out.println("이전 페이지가 존재하지 않습니다.");
                return 6;
            }
        }else if(p_input.equals("Exit")||p_input.equals("Ex")||p_input.equals("종료")||p_input.equals("메뉴로 돌아가기"))
        {
            return 4;
        }else
        {
            File dPath = new File("./PuzzleMode");   //파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
            File pFileList[]=dPath.listFiles();
            if(p_input.matches("[+-]?\\d*(\\.\\d+)?")&&Integer.parseInt(p_input)>=2&&Integer.parseInt(p_input)-2<pFileList.length) {
                return 5;
            }else {
                return 6;
            }
        }
    }
    void printPuzzleList(int findex){   //퍼즐메뉴 목록 출력
        System.out.println("1." + "PuzzleMaker");
        int index=2;
        File dir = new File("PuzzleMode");
        File[] filesList = dir.listFiles();
        for (int i=findex*14; i<findex*14+14; i++) {
            if(i<filesList.length) {
                File file=filesList[i];
                if (file.isFile() && !file.getName().equals(".DS_Store")) {
                    Puzzle puzzle = new Puzzle("PuzzleMode/"+file.getName());
                    System.out.println(index+"."+file.getName()+"   퍼즐이름:"+puzzle.getName()+" 제작자 이름:"+puzzle.getUserName()+" 테마:"+puzzle.getTheme()
                            +" 클리어 여부:"+puzzle.cleared.contains(Main.userID));
                    fName.add(file.getName());
                    index++;
                }
            }
        }
        while(index<16)
        {
            System.out.println("");
            index++;
        }
        System.out.println("[Back, Next]");
        //System.out.println("1.새 퍼즐 생성");
        //File dir = new File("PuzzleMode");
        //File[] filesList = dir.listFiles();
        //int i=findex*14;
        //int k=1;
        //while(k!=14) {
        //   File file=filesList[i];
        //    if (file.isFile() && !file.getName().equals(".DS_Store")){
        //      Puzzle puzzle = new Puzzle("PuzzleMode/"+file.getName());
        //   System.out.println((i+2)+"."+file.getName()+"   퍼즐이름:"+puzzle.getName()+" 제작자 이름:"+puzzle.getUserName()+" 테마:"+puzzle.getTheme()
        //   +" 클리어 여부:"+puzzle.cleared.contains(Main.userID));
        // fName.add(file.getName());
        // i++;
        // k++;
        // }else if(file.getName().equals(".DS_Store")) {
        //     i++;
        // }else {
        //    System.out.println();
        //    k++;
        // }
        // }
        //System.out.println("[Back, Next]");
    }
    public String playPuzzle(Puzzle myPuzzle, ArrayList<String> fName, String myFileName) throws FileNotFoundException {
        Board boardOriginal = myPuzzle.getPuzzleBoard();
        Board boardTemp = copyBoard(boardOriginal);

        // 화면 clear
//          System.out.println("\033[H\033[2J");
//           System.out.flush();

        //boardTemp.printBoard();
        int turn = myPuzzle.getTheme() * 2;
        int moveCount = 0;
        boolean myWhite = false;
        String[] moveInfo = myPuzzle.getPlaydata().split(",");
        String movedStr = "";
        String movedStrSave = "";           // 추가한 거
        String hintStr = "";

        System.out.println("Welcome to Puzzle Play\n");
        System.out.println("Puzzle name: "+myPuzzle.getName());

        while(turn > 0) {
//              System.out.println("Welcome to Puzzle Play\n");
//              System.out.println("Puzzle name: "+myPuzzle.getName());
            String moveToken[];

            if(turn%2 == 0) {
                moveToken = moveInfo[moveCount].split(" ");
                // 로그 문자열 지정
                String str = "";
                if(myWhite) {
                    str += "흰색 ";
                } else {
                    str += "검은색 ";
                }
                int i = Controller.rankToInd(moveToken[0]);
                int j = Controller.fileToInd(moveToken[0]);
                System.out.println("\t\t:"+moveToken[0]);
                System.out.println(boardTemp.GBoard[0][3].getPiece());
                System.out.println(i+"   \t   "+j);
                switch(boardTemp.GBoard[i][j].getPiece())
                {
                    case "♙":
                    case "♟":
                        str += "폰";
                        break;
                    case "♖":
                    case "♜":
                        str += "룩";
                        break;
                    case "♘":
                    case "♞":
                        str += "나이트";
                        break;
                    case "♗":
                    case "♝︎":
                        str += "비숍";
                        break;
                    case "♕":
                    case "♛":
                        str += "퀸";
                        break;
                    case "♚":
                    case "♔":
                        str += "킹";
                        break;
                }
                str += "(";
                str += moveToken[0].toLowerCase();
                str += ")";
                str += "가 ";
                str += moveToken[1].toLowerCase();
                str += "로 이동했습니다.\n";
                movedStr = str;
                movedStrSave = movedStr;

                boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);
                myWhite = !myWhite;
                moveCount++;
                turn--;
            }

            try {
                Thread.sleep(2000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }

            // 화면 clear
//              System.out.println("\033[H\033[2J");
//               System.out.flush();

            System.out.println(turn +" turn left!");
            boardTemp.printBoard();

            // 이동 로그, 힌트 출력
            if(!movedStr.equals("")) {
                System.out.print(movedStr);
            } else {
                movedStr = movedStrSave;
                System.out.print(movedStr);
            }
            System.out.print(hintStr);
            //movedStr = "";
            hintStr = "";

            System.out.print("\t>_");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            input = input.toLowerCase(); // 소문자로 변경 추가
            input = input.trim();
            moveToken = input.split(" ");


            // 좌표 입력으로 인식 가능한지 판별
            if(moveToken.length == 2 && moveToken[0].length() == 2 && moveToken[1].length() == 2 &&
                    Controller.rankToInd(moveToken[0])!=-1 && Controller.fileToInd(moveToken[0])!=-1 &&
                    Controller.rankToInd(moveToken[1])!=-1 && Controller.fileToInd(moveToken[1])!=-1) {
                Board boardSave = copyBoard(boardTemp);
                if(Controller.isValidMove(moveToken[0], moveToken[1], boardTemp, myWhite)) {
                    // 로그 문자열 지정
                    String str = "";
                    if(myWhite) {
                        str += "흰색 ";
                    } else {
                        str += "검은색 ";
                    }
                    int i = Controller.rankToInd(moveToken[0]);
                    int j = Controller.fileToInd(moveToken[0]);
                    switch(boardTemp.GBoard[i][j].getPiece()) {
                        case "♙":
                        case "♟":
                            str += "폰";
                            break;
                        case "♖":
                        case "♜":
                            str += "룩";
                            break;
                        case "♘":
                        case "♞":
                            str += "나이트";
                            break;
                        case "♗":
                        case "♝︎":
                            str += "비숍";
                            break;
                        case "♕":
                        case "♛":
                            str += "퀸";
                            break;
                        case "♚":
                        case "♔":
                            str += "킹";
                            break;
                    }
                    str += "(";
                    str += moveToken[0].toLowerCase();
                    str += ")";
                    str += "가 ";
                    str += moveToken[1].toLowerCase();
                    str += "로 이동했습니다.\n";
                    movedStr = str;

                    boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);


                }
                else {
                    continue;
                }



                // 화면 clear
//                      System.out.println("\033[H\033[2J");
//                       System.out.flush();

                //System.out.println(turn +" turn left!");
                boardTemp.printBoard();

                // 이동 로그, 힌트 출력
                if(!movedStr.equals("")) {
                    System.out.print(movedStr);
                } else {
                    movedStr = movedStrSave;
                    System.out.print(movedStr);
                }
                System.out.print(hintStr);
                //movedStr = "";
                hintStr = "";






                String[] answerToken = moveInfo[moveCount].split(" ");
                if(moveToken[0].equalsIgnoreCase(answerToken[0]) && moveToken[1].equalsIgnoreCase(answerToken[1])) {
                    myWhite = !myWhite;
                    moveCount++;
                    turn--;
                } else {
                    System.out.println("오답입니다.");





                    boolean flag = true;
                    while(flag) {

                        try {
                            Thread.sleep(2000);
                        }catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 화면 clear
//                             System.out.println("\033[H\033[2J");
//                              System.out.flush();

                        System.out.println("명령어의 종류: replay, hint, solution, next, previous, exit (1~6)");
                        System.out.println("위의 명령어 중 하나를 입력해주세요.");
                        System.out.print("\t>_");
                        String input2 = scanner.nextLine();
                        input2 = input2.trim().replaceAll("\\s+","").toLowerCase(); // 대소문자 예외 추가
                        input2 = input2.replace(" ", ""); // 추가함
                        //유효한 커맨드 배열에 사용자가 입력한 커맨드가 존재하는지 확인
                        while(!Arrays.asList(validCommands).contains(input2))
                        {
                            System.err.println("잘못된 입력입니다");
                            System.out.println("명령어의 종류: replay, hint, solution, next, previous, exit (1~6)");
                            System.out.println("위의 명령어 중 하나를 입력해주세요.");
                            System.out.print("\t>_");
                            input2 = scanner.nextLine();
                            input2.trim().replaceAll("\\s+","").toLowerCase();
                            input2 = input2.replace(" ", ""); // 추가함
                        }
                        //유효한 커맨드  배열의 0~6인덱스 까지, 즉 1번 커맨드의 모든 변형들만 있는 부분에 사용자 입력이 존재하는지 확인
                        if(Arrays.asList(Arrays.copyOfRange(validCommands, 0, 6)).contains(input2)) {
                            boardTemp = copyBoard(boardSave);
                            movedStr = movedStrSave; // 추가한 거
                            flag = false;
                        } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 6, 12)).contains(input2)) {
                            boardTemp = copyBoard(boardSave);
                            movedStr = movedStrSave; // 추가한 거
                            moveToken = moveInfo[moveCount].split(" ");
                            String pieceType = boardTemp.GBoard[Controller.rankToInd(moveToken[0])][Controller.fileToInd(moveToken[0])].getPiece();
                            switch(pieceType) {
                                case "♟":
                                case "♙":
                                    hintStr = "힌트: 폰 움직이기\n";
                                    break;
                                case "♜":
                                case "♖":
                                    hintStr = "힌트: 룩 움직이기\n";
                                    break;
                                case "♞":
                                case "♘":
                                    hintStr = "힌트: 나이트 움직이기\n";
                                    break;
                                case "♝︎":
                                case "♗":
                                    hintStr = "힌트: 비숍 움직이기\n";
                                    break;
                                case "♛":
                                case "♕":
                                    hintStr = "힌트: 퀸 움직이기\n";
                                    break;
                                case "♚":
                                case "♔":
                                    hintStr = "힌트: 킹 움직이기\n";
                                    break;
                                default:
                                    break;
                            }
                            flag = false;
                        } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 12, 18)).contains(input2)) {
                            boardTemp = copyBoard(boardSave);
                            movedStr = movedStrSave; // 추가한 거
                            moveToken = moveInfo[moveCount].split(" ");

                            // 로그 문자열 지정
                            String str = "";
                            if(myWhite) {
                                str += "흰색 ";
                            } else {
                                str += "검은색 ";
                            }
                            int i = Controller.rankToInd(moveToken[0]);
                            int j = Controller.fileToInd(moveToken[0]);
                            switch(boardTemp.GBoard[i][j].getPiece()) {
                                case "♙":
                                case "♟":
                                    str += "폰";
                                    break;
                                case "♖":
                                case "♜":
                                    str += "룩";
                                    break;
                                case "♘":
                                case "♞":
                                    str += "나이트";
                                    break;
                                case "♗":
                                case "♝︎":
                                    str += "비숍";
                                    break;
                                case "♕":
                                case "♛":
                                    str += "퀸";
                                    break;
                                case "♚":
                                case "♔":
                                    str += "킹";
                                    break;
                            }
                            str += "(";
                            str += moveToken[0].toLowerCase();
                            str += ")";
                            str += "가 ";
                            str += moveToken[1].toLowerCase();
                            str += "로 이동했습니다.\n";
                            movedStr = str;


                            boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);





                            // 화면 clear
//                                  System.out.println("\033[H\033[2J");
//                                   System.out.flush();

                            boardTemp.printBoard();

                            // 이동 로그, 힌트 출력
                            if(!movedStr.equals("")) {
                                System.out.print(movedStr);
                            } else {
                                movedStr = movedStrSave;
                                System.out.print(movedStr);
                            }
                            System.out.print(hintStr);
                            //movedStr = "";
                            hintStr = "";


                            myWhite = !myWhite;
                            moveCount++;
                            turn--;
                            flag = false;
                        } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 18, 24)).contains(input2)) {
                            System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                            String yn = scanner.nextLine();
                            yn.trim();
                            if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                                    yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                                    yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                                    yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                                    yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                                // 자신의 인덱스+1 = 파일 리스트의 사이즈 -> 다음파일 없다
                                String[] nameToken = myFileName.split("/");
                                int fileIndex = fName.indexOf(nameToken[1]);
                                if(fileIndex+1 == fName.size()) {
                                    System.out.println("다음 퍼즐이 존재하지 않습니다.");
                                } else {
                                    return fName.get(fileIndex+1);
                                }
                            } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                                    yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                                    yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                                    yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                            } else {
                                System.out.println("잘못된 입력입니다.");
                            }

                        } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 24, 30)).contains(input2)) {
                            System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                            String yn = scanner.nextLine();
                            yn.trim();
                            if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                                    yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                                    yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                                    yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                                    yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                                // 현재 인덱스가 0이면 이전파일 없다
                                String[] nameToken = myFileName.split("/");
                                int fileIndex = fName.indexOf(nameToken[1]);
                                if(fileIndex == 0) {
                                    System.out.println("이전 퍼즐이 존재하지 않습니다.");
                                } else {
                                    return fName.get(fileIndex-1);
                                }
                            } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                                    yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                                    yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                                    yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                            } else {
                                System.out.println("잘못된 입력입니다.");
                            }
                        } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 30, 36)).contains(input2)) {
                            System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                            String yn = scanner.nextLine();
                            yn.trim();
                            if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                                    yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                                    yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                                    yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                                    yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                                return ""; // 그냥 종료
                            } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                                    yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                                    yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                                    yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                            } else {
                                System.out.println("잘못된 입력입니다.");
                            }
                        } else {
                            System.out.println("잘못된 입력입니다.");
                        }
                    }

                }

            } else {
                /////////////// 입력 예외처리 세부사항 작성(2)
                input.replaceAll("\\s+","").toLowerCase();
                input = input.replace(" ", ""); // 추가함
                //유효한 커맨드 배열에 사용자가 입력한 커맨드가 존재하는지 확인
//                if(!Arrays.asList(validCommands).contains(input))
//                {
//                   System.err.println("잘못된 입력입니다");
//
//                   try {
//                      Thread.sleep(2000);
//                   }catch(InterruptedException e) {
//                      e.printStackTrace();
//                   }
//                   // 화면 clear
//                    System.out.println("\033[H\033[2J");
//                     System.out.flush();
//
//                   System.out.println("명령어의 종류: replay, hint, solution, next, previous, exit (1~6)");
//                   System.out.println("위의 명령어 중 하나를 입력해주세요.");
//                   System.out.print("\t>_");
//                   input = scanner.nextLine();
//                   input.trim().replaceAll("\\s+","").toLowerCase();
//                }
                if(Arrays.asList(Arrays.copyOfRange(validCommands, 0, 6)).contains(input)) {
                    boardTemp = copyBoard(boardOriginal);
                    turn = myPuzzle.getTheme() * 2;
                    moveCount = 0;
                    myWhite = false;
                    movedStr = "";
                    movedStrSave = "";           // 추가한 거
                    hintStr = "";


                    continue;
                }
                else if(Arrays.asList(Arrays.copyOfRange(validCommands, 6, 12)).contains(input)) {
                    moveToken = moveInfo[moveCount].split(" ");
                    String pieceType = boardTemp.GBoard[Controller.rankToInd(moveToken[0])][Controller.fileToInd(moveToken[0])].getPiece();
                    switch(pieceType) {
                        case "♟":
                        case "♙":
                            hintStr = "힌트: 폰 움직이기\n";
                            break;
                        case "♜":
                        case "♖":
                            hintStr = "힌트: 룩 움직이기\n";
                            break;
                        case "♞":
                        case "♘":
                            hintStr = "힌트: 나이트 움직이기\n";
                            break;
                        case "♝︎":
                        case "♗":
                            hintStr = "힌트: 비숍 움직이기\n";
                            break;
                        case "♛":
                        case "♕":
                            hintStr = "힌트: 퀸 움직이기\n";
                            break;
                        case "♚":
                        case "♔":
                            hintStr = "힌트: 킹 움직이기\n";
                            break;
                        default:
                            break;
                    }
                    continue;
                }
                else if(Arrays.asList(Arrays.copyOfRange(validCommands, 12, 18)).contains(input)) {
                    moveToken = moveInfo[moveCount].split(" ");

                    // 로그 문자열 지정
                    String str = "";
                    if(myWhite) {
                        str += "흰색 ";
                    } else {
                        str += "검은색 ";
                    }
                    int i = Controller.rankToInd(moveToken[0]);
                    int j = Controller.fileToInd(moveToken[0]);
                    switch(boardTemp.GBoard[i][j].getPiece()) {
                        case "♙":
                        case "♟":
                            str += "폰";
                            break;
                        case "♖":
                        case "♜":
                            str += "룩";
                            break;
                        case "♘":
                        case "♞":
                            str += "나이트";
                            break;
                        case "♗":
                        case "♝︎":
                            str += "비숍";
                            break;
                        case "♕":
                        case "♛":
                            str += "퀸";
                            break;
                        case "♚":
                        case "♔":
                            str += "킹";
                            break;
                    }
                    str += "(";
                    str += moveToken[0].toLowerCase();
                    str += ")";
                    str += "가 ";
                    str += moveToken[1].toLowerCase();
                    str += "로 이동했습니다.\n";
                    movedStr = str;


                    boardTemp = Controller.processMove(moveToken[0], moveToken[1], boardTemp, myWhite);

                    // 화면 clear
                    System.out.println("\033[H\033[2J");
                    System.out.flush();


                    boardTemp.printBoard();  ///추가한 부분
                    System.out.print(movedStr);
                    System.out.print(hintStr);

                    myWhite = !myWhite;
                    moveCount++;
                    turn--;
                    continue;
                }
                else if(Arrays.asList(Arrays.copyOfRange(validCommands, 18, 24)).contains(input)) {
                    System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                    String yn = scanner.nextLine();
                    yn.trim();
                    if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                            yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                            yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                            yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                            yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                        // 자신의 인덱스+1 = 파일 리스트의 사이즈 -> 다음파일 없다
                        String[] nameToken = myFileName.split("/");
                        int fileIndex = fName.indexOf(nameToken[1]);
                        if(fileIndex+1 == fName.size()) {
                            System.out.println("다음 퍼즐이 존재하지 않습니다.");
                        } else {
                            return fName.get(fileIndex+1);
                        }
                    } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                            yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                            yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                            yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                }
                else if(Arrays.asList(Arrays.copyOfRange(validCommands, 24, 30)).contains(input)) {
                    System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                    String yn = scanner.nextLine();
                    yn.trim();
                    if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                            yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                            yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                            yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                            yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                        // 현재 인덱스가 0이면 이전파일 없다
                        String[] nameToken = myFileName.split("/");
                        int fileIndex = fName.indexOf(nameToken[1]);
                        if(fileIndex == 0) {
                            System.out.println("이전 퍼즐이 존재하지 않습니다.");
                        } else {
                            return fName.get(fileIndex-1);
                        }
                    } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                            yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                            yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                            yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                }
                else if(Arrays.asList(Arrays.copyOfRange(validCommands, 30, 36)).contains(input)) {
                    System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                    String yn = scanner.nextLine();
                    yn.trim();
                    if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                            yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                            yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                            yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                            yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                        return ""; // 그냥 종료
                    } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                            yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                            yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                            yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                }
                else {
                    System.out.println("잘못된 입력입니다."); // 인식할 수 없는 입력인 경우
                }



            }
        }
        // 화면 clear
        System.out.println("\033[H\033[2J");
        System.out.flush();


        boardTemp.printBoard();
        System.out.print(movedStr);
        System.out.print(hintStr);
        System.out.println("축하합니다! 퍼즐을 푸는 것에 성공했습니다.");


        boolean flag = true;
        /////////////////////// 현재 유저의 id를 이 퍼즐의 클리어한 사람들 이름 리스트에 추가(텍스트 파일 수정)
        //파일 읽어오기
        Scanner sc = new Scanner(new File(myFileName));
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNextLine())
        {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        sc.close();
        String oldLine = "";
        for(int i=0;i<myPuzzle.cleared.size();i++)
        {
            if(i==myPuzzle.cleared.size()-1)
                oldLine+=myPuzzle.cleared.get(i);
            else
                oldLine+=myPuzzle.cleared.get(i)+" ";
        }
        String newLine;
        if(myPuzzle.cleared.size()==0)
        {
            oldLine += "★";
            newLine = Main.userID;
        }
        else
            newLine =oldLine + " " + Main.userID;
        //새로운 클리어 아이디 목록과 예전것과 바꾸기
        fileContents = fileContents.replaceAll(oldLine, newLine);
        FileWriter writer;
        try
        {
            writer = new FileWriter(myFileName);
            PrintWriter pw = new PrintWriter(myFileName);
            //파일에 있는 내용 모두 삭제
            pw.print("");
            pw.close();
            //새로운 클리어 아이디 목록을 포함하는 내용으로 새로 작성
            writer.append(fileContents);
            writer.flush();
        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        /////////////// 입력 예외처리 세부사항 작성(3)
        while(flag) {

            try {
                Thread.sleep(2000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            // 화면 clear
            System.out.println("\033[H\033[2J");
            System.out.flush();

            System.out.println("명령어의 종류: replay, hint(선택불가), solution(선택불가), next, previous, exit");
            System.out.println("위의 명령어 중 하나를 입력해주세요.");
            System.out.print("\t>_");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            input.trim();
            input = input.replaceAll("\\s+","").toLowerCase(); // 추가함
            input = input.replace(" ", ""); // 추가함
            //유효한 커맨드 배열에 사용자가 입력한 커맨드가 존재하는지 확인
            while(!Arrays.asList(validCommands).contains(input))
            {
                System.err.println("잘못된 입력입니다");
                System.out.println("명령어의 종류: replay, hint, solution, next, previous, exit (1~6)");
                System.out.println("위의 명령어 중 하나를 입력해주세요.");
                System.out.print("\t>_");
                input = scanner.nextLine();
                input.trim().replaceAll("\\s+","").toLowerCase();
                input = input.replace(" ", ""); // 추가함
            }
            if(Arrays.asList(Arrays.copyOfRange(validCommands, 0, 6)).contains(input)) {
                String[] nameToken = myFileName.split("/");
                return nameToken[1];

            } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 6, 12)).contains(input)) {
                System.out.println("현재 상태에서 수행할 수 없는 명령어입니다.");
            } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 12, 18)).contains(input)) {
                System.out.println("현재 상태에서 수행할 수 없는 명령어입니다.");
            } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 18, 24)).contains(input)) {
                System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                String yn = scanner.nextLine();
                yn.trim();
                if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                        yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                        yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                        yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                        yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                    // 자신의 인덱스+1 = 파일 리스트의 사이즈 -> 다음파일 없다
                    String[] nameToken = myFileName.split("/");
                    int fileIndex = fName.indexOf(nameToken[1]);
                    if(fileIndex+1 == fName.size()) {
                        System.out.println("다음 퍼즐이 존재하지 않습니다.");
                    } else {
                        return fName.get(fileIndex+1);
                    }
                } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                        yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                        yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                        yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                } else {
                    System.out.println("잘못된 입력입니다.");
                }
            } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 24, 30)).contains(input)) {
                System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                String yn = scanner.nextLine();
                yn.trim();
                if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                        yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                        yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                        yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                        yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                    // 현재 인덱스가 0이면 이전파일 없다
                    String[] nameToken = myFileName.split("/");
                    int fileIndex = fName.indexOf(nameToken[1]);
                    if(fileIndex == 0) {
                        System.out.println("이전 퍼즐이 존재하지 않습니다.");
                    } else {
                        return fName.get(fileIndex-1);
                    }
                } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                        yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                        yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                        yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

                } else {
                    System.out.println("잘못된 입력입니다.");
                }
            } else if(Arrays.asList(Arrays.copyOfRange(validCommands, 30, 36)).contains(input)) {
                System.out.println("현재 퍼즐의 진행상황은 저장되지 않습니다. 계속하시겠습니까? (Y/N)");
                String yn = scanner.nextLine();
                yn.trim();
                if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("yes") ||
                        yn.equalsIgnoreCase("예") || yn.equalsIgnoreCase("ㅇ") ||
                        yn.equalsIgnoreCase("ㅇㅇ") || yn.equalsIgnoreCase("ㅇㅋ") ||
                        yn.equalsIgnoreCase("ㄱ") || yn.equalsIgnoreCase("oui") ||
                        yn.equalsIgnoreCase("ja") || yn.equalsIgnoreCase("si")) {
                    return ""; // 그냥 종료
                } else if(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("no") ||
                        yn.equalsIgnoreCase("아니요") || yn.equalsIgnoreCase("ㄴ") ||
                        yn.equalsIgnoreCase("ㄴㄴ") || yn.equalsIgnoreCase("Non") ||
                        yn.equalsIgnoreCase("Nein") || yn.equalsIgnoreCase("Nope")){

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
        //nb.lastPieceMoved = b.lastPieceMoved;
        nb.lastPieceMoved = copyGamepiece(b.lastPieceMoved);      // 깊은 복사 코드 수정한 부분
        nb.blackPieceList = new ArrayList<String>();
        for(int i=0; i<b.blackPieceList.size(); i++) {
            //nb.blackPieceList.add(b.blackPieceList.get(i));
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