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
		if((a != 0 &&b==0)||(a==0 && b!=0)) { // ���� �̵�.
			if(a<0 && b==0) {
				for(int i=0;i<-a;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. --,__
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
			}else if(a>0 && b==0) {
				for(int i=0;i<a;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. ++,__
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
			}else if(a==0 && b<0) {
				for(int i=0;i<-b;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. __,--
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
			}else if(a==0&&b>0) {
				for(int i=0;i<b;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. __,++
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
			}
			else {
				if(a==0&& b==0) {
					System.out.println("���ڸ����� �̵��� �Ұ��� �մϴ�.");
				}
				else {
					super.move(row, col);
				}
			}
		}
		else {
			System.out.println("������ ���.");
		}
	}
}
