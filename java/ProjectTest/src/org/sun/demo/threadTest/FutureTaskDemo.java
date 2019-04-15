/**
 * Project Name:ProjectTest
 * File Name:FutureTaskDemo.java
 * Package Name:org.sun.demo.threadTest
 * Date:2019年4月15日上午9:50:07
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package org.sun.demo.threadTest;
/**
 * ClassName:FutureTaskDemo 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月15日 上午9:50:07 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/** 
 * @author : mingwu.lxm
 * @version 创建时间：2016年1月5日 下午8:23:09 
 * 类说明  解决计算一个类的两个数相加，放到sum类中
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size =6;
        
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        //存储本地串行计算花费的时间
        List<UserInfo> retInfoList1 = new ArrayList<UserInfo>();
        //存储多线程计算花费的时间
        List<UserInfo> retInfoList2 = new ArrayList<UserInfo>();
        
        for (int i = 0; i < 1000; i++) {
            UserInfo uc = new UserInfo();
            uc.setMoneyA((int) (Math.random()*100));
            uc.setMoneyB((int) (Math.random()*100));
            userInfoList.add(uc);
        }
        //准备要执行的数据，数据整备完毕
        
        
       
        Long aDateLong = System.currentTimeMillis();
        for (int i = 0; i < userInfoList.size(); i++) {
            int sum = userInfoList.get(i).getMoneyA()+userInfoList.get(i).getMoneyB();
            userInfoList.get(i).setSumNum(sum);
            retInfoList1.add(userInfoList.get(i));
            //线程睡眠以模仿计算消耗的时间
            Thread.sleep(1);
        }
        Long bDateLong = System.currentTimeMillis();
        System.out.println("串行进行的耗时:"+ (bDateLong-aDateLong));
        
        ExecutorService es = Executors.newFixedThreadPool(100);
        List<List<UserInfo>> subTaskList = new ArrayList<List<UserInfo>>();
        //切分任务
        for (int i =0 ;i<=userInfoList.size();i=i+size) {
            subTaskList.add(userInfoList.subList(i, Math.min(i+size, userInfoList.size())));
        }
        Long aDateLong2 = System.currentTimeMillis();
        
        //这个list是专门用来保存所有的future的，先把所有任务全部扔出去执行，这时候不管结果
        List<FutureTask<List<UserInfo>>> tasklist = new ArrayList<FutureTask<List<UserInfo>>>();
       for (List<UserInfo> alist : subTaskList) {
          FutureTask<List<UserInfo>> task = new FutureTask<List<UserInfo>>(new SumJob(alist));
          es.execute(task);
          tasklist.add(task);
       }
       //拿到所有的结果
       for (FutureTask<List<UserInfo>> futureTask : tasklist) {
           retInfoList2.addAll(futureTask.get());
        
       }
       
       Long bDateLong2 = System.currentTimeMillis();
       System.out.println("多线程拆分后耗时:"+ (bDateLong2-aDateLong2));
       System.out.println(retInfoList1.size());
       System.out.println(retInfoList2.size());
        
    }


}
//求和的线程任务
class SumJob implements Callable<List<UserInfo>>{
    private List<UserInfo> sumList=null;
    public SumJob(List<UserInfo> sumList) {
        super();
        this.sumList = sumList;
    }

    @Override
    public List<UserInfo> call() throws Exception {
        for (int i = 0; i < sumList.size(); i++) {
            int sum = sumList.get(i).getMoneyA()+sumList.get(i).getMoneyB();
            sumList.get(i).setSumNum(sum);
            //线程睡眠以模仿计算消耗的时间
            Thread.sleep(1);
        }
        return sumList;
    }
    
}
class UserInfo{
    private Integer moneyA;
    private Integer moneyB;
    private Integer sumNum;
    public Integer getMoneyA() {
        return moneyA;
    }
    public void setMoneyA(Integer moneyA) {
        this.moneyA = moneyA;
    }
    public Integer getMoneyB() {
        return moneyB;
    }
    public void setMoneyB(Integer moneyB) {
        this.moneyB = moneyB;
    }
    public Integer getSumNum() {
        return sumNum;
    }
    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }
    
}