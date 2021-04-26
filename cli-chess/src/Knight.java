package chess;

public class Knight extends chesspiece{
	public Knight(int row,int col) {
		this.setRow(row);
		this.setCol(col);
		this.setName("Knight");
	}
	
	public void move(int row,int col) {
		int a=this.getRow()-row;
		int b= this.getCol()-col;
		if(((a==2||a==-2)&&(b==1||b==-1))||((a==1||a==-1)&&(b==2||b==-2))) {
			super.move(row, col);
		}
	}
}
