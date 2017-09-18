/**
 * Project Name:ProjectTest
 * File Name:SwapSort.java
 * Package Name:org.sun.sort
 * Date:2017年9月11日下午5:00:14
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.sun.sort;
/**
 * ClassName:SwapSort <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月11日 下午5:00:14 <br/>
 * @author   Lenovo
 * @version   冒泡  快速排序
 * @since    JDK 1.8
 * @see 	 
 */
public class SwapSort {
    public static void main(String[] args) {
//        BubbleSort();
        QuickSort();
    }
    /**
     * 冒泡排序
     * BubbleSort:(快速排序).依次交换，小的往上跑，放到第i处，
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    public static void BubbleSort(){
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("冒泡排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if(a[j]<a[i]){
                    int temp =a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }else {
                    continue;
                }
            }
            System.out.println("\nstep :"+i);
            for (int k = 0; k < a.length; k++) {
                System.out.print(a[k]+" ");
            }
        }
        System.out.println();
        System.out.println("冒泡排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
    /**
     * 
     * QuickSort:(快速排序).
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    public static void QuickSort(){
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("快速排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        if(a.length>0){
            quickSort(a,0,a.length-1);
        }
        System.out.println();
        System.out.println("快速排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
    
    private static void quickSort(int[] a, int low, int high) {
        if(low<high){ //如果不加这个判断递归会无法退出导致堆栈溢出异常
            int middle = getMiddle(a,low,high);
            quickSort(a, 0, middle-1);
            quickSort(a, middle+1, high);
        }
    }

    private static int getMiddle(int[] a, int low, int high) {
        int temp = a[low];//基准元素
        while(low<high){
            //找到比基准元素小的元素位置
            while(low<high && a[high]>=temp){
                high--;
            }
            a[low] = a[high]; 
            while(low<high && a[low]<=temp){
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }
    
}

