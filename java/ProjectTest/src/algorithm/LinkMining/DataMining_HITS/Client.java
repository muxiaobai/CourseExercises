package algorithm.LinkMining.DataMining_HITS;

/**
 * HITS链接分析算法
 * @author lyq
 *
 */
public class Client {
    public static void main(String[] args){
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\LinkMining\\DataMining_HITS\\input.txt";
        
        HITSTool tool = new HITSTool(filePath);
        tool.printResultPage();
    }
}
