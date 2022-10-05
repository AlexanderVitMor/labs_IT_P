public class Palindrome {
    public static void main(String[] args){
        for (int i = 0; i < args.length; i++){
            String word = args[i];
            if(isPalindrome(word))
                System.out.println(i + " - Palindrome");
            else
                System.out.println(i + " - Not Palindrome" + " ");
        }
    }
    public static String reverseString(String word){
        String reversWord = "";
        for (int i = word.length() - 1; i >= 0; i--)
            reversWord += word.charAt(i);
        return reversWord;
    }
    public static boolean isPalindrome(String word){
        return word.equals(reverseString(word));
    }
}
