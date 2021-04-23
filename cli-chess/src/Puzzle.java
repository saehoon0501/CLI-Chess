public class Puzzle extends Board{
    private String userName;
    private String name;
    private int theme;
    private String playdata;
    private boolean hasBeenSolved;
    private Board puzzleBoard;

    public Puzzle(String userName, String name, int theme) {
        this.userName = userName;
        this.name = name;
        this.theme = theme;
    }

    public Puzzle() {
        this.userName = null;
        this.name = null;
        this.theme = 0;
        this.playdata = null;
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

    @Override
    public String toString() {
        return "Puzzle{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }
}
