import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleMain {
    private ArrayList<Puzzle> puzzles;

    public PuzzleMain() {
        this.puzzles = new ArrayList<Puzzle>(15);
    }

    public void start(){
        this.puzzles.add(new Puzzle("1", "A", 1));
        this.puzzles.add(new Puzzle("2", "B", 2));
        this.puzzles.add(new Puzzle("3", "C", 3));
        this.puzzles.add(new Puzzle("3", "C", 3));
        this.puzzles.add(new Puzzle("3", "C", 3));
        this.puzzles.add(new Puzzle("3", "C", 3));
        this.puzzles.add(new Puzzle("3", "C", 3));
        this.puzzles.add(new Puzzle("3", "C", 3));
        System.out.println("Welcome to Puzzle Mode !");
        System.out.println("Please Select Number in below list"+"\n");
        printPuzzles(this.puzzles);

        System.out.println("Select :");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input){
            case "1":
                PuzzleControl puzzleControl = new PuzzleControl();
                puzzleControl.start();
            case "2":

        }
    }

    public void printPuzzles(ArrayList<Puzzle> puzzles){
        System.out.println("1." + "PuzzleMaker");
        for (int i = 0; i < puzzles.size(); i++){
            System.out.println(i+2 + "." + puzzles.get(i));
        }
    }


}
