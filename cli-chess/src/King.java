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
					if(true) { //���� getrow,7�� ��ġ�� ���� �ְ� �� ���� movecount==0�̶��.
						if(true) {// ŷ�� �� ���̿� � �⹰�� �������� �ʴ´ٸ�.
							// ĳ���� ����.
						}
					}
				}
			}
			else {
				System.out.println("ŷ �ֺ� ��ĭ���� �̵� ����.");
			}
			
			
		}else if(a==0 &&b==0){
			System.out.println("���ڸ� �̵��� �Ұ���.");
		}else {
			if(true) {// ��� ŷ���� �Ÿ��� ������ �̵� �Ұ�.
				System.out.println("��� ŷ���� �Ÿ��� �����.");
			}
			else if(true) { // �ش� �ڸ��� üũ�� ���.
				System.out.println("üũ �ڸ��̹Ƿ� �̵��� �� ����.");
			}else {
				super.move(row, col);
			}
		}
	}
}
