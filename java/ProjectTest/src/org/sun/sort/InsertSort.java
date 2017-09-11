/**
 * Project Name:ProjectTest
 * File Name:InsertSort.java
 * Package Name:org.sun.sort
 * Date:2017年9月11日上午10:58:58
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.sun.sort;
/**
 * ClassName:InsertSort <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月11日 上午10:58:58 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class InsertSort {
   public static void main(String[] args) {
        //插入
//       straightInsert();
       binaryInsert();
//       ShellInsert();
   }
   /**
    * 直接插入
    * insert:(这里用一句话描述这个方法的作用).
    * @author Mu Xiaobai
    * @since JDK 1.6
    */
   public static void straightInsert(){
       int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
       System.out.println("排序之前：");
       for (int i = 0; i < a.length; i++) {
           System.out.print(a[i]+" ");
       }
       //直接插入排序
       for (int i = 1; i < a.length; i++) {//从第二个数据开始下标是1
           int temp = a[i]; //待插入元素
           int j;
           for (j = i-1; j>=0; j--) {//从当前元素的前一个开始，依次递减
               //将大于temp的往后移动一位
               if(a[j]>temp){//如果前一个比待插入的大，则把前一个赋给当前位置
                   a[j+1] = a[j];
               }else{
                   break;
               }
           }
           a[j+1] = temp;//最后把当前位置赋上初始待插入的值
           System.out.println("\nstep :"+i);
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
    * binaryInsert:二分插入.
    * @author Mu Xiaobai
    * @since JDK 1.8
    */
   public static void binaryInsert(){
       int[] a={49,38,65,97,176,213,227,49,78,34,12,164,11,18,1};
       System.out.println("排序之前：");
       for (int i = 0; i < a.length; i++) {
           System.out.print(a[i]+" ");
       }
       //二分插入排序
       for (int i = 0; i < a.length; i++) {
           int temp = a[i];
           int left = 0;
           int right = i-1;
           int mid = 0;
           //找位置
           while(left<=right){
               mid = (left+right)/2;
               if(temp<a[mid]){
                   right = mid-1;
               }else{
                   left = mid+1;
               }
           }
           //赋值
           for (int j = i-1; j >= left; j--) {
               a[j+1] = a[j];
           }
           if(left != i){
               a[left] = temp;
           }
       }
       System.out.println();
       System.out.println("排序之后：");
       for (int i = 0; i < a.length; i++) {
           System.out.print(a[i]+" ");
       }
   }
   /**
    * Shell也是用的插入
    * ShellInsert:()
    * 先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。
    * 所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；
    * 然后，取第二个增量d2<d1重复上述的分组和排序，直至所取的增量dt=1(dt<dt-l<…<d2<d1)，
    * 即所有记录放在同一组中进行直接插入排序为止。该方法实质上是一种分组插入方法。
    * @author Mu Xiaobai
    * @since JDK 1.8
    */
   public static void  ShellInsert(){
       int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
       System.out.println("排序之前：");
       for (int i = 0; i < a.length; i++) {
           System.out.print(a[i]+" ");
       }
       //希尔排序
       int d = a.length;
       while(true){
           d = d / 2;
           for(int x=0;x<d;x++){
               for(int i=x+d;i<a.length;i=i+d){
                   int temp = a[i];
                   int j;
                   for(j=i-d;j>=0&&a[j]>temp;j=j-d){
                       a[j+d] = a[j];
                   }
                   a[j+d] = temp;
               }
           }
           if(d == 1){
               break;
           }
       }
       System.out.println();
       System.out.println("排序之后：");
       for (int i = 0; i < a.length; i++) {
           System.out.print(a[i]+" ");
       }
   }
}

