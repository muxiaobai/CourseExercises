package awt.huaji;

import java.awt.Image;
import java.util.ArrayList;

public class ball {
	double x;//��ĺ����
	double y;//���������
	ArrayList<Image> img;//�����ͼ
	double l;//��İ뾶
	double nowimg; //��ĵ�ǰ��ͼ����
	public ball(double x, double y, ArrayList<Image> img,double l,double nowimg) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.l=l;
		this.nowimg=nowimg;
	}
}
