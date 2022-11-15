package Tasks;

import java.util.*;

public class Task_3 {
    public static void main(String[] args) {
        System.out.println(solutions(1, 0, -1));
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(checkPerfect(97));
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(same(new Integer[]{1, 3, 4, 4, 4}, new Integer[]{2, 5, 7}));
        System.out.println(isKaprekar(10));

    }

    public static int solutions(int a, int b, int c) {
        int discriminant = b * b - 4 * a * c;
        if (discriminant < 0) return 0;
        else if (discriminant == 0) return 1;
        else return 2;
    }

    public static int findZip(String message) {
        message = message.toLowerCase();
        int firstIndex = message.indexOf("zip");
        return message.indexOf("zip", firstIndex + 1);
    }

    public static boolean checkPerfect(int number) {
        int Number = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) Number += i;
        }
        return number == Number;
    }

    public static String flipEndChars(String message) {
        int lastIndex = message.length() - 1;
        char lastChar = message.charAt(lastIndex);
        char firstChar = message.charAt(0);

        if (lastIndex == firstChar) return "Two's a pair.";
        else if (message.length() == 2) return "Incompatible.";
        else {
            StringBuilder Message = new StringBuilder(message);

            Message.deleteCharAt(lastIndex).append(firstChar);
            Message.reverse().deleteCharAt(lastIndex).append(lastChar);
            message = Message.reverse().toString();

            return message;
        }
    }

    public static boolean isValidHexCode(String code) {
        String ISAlphabetic = "abcdef0123456789";
        code = code.toLowerCase();
        if (code.charAt(0) == '#' && code.length() == 7) {
            for (int i = 1; i < code.length(); i++) {
                if (!ISAlphabetic.contains(String.valueOf(code.charAt(i)))) {
                    return false;
                }
            }
        } else return false;
        return true;
    }

    public static boolean same(Integer[] firstArray, Integer[] secondArray) {
        List<Integer> firstMessage = Arrays.asList(firstArray);
        List<Integer> secondMessage = Arrays.asList(secondArray);
        Set<Integer> uniqueFirst = new HashSet<>(firstMessage);
        Set<Integer> uniqueSecond = new HashSet<>(secondMessage);
        return uniqueFirst.size() == uniqueSecond.size();

    }

    public static boolean isKaprekar(int number) {
        StringBuilder Number = new StringBuilder(Integer.toString(number * number));
        String first, second;
        int numberLength = Number.length();
        if (number <= 1) return true;
        first = Number.substring(0, numberLength / 2);
        second = Number.substring(numberLength / 2);

        return Integer.parseInt(first) +  Integer.parseInt(second)== number;


    }


}
