package Task_1;

public class Task_1 {
    public static void main(String[] args){
        int[] myArray = {1, 5, 9};
        System.out.println(remainder(-9, 45));
        System.out.println(area(10, 10));
        System.out.println(animals(5, 2, 8));
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(operation(15, 11, 11));
        System.out.println(ctoa('A'));
        System.out.println(addToUp(7));
        System.out.println(nextEdge(8, 10));
        System.out.println(sumOfCubes(myArray));
        System.out.println(abcmath(42, 5, 10));



    }
    public static boolean abcmath(int firstValue, int steps, int divider){
        for (int i = 0; i < steps; i++) {
            firstValue += firstValue;
        }
        return firstValue % divider == 0;
    }
    public static int addToUp(int value){
        /*int answer = 0;
        for (int i = 1; i <= value; i++) {
            answer += i;
        }
        return answer;*/
        return (1 + value)*value/2;
    }
    public static int area(int base, int height){
        return base*height/2;
    }
    public static int animals(int chickens, int cows, int pigs){
        return chickens*2 + cows*4 + pigs*4;
    }
    public static int ctoa(char c){
        return c;
    }
    public static int nextEdge(int firstEdge, int secondEdge){
        return firstEdge + secondEdge - 1;
    }
    public static String operation(int N, int a, int b){
        if (a + b == N) return "added";
        else if (a - b == N || b - a == N) return "subtracted";
        else if (a / b == N || b / a == N) return "division";
        else if (a * b == N) return "multiplication";
        else return "none";
    }
    public static boolean profitableGamble(double prob, int prize, int pay){
        return prob*prize > pay;
    }


    public static int remainder(int firstValue, int secondValue){
        return firstValue % secondValue;
    }
    public static int sumOfCubes(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += Math.pow(array[i], 3);
        }
        return sum;


    }
}
