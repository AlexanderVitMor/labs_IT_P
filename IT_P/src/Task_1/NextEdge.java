package Task_1;

public class NextEdge {
    public static void main(String[] args){
        System.out.println(nextEdge(2, 3));
    }
    public static int nextEdge(int firstEdge, int secondEdge){
        return firstEdge + secondEdge - 1;
    }
}
