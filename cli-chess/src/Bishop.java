package chess;

public class Bishop extends chesspiece{

	public Bishop(int row, int col) {
		this.setRow(row);
		this.setCol(col);
		this.setName("Bishop");
	}
	
	public void move(int row, int col) {
		int a=this.getRow()-row;
		int b= this.getCol()-col;
		if(a==b|| a==-b) {
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
			System.out.println("������ ���.");
		}
	}

	
}
