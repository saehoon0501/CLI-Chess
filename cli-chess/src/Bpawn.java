package chess;

public class Bpawn extends chesspiece {
	
	public Bpawn(int row, int col) {
		this.setRow(row);
		this.setCol(col);
		this.setName("Bpawn");
	}
	
	public void move(int row, int col) {
		// 1.�� �� ���忡���� �⺻�� ������ ����
		// 2. �⺻ ���� ���������� ���� ���� �� ���� ���.
		// 3. �⺻ ���� ���������� ������ ĭ�� �⹰�� �ִ� ��� �츮�� �⹰���� ����� �⹰���� Ȯ���ϴ� �۾�.
		// ���� �츮���̴�-> �ش�ĭ���� ������ �� ������ ǥ��. �ݴ�� ��� ���̴�-> ��� �ش�ĭ���� �̵�.
		//4. row�� col �� ��ġ�� ������ ����� ���θ�� ó��.
		//5.����� ������ 3���� �̵��� ��� ���� 4���� ��� ���� �ִٸ� �� ���� ��ġ ������ movecount ������ �޾ƿ� ���Ļ� ���ǿ� �´��� Ȯ��.
		//6. movecount++ ���ֱ�.
		int a=this.getRow()-row;
		int b= this.getCol()-col;
		if(row<0 || row>7 ||col < 0 || col>7) { // �� �������Ȯ��.
			System.out.println("�ش� ��ġ�δ� ������ �� �����ϴ�.");
		}
		else {
			if(a==1 && b==0) {
				if(true) { // �ش� ĭ�� null�̶�� 
					this.setCol(col);
					this.setRow(row);
				}else {
					System.out.println("�ش� ĭ�� �ٸ� �⹰�� ������.");
				}
			}else if((b==1||b==-1)&&a==1) {
				if(true) {//��ġ�� ��� �⹰�� �ִٸ� ��� �̵�. 
					this.setRow(row);
					this.setCol(col);
					// ��� �⹰ ����.
				}
				else {
					if(col==5 ) {//�׸��� 4������ ��� ���� �������� �� ���� movecount==1�̶�� ��� ���� ���� (���Ļ� ����.)
						
					}
					System.out.println("�ش� ĭ�� ����ų� �츮�� �⹰�� ������.");
				}
			}
			else if(a==2 && b==0) {
				if(this.getmovecount()==0) {
					this.setRow(row);
					this.setCol(col);
				}
				else {
					System.out.println("�ʱ� ��ġ�� �������� �ʾ� �Ұ���.");
				}	
		}
		}
		
	}
	
	public void promotion(Wpawn wpa) {
		//pawn�� �ٸ� ��ķ� �ٲ�. 1. �ƿ� �ٸ� ��ü�� �ҷ��ͼ� �ش� ĭ�� ������. 2. ���� �⺻ �Ӽ� ������ �ؼ� ��ġ �ٸ� ���ΰ�ó�� �༼�ϱ�.
	}
	
	public void angpa(Bpawn bpa) {
		// bpa�� ������ ������ ���� Ȯ�� �ϱ�.
	}
	
}
