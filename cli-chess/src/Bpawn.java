package chess;

public class Bpawn extends chesspiece {
	
	public Bpawn(int row, int col) {
		this.setRow(row);
		this.setCol(col);
		this.setName("Bpawn");
	}
	
	public void move(int row, int col) {
		// 1.흑 폰 입장에서의 기본적 움직임 조건
		// 2. 기본 조건 만족하지만 놓을 곳이 판 밖인 경우.
		// 3. 기본 조건 만족하지만 움직인 칸에 기물이 있는 경우 우리측 기물인지 상대측 기물인지 확인하는 작업.
		// 만약 우리측이다-> 해당칸으로 움직일 수 없음을 표시. 반대로 상대 측이다-> 잡고 해당칸으로 이동.
		//4. row와 col 의 위치로 조건을 만들어 프로모션 처리.
		//5.상대측 진영의 3열로 이동한 경우 만약 4열에 상대 폰이 있다면 그 폰의 위치 정보와 movecount 정보를 받아와 앙파상 조건에 맞는지 확인.
		//6. movecount++ 해주기.
		int a=this.getRow()-row;
		int b= this.getCol()-col;
		if(row<0 || row>7 ||col < 0 || col>7) { // 판 벗어났는지확인.
			System.out.println("해당 위치로눈 움직일 수 없습니다.");
		}
		else {
			if(a==1 && b==0) {
				if(true) { // 해당 칸이 null이라면 
					this.setCol(col);
					this.setRow(row);
				}else {
					System.out.println("해당 칸이 다른 기물이 존재함.");
				}
			}else if((b==1||b==-1)&&a==1) {
				if(true) {//위치에 상대 기물이 있다면 잡고 이동. 
					this.setRow(row);
					this.setCol(col);
					// 상대 기물 삭제.
				}
				else {
					if(col==5 ) {//그리고 4열에에 상대 폰이 존재히고 이 폰의 movecount==1이라면 상대 폰을 잡음 (앙파상 조건.)
						
					}
					System.out.println("해당 칸이 비었거나 우리측 기물이 존재함.");
				}
			}
			else if(a==2 && b==0) {
				if(this.getmovecount()==0) {
					this.setRow(row);
					this.setCol(col);
				}
				else {
					System.out.println("초기 위치에 존쟈하지 않아 불가능.");
				}	
		}
		}
		
	}
	
	public void promotion(Wpawn wpa) {
		//pawn을 다른 기뮬로 바꿈. 1. 아예 다른 객체를 불러와서 해당 칸에 입히기. 2. 폰의 기본 속성 재정의 해서 마치 다른 말인것처럼 행세하기.
	}
	
	public void angpa(Bpawn bpa) {
		// bpa의 정보를 가져와 조건 확인 하기.
	}
	
}
