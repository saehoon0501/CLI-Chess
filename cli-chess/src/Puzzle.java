import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle extends Board{
    private String userName;
    private String name;
    private int theme;
    private String playdata;
    private boolean hasBeenSolved;

	private Board puzzleBoard = new Board();

    public Puzzle(String userName, String name, int theme) {
        this.userName = userName;
        this.name = name;
        this.theme = theme;
    }
    public Puzzle(String fileName)
    {
		int numLine = 0;
		try 
		{
		    BufferedReader in = new BufferedReader(new FileReader(fileName));
		    String str;
		    while ((str = in.readLine()) != null) 
			{
		    	if(numLine==0)
		    	{
		    		//공백제거
					this.userName= str.trim().replaceAll("\\s+","");
					numLine++;
		    	}
		    	else if(numLine==1)
		    	{
					this.name = str.trim();
					numLine++;
		    	}
		    	else if(numLine==2)
		    	{
		    		this.theme = Integer.parseInt(str.trim());
		    		numLine++;
		    	}
		    	else if(numLine==3)
		    	{
		    		//playdata의 기능???
		    		numLine++;
		    	}
		    	else if(numLine==4)
		    	{
		    		this.hasBeenSolved = str.trim().toLowerCase().equals("true");
		    		numLine++;
		    	}
		    	else if(numLine>=5)
		    	{
		    		//퍼즐 파일 읽어오기
		    		numLine++;
		    	}
			}
		    in.close();
		} catch (IOException e) {
			System.err.println("File not found!");
		}
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
	public void setPuzzleBoard(Board puzzleBoard) {
		this.puzzleBoard = puzzleBoard;
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
