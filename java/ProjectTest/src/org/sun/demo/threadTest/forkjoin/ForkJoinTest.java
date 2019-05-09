package org.sun.demo.threadTest.forkjoin;

import java.util.concurrent.*;
import java.util.Map;
/**
 * Project Name:ProjectTest
 * File Name:
 * Package Name:
 * Date:
 * Copyright (c) , All Rights Reserved.
 */
public class ForkJoinTest extends RecursiveAction {
    private Map map ;
    public ForkJoinTest(Map map) {
        this.map = map;
    }

    @Override
    protected void compute() {

    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool ();
        Map map = new ConcurrentHashMap<Integer,Object> ();
        // 100w
        ForkJoinTask forkJoinTask = new ForkJoinTest (map);
        forkJoinPool.submit (forkJoinTask);


    }
}
