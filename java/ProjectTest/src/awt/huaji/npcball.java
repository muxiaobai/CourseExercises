package awt.huaji;

import java.awt.Image;
import java.util.ArrayList;

public class npcball extends ball implements Runnable{
	double v;//����ٶ�,ÿ1������ƶ��ľ���
	double d;//����˶��Ƕȣ���������˳ʱ�����
	double av;//���˥�����ٶȰٷֱ�
	double gv;//���¼��ٶ�
	double mg;//�������ٶ�
	double ef;//����
	double gva;//�������ٶȼ�����
	static double fn=1000000;//��������������Խ������ԽС
	static double kn=1;//��������
	static double G=300;//����������ԽС����Խ��
	int r_l;//��������˶�����,��Ϊ-1,��Ϊ1����ֱ����Ϊ0
	int d_u;//��������˶�������Ϊ-1����Ϊ1,��ֱ����Ϊ0
	static int room_width=MyFrame.width;//��Ļ��
	static int room_height=MyFrame.height;//��Ļ��
	static int k=10;//�˶�ˢ����
	double g;//����
	public npcball(int x,int y,ArrayList<Image> img, double l, double v,double d,int r_l,int d_u){
		super(x,y,img,l,Math.random());
		this.v=v;
		this.d=d;
		this.av=1-(l/fn);
		this.r_l=r_l;
		this.d_u=d_u;
		gva=1;
		g=(l/G)*(l/G);
		new Thread(this).start();
	}
	public void mmotioning()//kΪˢ��Ƶ�ʣ�Ϊ����ֵ
	{
		gv=(v*k/Math.sin(Math.PI/180*90)*Math.sin(Math.PI/180*(d)));
		ef=ef<(g/2)?0:ef;
		mg=((g>gv&&y>room_height-l/2-35)?gv:g)*gva;
		gva=mg>=g?y>room_height-l/2-35?1:gva+0.05:1;
		y=y+(d_u*(gv+((d_u==1||ef!=0)?mg:-mg))-ef);
		x=x+(r_l*(v*k/Math.sin(Math.PI/180*90)*Math.sin(Math.PI/180*(90-d))));
		v*=av;
		r_l=x<l/2?1:x>room_width-l/2-15?-1:r_l;
		d_u=y<l/2?1:y>room_height-l/2-35?-1:d_u;
		ef=y>room_height-l/2-35?gv*kn:0;
		nowimg=nowimg>=img.size()-0.8?0:nowimg+(Math.random()/100);
	}
	@Override
	public void run() {
		try {
			while(true)
			{
				this.mmotioning();
				Thread.sleep(k);
				if(v==0)
					return;
			}
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		// TODO �Զ����ɵķ������
		
	}
}
