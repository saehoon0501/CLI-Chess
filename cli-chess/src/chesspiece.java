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
		//1. �⺻ ������ ����. 1) ü���� �ٱ����� ������ �� ����. 2)������ ĭ�� �ٸ� �⹰�� �����Ѵٸ� �ش� �⹰�� �츮�� �⹰���� ����� �⹰���� Ȯ���ϰ� �����ϼ� �ִ��� ������ �Ǵ�.
		if(row<0 || row>7 ||col < 0 || col>7) {
			System.out.println("�ش� ��ġ�δ� ������ �� �����ϴ�.");
		}
		else {
			if(true) { // �����̰��� �ϴ� ���� ��ġ�� null�̶��.
				//�ش� ��ġ�� �Ű���.
				this.row=row;
				this.col=col;
			}
			else if(false){ //null�� �ƴϰ� ��� �⹰�̶��.
				//�ش� ��ġ�� �ű�� ��� �⹰ ����.
				this.row=row;
				this.col=col;
				//�⹰ ����.
			}
			
			else { // null�� �ƴϰ� ��� �⹰�� �ƴ϶�� (�츮�� �⹰).
				System.out.println("�ش� ��ġ�δ� ������ �� �����ϴ�.");
			}
		}
	}
}
