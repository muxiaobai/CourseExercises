package algorithm.Others.DataMining_BayesNetwork;

import java.text.MessageFormat;

/**
 * 贝叶斯网络场景测试类
 * 
 * @author lyq
 * 
 */
public class Client {
    public static void main(String[] args) {
        String dataFilePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Others\\DataMining_BayesNetwork\\input.txt";
        String attachFilePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Others\\DataMining_BayesNetwork\\attach.txt";
        // 查询串语句
        String queryStr;
        // 结果概率
        double result;

        // 查询语句的描述的事件是地震发生了，导致响铃响了，导致接到Mary的电话  B(盗窃)，E(地震)，A(警铃响).M(接到M的电话)，J同M的意思,
        queryStr = "B=y,A=y,M=y";
        BayesNetWorkTool tool = new BayesNetWorkTool(dataFilePath,
                attachFilePath);
        result = tool.calProByNetWork(queryStr);

        if (result == -1) {
            System.out.println("所描述的事件不满足贝叶斯网络的结构，无法求其概率");
        } else {
            System.out.println(String.format("事件%s发生的概率为%s", queryStr, result));
        }
    }
}
