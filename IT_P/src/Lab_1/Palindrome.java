package Lab_1;
// Создание класса
public class Palindrome {

    /*
    Создание основного метода
    В него поступают данные из конфигуратора запуска кода
    */
    public static void main(String[] args){

    // Создание цикла для перебора слова в строке и проверки их по условию
        for (int i = 0; i < args.length; i++){
            String word = args[i];

    // Проверка условия и вывод работы программы
            if(isPalindrome(word))
                System.out.println(i + " - Lab_1.Palindrome");
            else
                System.out.println(i + " - Not Lab_1.Palindrome" + " ");
        }
    }

    // Создание метода переворачивания строки
    public static String reverseString(String word){

    // Создание пустой строки
        String reversWord = "";

    // Создание цикла для перебора посимвольно входящей строки
        for (int i = word.length() - 1; i >= 0; i--)

    // Посимвольная запись в новую строку (перевернутую)
            reversWord += word.charAt(i);
        return reversWord; // Закрытие метода и возвращение полученных данных
    }

    // Создание метода по проверки на палиндромность
    public static boolean isPalindrome(String word){

    // Закрытие метода и вывод булевой переменной False\True
        return word.equals(reverseString(word));
    }
}
