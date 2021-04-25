import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Puzzle extends Board{
    private String userName;
    private String name;
    private int theme;
    private String playdata;
    private boolean hasBeenSolved;
	private Board puzzleBoard = new Board();
	public ArrayList<String> cleared = new ArrayList<String>();

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
		    		this.playdata = str.trim();
		    		numLine++;
		    	}
		    	else if(numLine==4)
		    	{
		    		String id="";
		    		for(int i=0;i<str.length();i++)
		    		{
		    			if(str.substring(i,i+1).equals(" "))
		    			{
		    				cleared.add(id);
		    				System.out.println(id);
		    				id="";
		    				i++;
		    			}
		    			else if(i==str.length()-1)
		    			{
		    				id+=str.substring(i,i+1);
		    				cleared.add(id);
		    				System.out.println(id);
		    			}
		    			id+=str.substring(i,i+1);
		    		}
		    		numLine++;
		    	}
		    	else if(numLine ==5)
		    	{
		    		Gamepiece temp = new Gamepiece();
		    		temp.setPiece(str.substring(0,1));
		    		temp.setPosition(str.substring(2,4));
		    		temp.setPlayer(str.substring(5,6));
		    		temp.setMoveCount(Integer.parseInt(str.substring(7,8)));
		    		puzzleBoard.lastPieceMoved= temp;
		    		numLine++;
		    	}
		    	else if(numLine>=6)
		    	{
		    		if(str.equals("null"))
		    		{
		    			puzzleBoard.GBoard[(numLine-5)/8][(numLine-5)%8]=null;
		    			numLine++;
		    		}
		    		else
		    		{
			    		Gamepiece gp = new Gamepiece();
			    		gp.setPiece(str.substring(0,1));
			    		gp.setPosition(str.substring(2,4));
			    		gp.setPlayer(str.substring(5,6));
			    		gp.setMoveCount(Integer.parseInt(str.substring(7,8)));
			    		puzzleBoard.GBoard[(numLine-6)/8][(numLine-6)%8]=gp;
			    		numLine++;
		    		}
		    	}
			}
		    in.close();
		} catch (IOException e) {
			System.err.println("File not found!");
		}
		for(int i=0;i<puzzleBoard.GBoard.length;i++)
		{
			for(int j=0;j<puzzleBoard.GBoard[0].length;j++)
			{
				if(puzzleBoard.GBoard[i][j]!=null)
					puzzleBoard.GBoard[i][j].createMoveSet(puzzleBoard);
			}
		}
		puzzleBoard.makePieceList();
		puzzleBoard.printBoard();
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
