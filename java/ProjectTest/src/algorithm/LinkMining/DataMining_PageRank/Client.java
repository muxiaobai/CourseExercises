package algorithm.LinkMining.DataMining_PageRank;

/**
 * PageRank计算网页重要性/排名算法 Google网页排名
 * @author lyq
 *
 */
public class Client {
    public static void main(String[] args){
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\LinkMining\\DataMining_PageRank\\input.txt";
        
        PageRankTool tool = new PageRankTool(filePath);
        tool.printPageRankValue();
    }
}
