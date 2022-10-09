package Task_1;

public class Abcmath {
    public static void main(String[] args){
        System.out.println(abcmath(42,5,10));
    }
    public static boolean abcmath(int firstValue, int steps, int divider){
        for (int i = 0; i < steps; i++) {
            firstValue += firstValue;
        }
        return firstValue % divider == 0;
    }
}
