package algorithm.LinkMining.DataMining_PageRank;

/**
 * PageRank计算网页重要性/排名算法 Google网页排名
 * 搞一个A 然后依次根据权重*A得到新的权重，新旧权重比对，误差小于0.001的时候这个权重就是最终的网页权重，然后取最大的一个
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
