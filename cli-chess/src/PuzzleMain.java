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
        	
        }
    }

    public void printPuzzles(ArrayList<Puzzle> puzzles){
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
}
