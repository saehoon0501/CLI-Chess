import java.util.Scanner;

public class PuzzleControl {
    private Puzzle puzzleFile;

    public PuzzleControl() {
        this.puzzleFile = new Puzzle();
    }

    public void start(){
        System.out.println("Choose Theme :");
        System.out.println("\tMateIn1");
        System.out.println("\tMateIn2");
        System.out.println("\tMateIn3");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == "1"){
            this.puzzleFile.setTheme(1);
        }
    }
}
