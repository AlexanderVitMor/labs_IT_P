package TASK_for_exz;

import java.math.BigInteger;
import java.util.*;

import static java.lang.Character.isDigit;

public class TasksForExz {
    public static void main(String[] args) {
        System.out.println(combinations(0, 2, 3));
        System.out.println(numInStr("1a", "a", "2b", "b"));
        System.out.println(isTriplet(1, 2, 3));
        System.out.println(duplication("birthday"));
        System.out.println(capSpace("iLoveMyTeapot"));
        histogram("#", 1, 3, 4);
        System.out.println(primorial(8));
        System.out.println(littleBig(28));
        System.out.println(program17(8));
    }

    public static int combinations(int... numbers) {
        int mult = 1;
        for (int number : numbers) {
            if (number != 0) mult += number;
        }
        return mult;
    }

    public static List<String> numInStr(String... words) {
        List<String> newWords = new ArrayList<>();
        for (String word : words) {
            char[] Word = word.toCharArray();
            boolean flag = false;
            for (Character c : Word) {
                if (isDigit(c)) {
                    flag = true;
                    break;
                }
            }
            if (flag) newWords.add(word);
        }
        return newWords;
    }

    public static boolean isTriplet(int... numbers) {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (max < num) max = num;
        }
        int sum2 = 0;
        for (int num : numbers) {
            if (num != max) sum2 += num * num;
        }
        return sum2 == max * max;
    }

    public static int duplication(String word) {
        word = word.toLowerCase();
        char[] wordSymbol = word.toCharArray();
        Set<Character> set = new HashSet<>();

        for (Character v : wordSymbol) {
            set.add(v);
        }

        int allSum = 0;
        for (char c : set) {
            int sum = 0;
            for (char dupC : wordSymbol) {
                if (c == dupC) sum++;
            }
            allSum += sum - 1;
        }
        return allSum;
    }

    public static String capSpace(String message) {
        char[] Words = message.toCharArray();
        StringBuilder stringSnake = new StringBuilder();
        for (char word : Words) {
            String c = String.valueOf(word);
            if (c.equals(c.toUpperCase())) {
                stringSnake.append(" ");
                stringSnake.append(c.toLowerCase());
            } else stringSnake.append(c);

        }
        return stringSnake.toString();
    }

    public static void histogram(String symbol, int... index) {
        StringBuilder result = new StringBuilder();
        for (int count : index) {
            System.out.println(symbol.repeat(count));
            result.append(symbol.repeat(count)).append("\\n");
        }
        System.out.println(result.deleteCharAt(result.length() - 1).deleteCharAt(result.length() - 1));
    }

    public static long primorial(int count) {
        long mult = 1;
        for (int i = 0, j = 0; i < count; j++) {
            BigInteger I = BigInteger.valueOf(j);
            if (I.isProbablePrime(100)) {
                mult *= j;
                i++;
            }
        }
        return mult;
    }

    public static int littleBig(int count) {
        int thisI = 0;
        for (int i = 0, j = 1, k = 0; i < count; i++) {
            thisI = 0;
            if (i % 2 == 0) {
                thisI += 5 + k;
                k++;
            } else {
                thisI = 100 * j;
                j *= 2;
            }
        }
        return thisI;
    }

    public static long program17(int n) {
        int nn = 0;
        int p = 2;
        long rez = 1;
        while (nn < n) {
            if (isPrime(p)) {
                rez *= p;
                nn++;
            }
            p++;
        }
        return rez;
    }

    private static boolean isPrime(int n) {
        if (n == 0 || n == 1) return false;
        if (n == 2) return true;
        int flag = 0;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) flag++;
        }
        return flag == 0;
    }
}
