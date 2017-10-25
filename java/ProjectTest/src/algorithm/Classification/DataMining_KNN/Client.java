package algorithm.Classification.DataMining_KNN;

/**
 * 
 * @TODO k最近邻算法场景类型
 * @author lyq
 * k=3 为多少范围之内 计算出来的权重值(computeEuclideanDistance)比较
 * KNN k-Nearest Neighbor
 * 多分类问题(multi-modal,对象具有多个类别标签) softmax
 */
public class Client {
	public static void main(String[] args){
		String trainDataPath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_KNN\\trainInput.txt";
		String testDataPath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_KNN\\testInput.txt";
		
		KNNTool tool = new KNNTool(trainDataPath, testDataPath);
		tool.knnCompute(3);
		
	}
	


}
