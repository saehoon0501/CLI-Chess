
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Menu {
   Scanner m_scanner=new Scanner(System.in);
   //public static String id=null;

   int loginMenu() throws IOException {   //로그인메뉴
      int l_index=1;
      while(l_index!=0) {
         System.out.print("이름을 입력해 주세요>");
         String input=m_scanner.nextLine();
         l_index=checkIInput(input);
         switch(l_index) {
            case 1: //루프 종료, 이후에 프로그램 종료
               l_index=0;
               break;
            case 2:   //이름 입력됨. 메인메뉴로 넘어감
               Main.userID=input;
               mainMenu();
               break;
            case 3:   //잘못된 이름. 에러 메시지 출력후 반복
               System.out.println("잘못된 이름 입력입니다.");
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
   int checkIInput(String input) {   //로그인메뉴에서의 사용자 입력이 문법 규칙에 맞는지 확인
      String c_Id=input.replaceAll("\\p{Z}", "");   //입력에서 모든 공백 제거
      if(c_Id.equalsIgnoreCase("Quit")||c_Id.equalsIgnoreCase("종료"))
      {
         return 1;
      }else if(c_Id.length()<2||input.length()>10)
      {
         return 3;
      }else if(c_Id.matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$"))   //이름의 문법규칙 확인
      {
         return 2;
      }
      return 3;
   }
   void mainMenu() throws IOException{   //메인메뉴
      int m_index=-1;
      while(m_index!=0){
         System.out.println("1.대전메뉴 2.퍼즐메뉴 3.로그아웃");
         String m_input=m_scanner.nextLine();
         m_index=checkMInput(m_input);
         switch(m_index) {
            case 1: //대전메뉴로 이동
               dualMenu();
               break;
            case 2:   //퍼즐메뉴로 이동
               puzzleMenu();
               break;
            case 3:   //메인메뉴로 이동
               m_index=0;
               break;
            default:
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
   int checkMInput(String input){   //메인메뉴에서의 사용자 입력이 문법규칙에 맞는지 확인
      String m_input=input.replaceAll("\\p{Z}", "");   //입력에서 모든 공백 제거
      if(m_input.equalsIgnoreCase("1")||m_input.equalsIgnoreCase("1.")||m_input.equalsIgnoreCase("대전모드")||m_input.equalsIgnoreCase("Dual"))
      {
         return 1;
      }else if(m_input.equalsIgnoreCase("2")||m_input.equalsIgnoreCase("2.")||m_input.equalsIgnoreCase("퍼즐모드")||m_input.equalsIgnoreCase("Puzzle"))
      {
         return 2;
      }else if(m_input.equalsIgnoreCase("3")||m_input.equalsIgnoreCase("3.")||m_input.equalsIgnoreCase("로그아웃")||m_input.equalsIgnoreCase("Logout"))
      {
         return 3;
      }else
      {
         return -1;
      }
   }
   void dualMenu() throws IOException {   //대전메뉴
      int d_index=-1;
      int dl_index=0;
      while(d_index!=0){
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
            case 2:   //Next 출력
               dl_index+=1;
               break;
            case 3:   //Back 출력
               dl_index-=1;
               break;
            case 4:   //메인메뉴로 복귀
               d_index=0;
               break;
            case 5:   //2 이상이고 선택 가능한 자연수를 입력
               d_input=d_input.replaceAll("\\p{Z}", "");   //입력에서 모든 공백 제거
               File dPath = new File("./DualMode");   //파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
               File temp[]=dPath.listFiles();
               ArrayList<File> dFileList = new ArrayList<File>();
               for(int i=0;i<temp.length;i++)
                  dFileList.add(temp[i]);
               for(int i=0;i<dFileList.size();i++)
               {
                  File f= dFileList.get(i);
                  if(f.getName().equalsIgnoreCase(".DS_Store"))
                     dFileList.remove(i);
               }
               int dfileIndex=Integer.parseInt(d_input)-2;
               String afileName=dFileList.get(dfileIndex).getName();
               DDual adual=new DDual(Main.userID, "./DualMode/"+afileName);
               adual.start();
               break;
            case 6:   //잘못된 입력
               break;
            default:
               break;
         }
      }
   }
   int checkDInput(String input, int dl_index) {   //대전메뉴에서의 사용자 입력이 문법 규칙에 맞는지 확인
      String d_input=input.replaceAll("\\p{Z}", "");   //입력에서 모든 공백 제거
      if(d_input.equalsIgnoreCase("1")||d_input.equalsIgnoreCase("1.")||d_input.equalsIgnoreCase("newgame"))
      {
         return 1;
      }else if(d_input.equalsIgnoreCase("Next")||d_input.equalsIgnoreCase("Nx")||d_input.equalsIgnoreCase("다음으로")||d_input.equalsIgnoreCase("다음페이지로이동"))
      {
         File dPath = new File("./DualMode");   //파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
         File temp[]=dPath.listFiles();
         ArrayList<File> dFileList = new ArrayList<File>();
         for(int i=0;i<temp.length;i++)
            dFileList.add(temp[i]);
         for(int i=0;i<dFileList.size();i++)
         {
            File f= dFileList.get(i);
            if(f.getName().equalsIgnoreCase(".DS_Store"))
               dFileList.remove(i);
         }
         if((dl_index+1)*14<=dFileList.size()) {
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
      }else if(d_input.equalsIgnoreCase("Back")||d_input.equalsIgnoreCase("Bc")||d_input.equalsIgnoreCase("이전으로")||d_input.equalsIgnoreCase("이전페이지로이동"))
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
      }else if(d_input.equalsIgnoreCase("Exit")||d_input.equalsIgnoreCase("Ex")||d_input.equalsIgnoreCase("종료")||d_input.equalsIgnoreCase("메뉴로 돌아가기"))
      {
         return 4;
      }else
      {
         File dPath = new File("./DualMode");   //파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
         File temp[]=dPath.listFiles();
         ArrayList<File> dFileList = new ArrayList<File>();
         for(int i=0;i<temp.length;i++)
            dFileList.add(temp[i]);
         for(int i=0;i<dFileList.size();i++)
         {
            File f= dFileList.get(i);
            if(f.getName().equalsIgnoreCase(".DS_Store"))
               dFileList.remove(i);
         }
         if(!d_input.matches("-?\\d+")) {     //입력이 정수인지 확인
            System.out.println("잘못된 입력입니다.");
            try {
               Thread.sleep(2000);
            }catch(InterruptedException e) {
               e.printStackTrace();
            }
            return 6;
         }
         int dfileIndex=Integer.parseInt(d_input)-2;
         if(d_input.matches("[+-]?\\d*(\\.\\d+)?")&&dfileIndex>=0&&dfileIndex<dFileList.size()) {   //입력이 2 이상의 선택 가능한 자연수일 경우
            String dFileName=dFileList.get(dfileIndex).getName();
            //String dWholeFileName="./DualMode/";
            //dWholeFileName+=dFileName;
            File t_file=new File("./DualMode/"+dFileName);
            FileReader filereader=null;
            try {
               filereader = new FileReader(t_file);
            } catch (FileNotFoundException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }   //입력 스트림 생성
            BufferedReader bufReader = new BufferedReader(filereader);    //입력 버퍼 생성
            String line1 = "";   //한줄씩 읽어와서 저장할 String변수
            String line2="";
            try {
               line1 = bufReader.readLine();
               line2=bufReader.readLine();
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }   //한줄을 읽어와서 line에 저장
            if(line1.equalsIgnoreCase(Main.userID)||line2.equalsIgnoreCase(Main.userID)||line2.equalsIgnoreCase("_")){   //플레이어가 2명이고 그중에 한명이 자신이거나 2p가 없을경우
               return 5;
            }else{   //플레이어가 2명이고 그중에 자신이 없을경우
               System.out.println("해당 게임은 플레이 하실 수 없습니다.");
               try {
                  Thread.sleep(2000);
               }catch(InterruptedException e) {
                  e.printStackTrace();
               }
               return 6;
            }
         }
         else
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
   void printDualList(int index){   //대전메뉴 목록 출력
      File dPath = new File("./DualMode");
      File temp[]=dPath.listFiles();
      ArrayList<File> dFileList = new ArrayList<File>();
      for(int i=0;i<temp.length;i++)
         dFileList.add(temp[i]);
      for(int i=0;i<dFileList.size();i++)
      {
         File f= dFileList.get(i);
         if(f.getName().equalsIgnoreCase(".DS_Store"))
            dFileList.remove(i);
      }
      System.out.println("1.새 게임");
      if(dFileList!=null) {
         for(int i=index*14; i<index*14+14; i++) {
            if(i<dFileList.size()) {
               System.out.println((i+2)+""+dFileList.get(i));
            }else
            {
               System.out.println();
            }
         }
      }
      System.out.println("[Back, Next]");
      int indexNumb=1;
      int countNum=15;
      int filenumb=dFileList.size()+1;
      for(;filenumb>countNum;) {
         countNum+=15;
         indexNumb++;
      }
      System.out.print("(");
      System.out.print(index+1);
      System.out.print(")/(");
      System.out.print(indexNumb);
      System.out.print(")");
      System.out.println();
   }
   void puzzleMenu() {   //퍼즐메뉴
      PuzzleMain foo = new PuzzleMain();
      try {
         foo.start();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   int checkPInput(String input, int pl_index) {   //퍼즐메뉴에서의 사용자 입력이 문법 규칙에 맞는지 확인
      String p_input=input.replaceAll("\\p{Z}", "");   //입력에서 모든 공백 제거
      if(p_input.equalsIgnoreCase("1")||p_input.equalsIgnoreCase("1.")||p_input.equalsIgnoreCase("makepuzzle"))
      {
         return 1;
      }else if(p_input.equalsIgnoreCase("Next")||p_input.equalsIgnoreCase("Nx")||p_input.equalsIgnoreCase("다음으로")||p_input.equalsIgnoreCase("다음페이지로이동"))
      {
         File pPath = new File("./PuzzleMode");   //파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
         File temp[]=pPath.listFiles();
         ArrayList<File> pFileList = new ArrayList<File>();
         for(int i=0;i<temp.length;i++)
            pFileList.add(temp[i]);
         for(int i=0;i<pFileList.size();i++)
         {
            File f= pFileList.get(i);
            if(f.getName().equalsIgnoreCase(".DS_Store"))
               pFileList.remove(i);
         }
         if((pl_index+1)*14<=pFileList.size()) {
            return 2;
         }else {
            System.out.println("해당 번호에 대전 파일이 존재하지 않습니다.");
            return 6;
         }
      }else if(p_input.equalsIgnoreCase("Back")||p_input.equalsIgnoreCase("Bc")||p_input.equalsIgnoreCase("이전으로")||p_input.equalsIgnoreCase("이전페이지로이동"))
      {
         if((pl_index-1)>=0) {
            return 3;
         }else {
            System.out.println("이전 페이지가 존재하지 않습니다.");
            return 6;
         }
      }else if(p_input.equalsIgnoreCase("Exit")||p_input.equalsIgnoreCase("Ex")||p_input.equalsIgnoreCase("종료")||p_input.equalsIgnoreCase("메뉴로 돌아가기"))
      {
         return 4;
      }else
      {
         File dPath = new File("./PuzzleMode");   //파일리스트 다시 받아서 입력한게 존재하는 세이브 파일을 가르키는지 확인
         File temp[]=dPath.listFiles();
         ArrayList<File> pFileList = new ArrayList<File>();
         for(int i=0;i<temp.length;i++)
            pFileList.add(temp[i]);
         for(int i=0;i<pFileList.size();i++)
         {
            File f= pFileList.get(i);
            if(f.getName().equalsIgnoreCase(".DS_Store"))
               pFileList.remove(i);
         }
         if(p_input.matches("[+-]?\\d*(\\.\\d+)?")&&Integer.parseInt(p_input)>=2&&Integer.parseInt(p_input)-2<pFileList.size()) {
            return 5;
         }else {
            System.out.println("잘못된 입력입니다");
            return 6;
         }
      }
   }
   void printPuzzleList(int index){   //퍼즐메뉴 목록 출력
      File pPath = new File("./PuzzleMode");
      File temp[]=pPath.listFiles();
      ArrayList<File> pFileList = new ArrayList<File>();
      for(int i=0;i<temp.length;i++)
         pFileList.add(temp[i]);
      for(int i=0;i<pFileList.size();i++)
      {
         File f= pFileList.get(i);
         if(f.getName().equalsIgnoreCase(".DS_Store"))
            pFileList.remove(i);
      }
      System.out.println("1.새 퍼즐 생성");
      if(pFileList!=null) {
         for(int i=index*14; i<index*14+14; i++) {
            if(i<pFileList.size()) {
               System.out.println((i+2)+""+pFileList.get(i));
            }else{
               System.out.println();
            }
         }
      }
      System.out.println("[Back, Next]");
   }

   String createNewDual()
   {
      //새 대전파일을 생성해서 저장하기
      File dPath = new File("./DualMode");
      File temp[]=dPath.listFiles();
      ArrayList<File> dFileList = new ArrayList<File>();
      for(int i=0;i<temp.length;i++)
         dFileList.add(temp[i]);
      for(int i=0;i<dFileList.size();i++)
      {
         File f= dFileList.get(i);
         if(f.getName().equalsIgnoreCase(".DS_Store"))
            dFileList.remove(i);
      }
      String fileName="./DualMode/";   //파일이 저장되는 경로.
      Random rnd=new Random();
      int rand=rnd.nextInt(10000)+1;
      Boolean check=true;
      if(dFileList.size()==0) {
         check=false;
      }
      while(check) {
         for(int i=0; i<dFileList.size(); i++) {
            String dFileName=dFileList.get(i).getName();
            String[] dFileNameSplit=dFileName.split("_");
            rand=rnd.nextInt(10000)+1;
            if(Integer.parseInt(dFileNameSplit[0])!=rand) {
               check=false;
            }
         }
      }
      fileName+=rand;
      fileName+="_.txt";
      try{
         BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));

         bufferedWriter.write(Main.userID); //한줄 입력
         bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
         bufferedWriter.write("_"); //상대 유져(파일 최초생성떄는 _로 적어놓음)
//            bufferedWriter.newLine();//개행문자쓰기(엔터키 입력)
//            bufferedWriter.write("null,null,1"); //움직임, 턴


         bufferedWriter.close();
      }catch(Exception e){
         e.printStackTrace();
      }
      return fileName;
   }
}