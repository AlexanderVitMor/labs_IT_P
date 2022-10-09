package Task_1;

public class AddUpTo {
    public static void main(String[] args){
        System.out.println(addToUp(7));
    }
    public static int addToUp(int value){
        /*int answer = 0;
        for (int i = 1; i <= value; i++) {
            answer += i;
        }
        return answer;*/
        return (1 + value)*value/2;
    }
}
