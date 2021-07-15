
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	//임의의 사용자 아이디 값
	public static String userID = "Sehoon";
    public static void main(String[] args) throws IOException 
    {
    	File file=new File("./testdd.txt");	//폴더생성
		File file2=new File("./testdd.txt");
		if(file.exists()) {
			file.delete();
		}
    	Menu menu=new Menu();
    	menu.loginMenu();
    }
}