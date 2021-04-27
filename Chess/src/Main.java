import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	//임의의 사용자 아이디 값
	public static String userID = "Dong";
    public static void main(String[] args) {
    	Menu menu=new Menu();
    	menu.loginMenu();
    }
}