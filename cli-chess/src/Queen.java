package chess;

public class Queen extends chesspiece{
	public Queen(int row, int col) {
		this.setRow(row);
		this.setCol(col);
		this.setName("Queen");
	}
	
	public void move(int row,int col) {
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
		else if(a==b|| a==-b) { // �밢�� �̵�.
			if(a<0 &&b<0) {
				for(int i=0;i<-a;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. --,--
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
			}else if(a<0&&b>0){
				for(int i=0;i<-a;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. --,++
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
				
			}else if(a>0 && b<0) {
				for(int i=0;i<a;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. ++,--
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
				
			}else if(a>0 && b>0) {
				for(int i=0;i<a;i++) {
					if(true) { // �ǿ��� �ش� �������� ���� �����ָ� �߰��� ����ġ�°��� ������ Ȯ��. ++,++
						
					}else {
						System.out.println("�߰��� �ٸ� �⹰�� �����մϴ�.");
					}
				}
				
			}else {
				if(a==0&& b==0) {
					System.out.println("���ڸ����� �̵��� �Ұ��� �մϴ�.");
				}
				else {
					super.move(row, col);
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
}
