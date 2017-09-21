package algorithm.AssociationAnalysis.DataMining_FPTree;

/**
 * FPTree频繁模式树算法
 * @author lyq
 *
 */
public class Client {
    public static void main(String[] args){
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_KNN\\testInput.txt";
        

        //最小支持度阈值
        int minSupportCount = 2;
        
        FPTreeTool tool = new FPTreeTool(filePath, minSupportCount);
        tool.startBuildingTree();
    }
}
