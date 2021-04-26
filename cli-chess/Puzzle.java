public class Puzzle {
    private String userName;
    private String name;
    private int theme;
    private String playdata;
    private boolean hasBeenSolved;
    private Board puzzleBoard;

    @Override
    public String toString() {
        return "Puzzle{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", theme=" + theme +
                ", hasBeenSolved=" + hasBeenSolved +
                '}';
    }

    public Puzzle(String userName, String name, int theme) {
        this.userName = userName;
        this.name = name;
        this.theme = theme;
        this.playdata = null;
        this.hasBeenSolved = false;
        this.puzzleBoard = new Board();
    }

    public void setPuzzleBoard(Board puzzleBoard) {
        this.puzzleBoard = puzzleBoard;
    }

    public Puzzle() {
        this.userName = null;
        this.name = null;
        this.theme = 0;
        this.playdata = null;
        this.hasBeenSolved = false;
        this.puzzleBoard = new Board();
    }
    public String getPlaydata() {
        return playdata;
    }

    public void setPlaydata(String playdata) {
        this.playdata = playdata;
    }

    public boolean isHasBeenSolved() {
        return hasBeenSolved;
    }

    public void setHasBeenSolved(boolean hasBeenSolved) {
        this.hasBeenSolved = hasBeenSolved;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public int getTheme() {
        return theme;
    }

    public Board getPuzzleBoard() {
        return puzzleBoard;
    }

}
