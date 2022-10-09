package Task_1;

public class Animals {
    public static void main(String[] args){
        System.out.println(animals(5,2,8));
    }

    public static int animals(int chickens, int cows, int pigs){
        return chickens*2 + cows*4 + pigs*4;
    }
}
