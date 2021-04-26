package chess;

public class WPlayer {
	chesspiece[] pieces = new chesspiece[16];
	public void start() {
		for(int i=0;i<8;i++) {
			pieces[i]=new Wpawn(i,6); 
		}
		pieces[8]=new Rook(7,0);
		pieces[9]= new Rook(7,7);
		pieces[10]= new Knight(7,1);
		pieces[11]= new Knight(7,6);
		pieces[12]= new Bishop(7,2);
		pieces[13]= new Bishop(7,5);
		pieces[14]= new Queen(7,3);
		pieces[15]= new King(7,4);
	}
}
