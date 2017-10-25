package algorithm.Clustering.DataMining_KMeans;

/**
 * K-means（K均值）算法调用类  聚类
 * @author lyq
 * classNum 聚成classNum类 3类
 */
public class Client {
    public static void main(String[] args){
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Clustering\\DataMining_KMeans\\input.txt";
        //聚类中心数量设定
        int classNum = 3;
        
        KMeansTool tool = new KMeansTool(filePath, classNum);
        tool.kMeansClustering();
    }
}
