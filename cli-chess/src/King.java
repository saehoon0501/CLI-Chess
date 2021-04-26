package chess;

public class King extends chesspiece{
	public King(int row, int col) {
		this.setCol(col);
		this.setRow(row);
		this.setName("King");
	}
	
	public void move(int row,int col) {
		int a= this.getRow()-row;
		int b= this.getCol()-col;
		if(a<-1||a>1||b<-1||b>1) {
			if(this.getmovecount()==0) {
				if((a==0 &&b==2)) {
					if(true) { //판의 getrow,7의 위치에 룩이 있고 그 룩의 movecount==0이라면.
						if(true) {// 킹과 룩 사이에 어떤 기물도 존재하지 않는다면.
							// 캐슬링 진행.
						}
					}
				}
			}
			else {
				System.out.println("킹 주변 한칸씩만 이동 가능.");
			}
			
			
		}else if(a==0 &&b==0){
			System.out.println("제자리 이동은 불가능.");
		}else {
			if(true) {// 상대 킹과의 거리가 인접시 이동 불가.
				System.out.println("상대 킹과의 거리가 가까움.");
			}
			else if(true) { // 해당 자리가 체크인 경우.
				System.out.println("체크 자리이므로 이동할 수 없음.");
			}else {
				super.move(row, col);
			}
		}
	}
}
