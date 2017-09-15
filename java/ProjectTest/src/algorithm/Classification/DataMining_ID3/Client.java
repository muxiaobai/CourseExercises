package algorithm.Classification.DataMining_ID3;

/**
 * ID3决策树分类算法测试场景类
 * @author lyq
 *
 */
public class Client {
    public static void main(String[] args){
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_ID3\\input.txt";
        
        ID3Tool tool = new ID3Tool(filePath);
        tool.startBuildingTree(true);
    }
}
