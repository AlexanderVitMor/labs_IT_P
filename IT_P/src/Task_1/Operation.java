package Task_1;

public class Operation {
    public static void main(String[] args){
        System.out.println(operation(10, 15, 9));
    }

    public static String operation(int N, int a, int b){
        if (a + b == N) return "added";
        else if (a - b == N || b - a == N) return "subtracted";
        else if (a / b == N || b / a == N) return "division";
        else if (a * b == N) return "multiplication";
        else return "none";
    }
}
