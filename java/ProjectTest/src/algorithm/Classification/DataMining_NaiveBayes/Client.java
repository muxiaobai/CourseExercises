package algorithm.Classification.DataMining_NaiveBayes;


/**
 * 朴素贝叶斯算法场景调用类
 * @author lyq
 *
 */
public class Client {
    public static void main(String[] args){
        //训练集数据
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_NaiveBayes\\input.txt";
//        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_NaiveBayes\\input2.txt";
          String testData = "Sunny Cool High Strong";
//        String testData = "Rainy Cool Normal Strong";
        NaiveBayesTool tool = new NaiveBayesTool(filePath);
        System.out.println(testData + " 数据的分类为:" + tool.naiveBayesClassificate(testData));
    }
}
