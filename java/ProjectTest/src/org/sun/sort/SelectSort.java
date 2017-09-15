/**
 * Project Name:ProjectTest
 * File Name:SelectSort.java
 * Package Name:org.sun.sort
 * Date:2017年9月11日下午5:19:32
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.sun.sort;
/**
 * ClassName:SelectSort <br/>
 * Function: TODO  选择排序 
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月11日 下午5:19:32 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class SelectSort {
    public static void main(String[] args) {
        SimpleSelectSort();
//        HeapSort();
    }
    /**
     * 
     * SimpleSelectSort:(简单选择排序).
     * 搞得不好的话要交换n次 好的话一次都不用交换
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    public static void SimpleSelectSort(){
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("简单选择排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        for (int i = 0; i < a.length; i++) {
            int n =i;//哪个位置上的数最小 索引
            int min =a[i];//最小位置上的那个数
            for (int j = i+1; j < a.length; j++) {
                if(a[j]<min){
                    min =a[j];
                    n =j;
                }
            }
            //找到要交换的那个最小数，和当前的i交换
            a[n] = a[i];
            a[i]= min;
            System.out.println("\nstep :"+i);
            for (int k = 0; k < a.length; k++) {
                System.out.print(a[k]+" ");
            }
        }
        System.out.println();
        System.out.println("简单选择排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
    /**
     * 
     * HeapSort:(堆排序).树
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    public static void HeapSort(){
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        
        
        
        
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}

