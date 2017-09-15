package com.aliyun.tianchi.mgr.aviation;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by peicheng on 17/6/28.
 */ 
public class Main {

    public static void main(String[] args) {
//        args[0] ="../file/data20170705_1.xlsx";
//        args[1] ="../file/myresult.csv";
//        if(args.length != 2){
//            System.err.println("传入参数有误，使用方式为：java -jar xxx.jar  xxx.xlsx   xxx.csv");
//            return;
//        }
//        String inputDataFilePath = args[0];
//        String resultDataFilePath = args[1];
        String inputDataFilePath ="E:\\CourseExercises\\java\\ProjectTest\\src\\com\\aliyun\\tianchi\\mgr\\file\\data20170705_1.xlsx";
      String resultDataFilePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\com\\aliyun\\tianchi\\mgr\\file\\my_result.csv";
        try {
            //计算所得分数
            InputStream inputDataStream = new FileInputStream(inputDataFilePath);
            InputStream resultDataStream = new FileInputStream(resultDataFilePath);
            ResultEvaluator resultEvaluator = new ResultEvaluator(inputDataStream);
//            resultEvaluator.generateBaselineResult(resultDataFilePath);
            double score = resultEvaluator.runEvaluation(resultDataStream);
            System.out.println("选手所得分数为：" + score);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
