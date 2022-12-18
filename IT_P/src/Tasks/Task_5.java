package Tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Task_5 {
    public static void main(String[] args) {
        System.out.println(encrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(decrypt("Sunshine")));
        System.out.println(canMove("Ферзь", new int[]{3, 4}, new int[]{4, 6}));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6)); //4
        System.out.println(Arrays.toString(new List[]{sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})})); //5
        System.out.println(validateCard("1234567890123452")); //6
        System.out.println(numToEng(909)); //7
        System.out.println(getSha256Hash("password123")); //8
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth.")); //9
        System.out.println(hexLattice(19)); //10
    }

    public static String encrypt(int[] messageCode) {
        int deCode = messageCode[0];
        StringBuilder message = new StringBuilder();
        message.append((char) deCode);
        for (int i = 1; i < messageCode.length; i++) {
            deCode += messageCode[i];
            message.append((char) deCode);
        }
        return message.toString();
    }

    public static int[] decrypt(String message) {
        char[] Message = message.toCharArray();
        int enCode = Message[0];
        int[] messageCode = new int[Message.length];
        messageCode[0] = enCode;
        for (int i = 1; i < Message.length; i++) {
            messageCode[i] = Message[i] - Message[i - 1];
        }
        return messageCode;
    }

    public static boolean canMove(String nameChest, int[] start, int[] end) {
        int x1 = start[0], x2 = end[0];
        int y1 = start[1], y2 = end[1];
        int absX = Math.abs(x1 - x2);
        int absY = Math.abs(y1 - y2);
        switch (nameChest) {
            case "Пешка":
                if (x1 == x2 && (y1 + 1 == y2)) return true;
            case "Конь":
                if ((absX == 1 && absY == 2) || (absX == 2 && absY == 1)) return true;
            case "Слон":
                if (absX == absY) return true;
            case "Ладья":
                if (x1 == x2 && y1 == y2) return true;
            case "Ферзь":
                if ((absX == absY) || x1 == x2 || y1 == y2) return true;
            case "Король":
                if (absX <= 1 && absY <= 1) return true;
        }
        return false;
    }

    public static boolean canComplete(String piece, String message) {
        int n = 0;
        for (int i = 0; i < message.length(); i++) {
            if (piece.charAt(n) == message.charAt(i)) n++;
        }
        return n == piece.length();
    }

    public static int sumDigProd(int... a) { //сложение чисел -> произведение их цифр, пока ответ != одной цифре
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        while (sum >= 10) {
            sum = multiply(sum);
        }
        return (sum);
    }

    public static int multiply(int a) { //произведение цифр числа
        int m = 1;
        while (a > 0) {
            m *= a % 10;
            a /= 10;
        }
        return m;
    }

    public static List<String> sameVowelGroup(String[] words) {
        List<String> listWords = new ArrayList<>();
        boolean flag = true;
        String example = words[0];
        for (String word : words) {
            String ISAlphabetic = "aeyuio";

            for (int i = 0; i < word.length(); i++) {
                if (ISAlphabetic.contains(String.valueOf(word.charAt(i)))) {
                    if (example.contains(String.valueOf(word.charAt(i)))) flag = true;
                    else {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) listWords.add(word);
        }
        return listWords;
    }

    public static boolean validateCard(String numCard) {
        StringBuilder checkCard = new StringBuilder(numCard);
        int[] Numbers = new int[checkCard.length()];
        int sum = 0;
        if (checkCard.length() >= 14 && checkCard.length() <= 19) {
            char checkPoint = checkCard.charAt(checkCard.length() - 1);
            checkCard.deleteCharAt(checkCard.length() - 1);
            checkCard.reverse();
            String Card = checkCard.toString();
            for (int i = 0; i < Card.length(); i++) {
                int num = Integer.parseInt(String.valueOf(Card.charAt(i)));
                if ((i + 1) % 2 != 0) {
                    num *= 2;
                    if (num / 10 == 0) {
                        Numbers[i] = num;
                        sum += num;
                    } else {
                        int NUM = 0;
                        NUM += num / 10;
                        NUM += num % 10;
                        Numbers[i] = NUM;
                        sum += NUM;
                    }
                } else {
                    Numbers[i] = num;
                    sum += num;
                }
            }
            StringBuilder last = new StringBuilder(String.valueOf(sum));
            int LAST = 10 - Integer.parseInt(String.valueOf(last.charAt(last.length() - 1)));
            char LAAST = Character.forDigit(LAST, 10);
            return LAAST == checkPoint;
        } else return false;

    }

    public static String numToEng(int num) { //строковое представление натурального числа < 1000
        String[] ones = {"zero", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine"}; //единицы
        String[] tens1 = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
                "sixteen", "seventeen", "eighteen", "nineteen"}; //числа 10-19
        String[] tens2 = {"", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
                "ninety"}; //десятки
        String strNum = Integer.toString(num);

        switch (strNum.length()) {
            case 1: //для чисел 0-9
                return ones[num];
            case 2: //для чисел 10-99
                if (num >= 10 && num <= 19) return tens1[num - 10];
                else return tens2[num / 10 - 1] + " " + ones[num % 10];
            case 3: //для чисел 100-999
                StringBuilder res = new StringBuilder(ones[num / 100] + " hundred ");
                strNum = strNum.substring(1);
                if (strNum.charAt(0) == '0') {
                    strNum = strNum.substring(1);
                    res.append(ones[Integer.parseInt(strNum) % 10]);
                } else {
                    if (Integer.parseInt(strNum) >= 10 && Integer.parseInt(strNum) <= 19)
                        res.append(tens1[Integer.parseInt(strNum) - 10]);
                    else
                        res.append(tens2[Integer.parseInt(strNum) / 10 - 1]).append(" ").append(ones[Integer.parseInt(strNum) % 10]);
                }
                return res.toString();
            default:
                break;
        }
        return "";
    }

    public static String getSha256Hash(String str) { //хеш SHA-256 для данной строки
        StringBuilder res = new StringBuilder();
        try {
            //шифр байтов в строку
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(str.getBytes());
            //перевод их в хеш
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) res.append(0);
                res.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public static String correctTitle(String str) { //все слова с большой буквы, кроме служебных
        String s = str.toLowerCase();
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            if (!word.equals("of") && !word.equals("in") && !word.equals("and") && !word.equals("the"))
                res.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return res.toString();
    }

    public static String hexLattice(int n) { //вывод на экран гексагональной решетки для центрированного шестиугольного числа
        int i = 0; //номер центрированного числа по порядку (0,1,2...)
        boolean isHexNum = false;
        while (3 * i * (i + 1) + 1 <= n) {
            if (3 * i * (i + 1) + 1 == n) {
                isHexNum = true;
                break;
            }
            i++;
        }
        StringBuilder res = new StringBuilder();
        if (isHexNum) {
            int l = i; //для 'o'
            int m = i; //для пробелов
            for (int j = 0; j < 2 * i - 1; j++) {
                res.append("\n");
                StringBuilder space = new StringBuilder("");
                space.append(" ".repeat(Math.max(0, m - 1)));
                res.append(space);
                res.append(" 1".repeat(Math.max(0, l)));
                res.append(space).append(" ");
                l += (j < i - 1) ? 1 : -1;
                m += (j < i - 1) ? -1 : 1;
            }
            return res.toString().replaceFirst("\n", "");
        } else return "Invalid";
    }
}
