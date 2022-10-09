package Task_1;

public class SumOfCubes {
    public static void main(String[] args){
        int[] myArray = {1, 5, 9};
        System.out.println(sumOfCubes(myArray));
    }
    public static int sumOfCubes(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += Math.pow(array[i], 3);
        }
        return sum;
    }
}
