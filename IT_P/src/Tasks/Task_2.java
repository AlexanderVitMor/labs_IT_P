package Tasks;

import java.util.Arrays;

public class Task_2 {
    public static void main(String[] args){
        System.out.println(repeat("hello", 3));
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(isAvgWhole(new int[]{1, 3}));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3})));
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(Fibonacci(12));
        System.out.println(isValid("59001"));

    }
    public static String repeat(String str, int value){
        String finishStr = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < value; j++) {
                finishStr += (str.charAt(i));
            }
        }
        return finishStr;
    }

    public static int differenceMaxMin(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j : array) {
            max = Math.max(max, j);
            min = Math.min(min, j);
        }
        return Math.abs(max) + Math.abs(min);
    }

    public static boolean isAvgWhole(int[] array){
        int sum = 0;
        for(int j : array){
            sum += j;
        }
        return sum % array.length == 0;
    }

    public static int[] cumulativeSum(int[] array){
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += array[j];
            }
            newArray[i] = sum;
        }
        return newArray;
    }

    public static int getDecimalPlaces(String number) {
        int negativeSum = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) != '.') negativeSum++;
            else return (number.length() - 1) - negativeSum;
        }
        return number.length() - negativeSum;
    }

    public static int Fibonacci(int number){

        int[] fibonacci = new int[number + 2];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i < number + 2; i++) {
            fibonacci[i] += fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci[number + 1];
    }

    public static boolean isValid(String index){
        if (index.length() <= 5){
            for (int i = 0; i < index.length(); i++) {
                if (Character.isLetter(index.charAt(i)) || index.charAt(i) == ' ') return false;
            }
            return true;
        }
        return false;
    }
}
