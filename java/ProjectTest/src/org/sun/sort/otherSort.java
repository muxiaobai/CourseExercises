/**
 * Project Name:ProjectTest
 * File Name:otherSort.java
 * Package Name:org.sun.sort
 * Date:2017年9月18日上午10:10:33
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.sun.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:otherSort <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月18日 上午10:10:33 <br/>
 * @author   Mu Xiaobai
 * @version  归并 基数/桶  计数
 * @since    JDK 1.8
 * @see 	 
 */
public class otherSort {
    public static void main(String[] args) {
//        mergeSort();
        //非比较
        radixSort();//基数
//        bucketSort();//桶
//        countSort();//计数
    }
    /**
     * 
     * mergeSort:(归并). 稳定
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    public static void mergeSort(){
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        mergeSort(a,0,a.length-1);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
    private static void mergeSort(int[] a, int left, int right) {
        if(left<right){
            int middle = (left+right)/2;
            //对左边进行递归
            mergeSort(a, left, middle);
            //对右边进行递归
            mergeSort(a, middle+1, right);
            //合并
            merge(a,left,middle,right);
        }
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int[] tmpArr = new int[a.length];
        int mid = middle+1; //右边的起始位置
        int tmp = left;
        int third = left;
        while(left<=middle && mid<=right){
            //从两个数组中选取较小的数放入中间数组
            if(a[left]<=a[mid]){
                tmpArr[third++] = a[left++];
            }else{
                tmpArr[third++] = a[mid++];
            }
        }
        //将剩余的部分放入中间数组
        while(left<=middle){
            tmpArr[third++] = a[left++];
        }
        while(mid<=right){
            tmpArr[third++] = a[mid++];
        }
        //将中间数组复制回原数组
        while(tmp<=right){
            a[tmp] = tmpArr[tmp++];
        }
    }
    /**
     * 
     * sort:(radixSort稳定). 基数 根据位数来排序
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    private static void radixSort() {
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        
        //找到最大数，确定要排序几趟
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if(max<a[i]){
                max = a[i];
            }
        }
        //判断位数
        int times = 0;
        while(max>0){
            max = max/10;
            times++;
        }
        //建立十个队列
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList queue1 = new ArrayList();
            queue.add(queue1);
        }
        //进行times次分配和收集
        for (int i = 0; i < times; i++) {
            //分配
            for (int j = 0; j < a.length; j++) {
                int x = a[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10, i);
                ArrayList queue2 = queue.get(x);
                queue2.add(a[j]);
                queue.set(x,queue2);
            }
            //收集
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while(queue.get(j).size()>0){
                    ArrayList<Integer> queue3 = queue.get(j);
                    a[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
            System.out.println();
            System.out.println("循环次数："+(i+1));
            for (int k = 0; k < a.length; k++) {
                System.out.print(a[k]+" ");
            }
        }
        
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
    /**
     * 
     * bucketSort:(桶排序).
     * @author Mu Xiaobai
     * @since JDK 1.8
     */
    private static void bucketSort() {
        
    }
}

