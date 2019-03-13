/**
 * Project Name:ProjectTest
 * File Name:Point.java
 * Package Name:awt.fiveChess
 * Date:2019年3月13日下午2:54:36
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package awt.fiveChess;
/**
 * ClassName:Point 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午2:54:36 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

import java.awt.Color;
/**
 * 棋子类
 */
public class Point {
  private int x;//棋盘中的x索引
  private int y;//棋盘中的y索引
  private Color color;//颜色
  public static final int DIAMETER=30;//直径
  
  public Point(int x,int y,Color color){
      this.x=x;
      this.y=y;
      this.color=color;
  } 
  
  public int getX(){//拿到棋盘中x的索引
      return x;
  }
  public int getY(){
      return y;
  }
  public Color getColor(){//获得棋子的颜色
      return color;
  }
}