package Tasks;


import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Task_4 {

    public static void main(String[] args) {
        essayWriting(10, 7, "hello my name is Bessie and this is my essay");
        System.out.println(Split("((())())(()(()()))"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println(overTime(new Double[]{9d, 17d, 30d, 1.8}));
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(bugger(39));
        System.out.println(toStarShorthand(""));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println(trouble("666789", "12345667"));
        countUniqueBooks("AZYWABBCATTTA", 'A');
    }

    public static void essayWriting(int N, int K, String message) {
        String[] words = message.split(" ");
        StringBuilder string = new StringBuilder();
        System.out.println(Arrays.toString(words));
        int counter = 0;
        for (String word : words) {
            if (word.length() + counter <= K) {
                counter += word.length();
                string.append(word).append(" ");
            } else {
                System.out.println(string);
                counter = 0;
                string = new StringBuilder();
                string.append(word).append(" ");
                counter += word.length();
            }

        }
        System.out.println(string);
    }

    public static List<String> Split(String s) {
        List<String> array = new ArrayList<>();
        int left = 0;
        int right = 0;
        StringBuilder part = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
                part.append(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                right++;
                part.append(s.charAt(i));
            }
            if (right == left) {
                array.add(String.valueOf(part));
                part = new StringBuilder("");
                left = 0;
                right = 0;
            }
        }
        return array;
    }

    public static String toCamelCase(String message) {
        String[] Words = message.split("_");
        StringBuilder stringCamel = new StringBuilder(Words[0]);
        for (int i = 1; i < Words.length; i++) {
            stringCamel.append(Words[i].substring(0, 1).toUpperCase()).append(Words[i].substring(1));
        }
        return stringCamel.toString();
    }

    public static String toSnakeCase(String message) {
        char[] Words = message.toCharArray();
        StringBuilder stringSnake = new StringBuilder();
        for (char word : Words) {
            String c = String.valueOf(word);
            if (c == c.toUpperCase()) {
                stringSnake.append("_");
                stringSnake.append(c.toLowerCase());
            } else stringSnake.append(c);

        }
        return stringSnake.toString();
    }

    public static String overTime(Double[] array) {
        double cost = 0;
        for (Double i = array[0]; i < array[1]; i++) {
            if (i < 17) cost += array[2];
            else cost += array[2] * array[3];

        }
        return "$" + cost;
    }

    public static String BMI(String weight, String height) {
        String[] Weight = weight.split(" ");
        Double WEIGHT = Double.valueOf(Weight[0]);

        String[] Height = height.split(" ");
        Double HEIGHT = Double.valueOf(Height[0]);

        if (Weight[1].equals("pounds")) WEIGHT = WEIGHT * 0.45;
        if (Height[1].equals("inches")) HEIGHT = HEIGHT * 0.025;

        Double bmi = WEIGHT / (HEIGHT * HEIGHT);
        //Double bmi = (WEIGHT * 0.45) / ((HEIGHT * 0.025)*(HEIGHT * 0.025));

        if (bmi < 18.5) return String.format("%.1f", bmi) + " Underweight";
        else if (bmi >= 18.5 && bmi < 25) return String.format("%.1f", bmi) + " Normal weight";
        else return String.format("%.1f", bmi) + " Overweight";
    }

    public static int bugger(int number) {
        int newNumber = 1;
        int counter = 0;
        String Number = String.valueOf(number);
        while (Number.length() > 1) {
            for (int i = 0; i < Number.length(); i++) {
                newNumber *= Integer.parseInt(String.valueOf(Number.charAt(i)));
            }
            Number = String.valueOf(newNumber);
            newNumber = 1;
            counter++;
        }
        return counter;
    }

    public static String toStarShorthand(String message) {
        int index = 0;
        StringBuilder newMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String thisChar = String.valueOf(message.charAt(i));
            if (message.indexOf(message.charAt(i), i + 1) == i + 1) {
                index++;
            } else if (index == 0) {
                newMessage.append(thisChar.repeat(1));
            } else if (message.charAt(i) == message.charAt(i - 1)) {
                newMessage.append(thisChar).append("*").append(index + 1);
                index = 0;
            }
        }
        return newMessage.toString();
    }

    public static boolean doesRhyme(String firstMessage, String secondMessage) {
        firstMessage = firstMessage.toLowerCase();
        secondMessage = secondMessage.toLowerCase();
        String[] firstString = firstMessage.split(" ");
        String firstEnd = firstString[firstString.length - 1];

        String[] secondString = secondMessage.split(" ");
        String secondEnd = secondString[secondString.length - 1];

        int maxLen = Math.max(firstEnd.length(), secondEnd.length());
        String maxEnd, minEnd;

        if (firstEnd.length() == maxLen) {
            maxEnd = firstEnd;
            minEnd = secondEnd;
        } else {
            maxEnd = secondEnd;
            minEnd = firstEnd;
        }

        String ISAlphabetic = "aeyuio";

        for (int i = 0; i < maxLen; i++) {
            if (ISAlphabetic.contains(String.valueOf(maxEnd.charAt(i)))) {
                if (!minEnd.contains(String.valueOf(maxEnd.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;

    }

    public static boolean trouble(String firstNumber, String secondNumber) {
        char[] charFirstNum = firstNumber.toCharArray();
        char[] charSecondNum = secondNumber.toCharArray();
        char[] ISNumbers = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int firstCounter = 0, secondCounter = 0;
        for (char c : ISNumbers) {
            for (char s : charFirstNum) {
                if (c == s && firstCounter < 3) {
                    firstCounter++;
                } else if (firstCounter > 3) return false;
            }
            for (char s : charSecondNum) {
                if (c == s && secondCounter < 2) {
                    secondCounter++;
                } else if (secondCounter > 3) return false;
            }
            if (firstCounter == 3 && secondCounter == 2) return true;
            else {
                firstCounter = 0;
                secondCounter = 0;
            }
        }
        return false;
    }

    public static void countUniqueBooks(String message, char point) {
        HashSet<Character> setMessage = new HashSet<>();
        int index = 0;
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != point && index % 2 == 1) {
                setMessage.add(message.charAt(i));
            } else if (message.charAt(i) == point) index++;
        }
        System.out.println(setMessage.size());
    }


}
