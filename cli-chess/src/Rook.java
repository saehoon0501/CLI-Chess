package chess;

public class Rook extends chesspiece{
	public Rook(int row,int col) {
		this.setCol(col);
		this.setRow(row);
		this.setName("Rook");
	}
	
	public void move(int row, int col) {
		int a=this.getRow()-row;
		int b= this.getCol()-col;
		if((a != 0 &&b==0)||(a==0 && b!=0)) { // 직선 이동.
			if(a<0 && b==0) {
				for(int i=0;i<-a;i++) {
					if(true) { // 판에서 해당 지점까지 값을 더해주며 중간에 마주치는것이 없는지 확인. --,__
						
					}else {
						System.out.println("중간에 다른 기물이 존재합니다.");
					}
				}
			}else if(a>0 && b==0) {
				for(int i=0;i<a;i++) {
					if(true) { // 판에서 해당 지점까지 값을 더해주며 중간에 마주치는것이 없는지 확인. ++,__
						
					}else {
						System.out.println("중간에 다른 기물이 존재합니다.");
					}
				}
			}else if(a==0 && b<0) {
				for(int i=0;i<-b;i++) {
					if(true) { // 판에서 해당 지점까지 값을 더해주며 중간에 마주치는것이 없는지 확인. __,--
						
					}else {
						System.out.println("중간에 다른 기물이 존재합니다.");
					}
				}
			}else if(a==0&&b>0) {
				for(int i=0;i<b;i++) {
					if(true) { // 판에서 해당 지점까지 값을 더해주며 중간에 마주치는것이 없는지 확인. __,++
						
					}else {
						System.out.println("중간에 다른 기물이 존재합니다.");
					}
				}
			}
			else {
				if(a==0&& b==0) {
					System.out.println("제자리로의 이동은 불가능 합니다.");
				}
				else {
					super.move(row, col);
				}
			}
		}
		else {
			System.out.println("움직을 벗어남.");
		}
	}
}
