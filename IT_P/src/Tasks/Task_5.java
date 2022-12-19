package Tasks;

import java.util.*;
import java.security.MessageDigest;

public class Task_5 {
    public static void main(String[] args) {
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println(canMove("Ферзь", new int[]{3, 4}, new int[]{4, 6}));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println(sumDigProd(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(Arrays.toString(new List[]{sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})}));
        System.out.println(validateCard("1234567890123452"));
        System.out.println(numToEng(919));
        System.out.println(getSha256Hash("password123"));
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(hexLattice(19));
    }

    public static String decrypt(int[] messageCode) {
        int deCode = messageCode[0];
        StringBuilder message = new StringBuilder();
        message.append((char) deCode);
        for (int i = 1; i < messageCode.length; i++) {
            deCode += messageCode[i];
            message.append((char) deCode);
        }
        return message.toString();
    }

    public static int[] encrypt(String message) {
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

    public static int sumDigProd(int[] numbers) {
        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        while (sum >= 10) {
            int checkPoint = 1;
            while (sum > 0) {
                checkPoint *= sum % 10;
                sum /= 10;
            }
            sum = checkPoint;
        }
        return (sum);
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
                        sum += num;
                    } else {
                        int NUM = 0;
                        NUM += num / 10;
                        NUM += num % 10;
                        sum += NUM;
                    }
                } else {
                    sum += num;
                }
            }
            StringBuilder last = new StringBuilder(String.valueOf(sum));
            int LAST = 10 - Integer.parseInt(String.valueOf(last.charAt(last.length() - 1)));
            char LAAST = Character.forDigit(LAST, 10);
            return LAAST == checkPoint;
        } else return false;

    }

    public static String numToEng(int number) {
        String[] ones = {"zero", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine"};
        String[] firstTens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
                "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] secondTens = {"", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
                "ninety"};
        String messageNum = Integer.toString(number);

        switch (messageNum.length()) {
            case 1:
                return ones[number];
            case 2:
                if (number >= 10 && number <= 19) return firstTens[number - 10];
                else return secondTens[number / 10 - 1] + " " + ones[number % 10];
            case 3:
                StringBuilder res = new StringBuilder(ones[number / 100] + " hundred ");
                messageNum = messageNum.substring(1);
                if (messageNum.charAt(0) == '0') {
                    messageNum = messageNum.substring(1);
                    res.append(ones[Integer.parseInt(messageNum) % 10]);
                } else {
                    if (Integer.parseInt(messageNum) >= 10 && Integer.parseInt(messageNum) <= 19)
                        res.append(firstTens[Integer.parseInt(messageNum) - 10]);
                    else
                        res.append(secondTens[Integer.parseInt(messageNum) / 10 - 1]).append(" ").append(ones[Integer.parseInt(messageNum) % 10]);
                }
                return res.toString();
        }
        return "";
    }

    public static String getSha256Hash(String str) {
        StringBuilder hashCodeMessage = new StringBuilder();
        try {
            MessageDigest doAlgorithm = MessageDigest.getInstance("SHA-256");
            byte[] hash = doAlgorithm.digest(str.getBytes());
            for (byte b : hash) {
                String hashSHA_256_to_16byte = Integer.toHexString(0xff & b);
                hashCodeMessage.append(hashSHA_256_to_16byte);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hashCodeMessage.toString();
    }

    public static String correctTitle(String message) {
        String[] words = message.toLowerCase().split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            if (!word.equals("of") && !word.equals("in") && !word.equals("and") && !word.equals("the"))
                res.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return res.toString();
    }

    public static String hexLattice(int n) {
        int i = 0;
        boolean isHexNum = false;
        while (3 * i * (i + 1) + 1 <= n) {
            if (3 * i * (i + 1) + 1 == n) {
                isHexNum = true;
                break;
            }
            i++;
        }
        StringBuilder result = new StringBuilder();
        if (isHexNum) {
            int painting = i;
            int spacing = i;
            for (int j = 0; j < 2 * i - 1; j++) {
                result.append("\n");
                StringBuilder space = new StringBuilder("");
                space.append(" ".repeat(Math.max(0, spacing - 1)));
                result.append(space);
                result.append(" 1".repeat(Math.max(0, painting)));
                result.append(space).append(" ");
                if (j < i - 1) {
                    painting += 1;
                    spacing -= 1;
                } else {
                    painting -= 1;
                    spacing += 1;
                }
            }
            return result.toString().replaceFirst("\n", "");
        } else return "Invalid";
    }
}
