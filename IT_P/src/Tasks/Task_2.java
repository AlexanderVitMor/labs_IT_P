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
        System.out.println(isStrangePair("", ""));
        System.out.println(isPrefix("retrospect", "ret-"));
        System.out.println(isSuffix("arachnophobia", "-phodfgdfgdbia"));
        System.out.println(boxSeq(6));

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

    public static boolean isStrangePair(String firstWord, String secondWord){
        return (firstWord.length() == 0  && secondWord.length() == 0) || (firstWord.charAt(0) == secondWord.charAt(secondWord.length() - 1) &&
               firstWord.charAt(firstWord.length() - 1) == secondWord.charAt(0));
    }

    public static boolean isPrefix(String word, String _prefix){
        StringBuilder prefix = new StringBuilder(_prefix);
        prefix.deleteCharAt(_prefix.length() - 1);
        String Prefix = prefix.toString();
        return word.startsWith(Prefix);
    }


    public static boolean isSuffix(String word, String _suffix){
        StringBuilder suffix = new StringBuilder(_suffix);
        suffix.deleteCharAt(0);
        String Suffix = suffix.toString();
        return word.endsWith(Suffix);
    }

    public static int boxSeq(int value){
        int sum = 0;
        for (int i = 1; i < value + 1; i++) {
            if (i % 2 != 0) sum += 3;
            else sum -= 1;
        }
        return sum;
    }



}
