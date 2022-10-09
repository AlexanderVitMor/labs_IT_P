package Task_1;

public class Profitable {
    public static void main(String[] args){
        System.out.println(profitableGamble(0.2, 50, 9));
    }
    public static boolean profitableGamble(double prob, int prize, int pay){
        return prob*prize > pay;
    }
}
