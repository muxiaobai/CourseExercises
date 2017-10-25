package algorithm.BaggingAndBoosting.DataMining_AdaBoost;

/**
 * AdaBoost提升算法调用类
 * @author lyq
 *
 */
public class Client {
    public static void main(String[] agrs){
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\BaggingAndBoosting\\DataMining_AdaBoost\\input.txt";
        //误差率阈值yu
        double errorValue = 0.2;
        
        AdaBoostTool tool = new AdaBoostTool(filePath, errorValue);
        tool.adaBoostClassify();
    }
}
