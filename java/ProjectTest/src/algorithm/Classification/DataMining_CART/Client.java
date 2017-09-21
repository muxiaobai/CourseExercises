package algorithm.Classification.DataMining_CART;

public class Client {
    public static void main(String[] args){
        String filePath = "E:\\CourseExercises\\java\\ProjectTest\\src\\algorithm\\Classification\\DataMining_CART\\input.txt";
        
        CARTTool tool = new CARTTool(filePath);
        
        tool.startBuildingTree();
    }
}
