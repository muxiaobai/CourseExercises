package awt.tankwar;


import java.awt.Color;
import java.awt.Graphics;

public class Energy {
	int x,y; //�����������λ��
	int width = 20;//����������Ŀ��     �����������λ���� (x+5,y+5)
	boolean tag = true;
	public Energy(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g) {
		if(this.tag==true) {
			g.setColor(Color.green);
			g.fillOval(x, y, width, width);
		}
	}
	
	public void hit(Tank tank) {
		if((Math.pow(((this.x+5)-(tank.x+15)),2)+Math.pow(((this.y+5)-(tank.y+15)),2))<700) {
			if(tank.blood<40) {
				tank.blood=40;
				this.tag = false;
			}
		} 


	}
	
}
