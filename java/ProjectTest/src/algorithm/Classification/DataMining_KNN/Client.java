package algorithm.Classification.DataMining_KNN;

/**
 * k最近邻算法场景类型
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String trainDataPath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_KNN\\trainInput.txt";
		String testDataPath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_KNN\\testInput.txt";
		
		KNNTool tool = new KNNTool(trainDataPath, testDataPath);
		tool.knnCompute(3);
		
	}
	


}
