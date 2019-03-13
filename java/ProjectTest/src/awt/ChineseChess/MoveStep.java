/**
 * Project Name:ProjectTest
 * File Name:MoveStep.java
 * Package Name:awt.ChineseChess
 * Date:2019年3月13日下午3:00:44
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package awt.ChineseChess;
/**
 * ClassName:MoveStep 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午3:00:44 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

import java.awt.Point;
 
/**
 * 走步类
 * 
 * @author cnlht
 * 
 */
public class MoveStep implements java.io.Serializable {
    public Point pStart, pEnd;
 
    public MoveStep(Point p1, Point p2) {
        pStart = p1;
        pEnd = p2;
    }
}

