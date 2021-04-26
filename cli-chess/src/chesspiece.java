package chess;

public class chesspiece {

	private String name;
	private int row;
	private int col;
	private int movecount=0;
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public void setName(String str) {
		this.name=str;
	}
	
	public int getmovecount() {
		return this.movecount;
	}
	public void move(int row, int col) {
		//1. 기본 움직임 설정. 1) 체스판 바깥으로 움직일 수 없음. 2)놓여질 칸에 다른 기물이 존재한다면 해당 기물이 우리측 기물인지 상대측 기물인지 확인하고 움직일수 있느냐 없느야 판단.
		if(row<0 || row>7 ||col < 0 || col>7) {
			System.out.println("해당 위치로눈 움직일 수 없습니다.");
		}
		else {
			if(true) { // 움직이고자 하는 판의 위치가 null이라면.
				//해당 위치로 옮겨줌.
				this.row=row;
				this.col=col;
			}
			else if(false){ //null이 아니고 상대 기물이라면.
				//해당 위치로 옮기고 상대 기물 제거.
				this.row=row;
				this.col=col;
				//기물 제거.
			}
			
			else { // null도 아니고 상대 기물도 아니라면 (우리측 기물).
				System.out.println("해당 위치로눈 움직일 수 없습니다.");
			}
		}
	}
}
