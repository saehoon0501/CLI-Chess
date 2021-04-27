import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Menu {
	Scanner m_scanner=new Scanner(System.in);
	//public static String id=null;
	void clearScreen(){	//화면 지움
		try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	int loginMenu() {	//로그인메뉴
    	int l_index=1;
    	while(l_index!=0) {
    	clearScreen();
    	System.out.print("이름을 입력해 주세요>");
    	String input=m_scanner.nextLine();
    	l_index=checkIInput(input);
    	switch(l_index) {
    		case 1: //루프 종료, 이후에 프로그램 종료
    			l_index=0;
    			break;
    		case 2:	//이름 입력됨. 메인메뉴로 넘어감
    			Main.userID=input;
    			mainMenu();
    			break;
    		case 3:	//잘못된 이름. 에러 메시지 출력후 반복
    			clearScreen();
    			System.out.println("잘못된 입력입니다.");
    			try {
    				Thread.sleep(2000);
    			}catch(InterruptedException e) {
    				e.printStackTrace();
    			}
    			break;
    		default:
    			break;
    		}
    	}
    	System.exit(l_index); //프로그램 종료
    	return 1;
    }
	int checkIInput(String input) {	//로그인메뉴에서의 사용자 입력이 문법 규칙에 맞는지 확인
	   String c_Id=input.replaceAll("\\p{Z}", "");	//입력에서 모든 공백 제거
	   if(c_Id.equals("Quit"))
	   {
		   return 1;
	   }else if(c_Id.length()<2||input.length()>10)
	   {
		   return 3;
	   }else if(c_Id.matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$"))	//이름의 문법규칙 확인
	   {
		   return 2;
	   }
	   return 3;
   }
	void mainMenu(){	//메인메뉴
	   int m_index=-1;
	   while(m_index!=0){
	   clearScreen();
	   System.out.println("1.대전메뉴 2.퍼즐메뉴 3.로그아웃");
	   String m_input=m_scanner.nextLine();
	   m_index=checkMInput(m_input);
	   switch(m_index) {
		case 1: //대전메뉴로 이동
			dualMenu();
			break;
		case 2:	//퍼즐메뉴로 이동
			puzzleMenu();
			break;
		case 3:	//메인메뉴로 이동
			m_index=0;
			break;
		default:
			clearScreen();
			System.out.println("잘못된 입력입니다.");
			try {
				Thread.sleep(2000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	   }
   }
	int checkMInput(String input){	//메인메뉴에서의 사용자 입력이 문법규칙에 맞는지 확인
	   String m_input=input.replaceAll("\\p{Z}", "");	//입력에서 모든 공백 제거
	   if(m_input.equals("1")||m_input.equals("1.")||m_input.equals("대전모드")||m_input.equals("Dual"))
	   {
		   return 1;
	   }else if(m_input.equals("2")||m_input.equals("2.")||m_input.equals("퍼즐모드")||m_input.equals("Puzzle"))
	   {
		   return 2;
	   }else if(m_input.equals("3")||m_input.equals("3.")||m_input.equals("로그아웃")||m_input.equals("Logout"))
	   {
		   return 3;
	   }else
	   {
		   return -1;
	   }
   }
	void dualMenu() {	//대전메뉴
		int d_index=-1;
		int dl_index=0;
		while(d_index!=0){
			clearScreen();
			printDualList(dl_index);
			String d_input=m_scanner.nextLine();
			d_index=checkDInput(d_input, dl_index);
			 switch(d_index) {
				case 1: //새 게임
					String fileName=createNewDual();
					DDual dual= new DDual(Main.userID, fileName);
					dual.start();
					d_index=0;
					break;
				case 2:	//Next 출력
					dl_index+=1;
					break;
				case 3:	//Back 출력
					dl_index-=1;
					break;
				case 4:	//메인메뉴로 복귀
					d_index=0;
					break;
				case 5:	//2 이상이고 선택 가능한 자연수를 입력
					d_input=d_input.replaceAll("\\p{Z}", "");	//입력에서 모든 공백 제거
					File dPath = new File("./DualMode");	//파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
					File dFileList[]=dPath.listFiles();
					int dfileIndex=Integer.parseInt(d_input)-2;
					String afileName=dFileList[dfileIndex].getName();
					DDual adual=new DDual(Main.userID, afileName);
					adual.start();
					break;
				case 6:	//잘못된 입력
					outer:
					break;
				default:
					break;
				}			
		}
	}
	int checkDInput(String input, int dl_index) {	//대전메뉴에서의 사용자 입력이 문법 규칙에 맞는지 확인
		   String d_input=input.replaceAll("\\p{Z}", "");	//입력에서 모든 공백 제거
		   if(d_input.equals("1")||d_input.equals("1.")||d_input.equals("newgame"))
		   {
			   return 1;
		   }else if(d_input.equals("Next")||d_input.equals("Nx")||d_input.equals("다음으로")||d_input.equals("다음페이지로이동"))
		   {
			   File dPath = new File("./DualMode");	//파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
			   File dFileList[]=dPath.listFiles();
			   if((dl_index+1)*14<=dFileList.length) {
				   return 2;
			   }else {
				   System.out.println("다음 페이지가 존재하지 않습니다.");
				   try {
						Thread.sleep(2000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				   return 6;
			   }
		   }else if(d_input.equals("Back")||d_input.equals("Bc")||d_input.equals("이전으로")||d_input.equals("이전페이지로이동"))
		   {
			   if((dl_index-1)>=0) {
				   return 3;
			   }else {
				   System.out.println("이전 페이지가 존재하지 않습니다.");
				   try {
						Thread.sleep(2000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				   return 6;
			   }
		   }else if(d_input.equals("Exit")||d_input.equals("Ex")||d_input.equals("종료")||d_input.equals("메뉴로 돌아가기"))
		   {
			   return 4;
		   }else
		   {
			   File dPath = new File("./DualMode");	//파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
			   File dFileList[]=dPath.listFiles();
			   int dfileIndex=Integer.parseInt(d_input)-2;
			   if(d_input.matches("[+-]?\\d*(\\.\\d+)?")&&dfileIndex>=0&&dfileIndex<dFileList.length) {	//입력이 2 이상의 선택 가능한 자연수일 경우
				   String dFileName=dFileList[dfileIndex].getName();
				   String[] dFileNameSplit=dFileName.split("_");
				   String dWholeFileName="./DualMode/";
				   dWholeFileName+=dFileName;
				   File t_file=new File("./DualMode/"+dFileName);
				   FileReader filereader=null;
				try {
					filereader = new FileReader(t_file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//입력 스트림 생성
				   BufferedReader bufReader = new BufferedReader(filereader);	 //입력 버퍼 생성
	               String line1 = "";	//한줄씩 읽어와서 저장할 String변수
	               String line2="";
	               try {
					line1 = bufReader.readLine();
					line2=bufReader.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//한줄을 읽어와서 line에 저장
				   if(dFileNameSplit[0].equals("2p를 기다리는중")) {	//플레이어 한명이 정해지지 않았을 경우
					   return 5;
				   }else if(line1.equals(Main.userID)||line2.equals(Main.userID)){	//플레이어가 2명이고 그중에 한명이 자신일 경우
					   return 5;
				   }else{	//플레이어가 2명이고 그중에 자신이 없을경우
					   System.out.println("해당 게임은 플레이 하실 수 없습니다.");
					   try {
							Thread.sleep(2000);
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					   return 6;
				   }
			   }else 
			   {
				   System.out.println("잘못된 입력입니다.");
				   try {
						Thread.sleep(2000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				   return 6;   
			   }
		   }
	   }
	void printDualList(int index){	//대전메뉴 목록 출력
		File dPath = new File("./DualMode");
		File dFileList[]=dPath.listFiles();
		System.out.println("1.새 게임");
		if(dFileList!=null) {
			for(int i=index*14; i<index*14+14; i++) {
				if(i<dFileList.length) {
				System.out.println((i+2)+""+dFileList[i]);
				}else
				{
					System.out.println();
				}
			}
		}
		System.out.println("[Back, Next]");
	}
	void puzzleMenu() {	//퍼즐메뉴
		//int p_index=-1;
		//int pl_index=0;
		//while(p_index!=0){
		clearScreen();
		PuzzleMain foo = new PuzzleMain();
	    try {
			foo.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//printPuzzleList(pl_index);
			//String p_input=m_scanner.nextLine();
			//p_index=checkPInput(p_input, pl_index);
			// switch(p_index) {
			//	case 1: //새 퍼즐 만들기
			//		//새로운 세이브 파일을 생성하고 객체 구현!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//		break;
			//	case 2:	//Next 출력
			//		pl_index+=1;
			//		break;
			//	case 3:	//Back 출력
			//		pl_index-=1;
			//		break;
			//	case 4:	//메인메뉴로 복귀
			//		p_index=0;
			//		break;
			//	case 5:	//2 이상이고 선택 가능한 자연수를 입력
			//		p_input=p_input.replaceAll("\\p{Z}", "");	//입력에서 모든 공백 제거
			//		//해당 파일을 읽어와 객체 생성및 구현(p_input에서 -2해야함, 클래스 내부에서 해당 이름으로 참가가 가능한지도 확인해야함)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//		break;
			//	case 6:	//잘못된 입력
			//		break;
			//	default:
			//		break;
			//	}			
		//}	
	}
	int checkPInput(String input, int pl_index) {	//퍼즐메뉴에서의 사용자 입력이 문법 규칙에 맞는지 확인
		   String p_input=input.replaceAll("\\p{Z}", "");	//입력에서 모든 공백 제거
		   if(p_input.equals("1")||p_input.equals("1.")||p_input.equals("makepuzzle"))
		   {
			   return 1;
		   }else if(p_input.equals("Next")||p_input.equals("Nx")||p_input.equals("다음으로")||p_input.equals("다음페이지로이동"))
		   {
			   File pPath = new File("./PuzzleMode");	//파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
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
			   File dPath = new File("./PuzzleMode");	//파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
			   File pFileList[]=dPath.listFiles();
			   if(p_input.matches("[+-]?\\d*(\\.\\d+)?")&&Integer.parseInt(p_input)>=2&&Integer.parseInt(p_input)-2<pFileList.length) {
				   return 5;
			   }else {
				   return 6;   
			   }
		   }
	   }
	void printPuzzleList(int index){	//퍼즐메뉴 목록 출력
		File pPath = new File("./PuzzleMode");
		File pFileList[]=pPath.listFiles();
		System.out.println("1.새 퍼즐 생성");
		if(pFileList!=null) {
			for(int i=index*14; i<index*14+14; i++) {
				if(i<pFileList.length) {
				System.out.println((i+2)+""+pFileList[i]);
				}else{
					System.out.println();
				}
			}
		}
		System.out.println("[Back, Next]");
	}
	
	String createNewDual() {	//새 대전파일을 생성해서 저장하기
		 File dPath = new File("./DualMode");
		 File dFileList[]=dPath.listFiles();
		 String fileName="./DualMode/";	//파일이 저장되는 경로.
		 Random rnd=new Random();
		 int rand=rnd.nextInt(10000)+1;
		 Boolean check=true;
		 if(dFileList.length==0) {
			 check=false;
		 }
		 while(check) {
			 for(int i=0; i<dFileList.length; i++) {
				 String dFileName=dFileList[i].getName();
				 String[] dFileNameSplit=dFileName.split("_");
				 rand=rnd.nextInt(10000)+1;
				 if(Integer.parseInt(dFileNameSplit[1])!=rand) {
					 check=false;
				 }
			 }
		 }
         fileName += "2p를 기다리는중_";	//실제 파일 이름을 추가
         fileName+=rand;
         fileName+="_.txt";
		try{           
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
            
            bufferedWriter.write(Main.userID); //한줄 입력
            bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
            bufferedWriter.write("_"); //상대 유져
            bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
            bufferedWriter.write("null,null,1"); //상대 유져
          //위에 2줄을 반복하며 모든 정보를 저장함
 
 
            bufferedWriter.close();            
        }catch(Exception e){
            e.printStackTrace();
        }
		return fileName;
	}
	
//	void changeFileName() {	//파일 이름 변경하기-사용 안함!
//		int index=0;	//후에 만들 FileList에서 어떤 파일이름을 변경할지 알기위한 색인, 보통 함수 인자로 전달받음
//		File path = new File("./DualMode");	//퍼즐파일의 경우 "./PuzzleMode"
//		File fileList[]=path.listFiles();	//index와 마찬가지로 함수 인자로 전달받을 수 있음. 아예 특정 file을 전달받을수도 있음.
//		String newFileName="./DualMode/";	//파일이 저장되는 경로, 퍼즐게임의 경우 "./PuzzleMode/"임
//		newFileName+="changedName.txt";	//변경할 이름
//		fileList[index].renameTo(new File(newFileName));
//	}
}
