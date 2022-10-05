package Lab_1;

// Создание класса
public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++){
            if (isPrime(i)) System.out.print(i + " ");
        }
    }
    // Создание метода для определения является ли аргумент простым\нет числом
    public static boolean isPrime(int n){
        // цикл перебора всех чисел внутри входящего исключающие 1 и само число Exp: 17 (2....16)
        for (int i = 2; i < n; i++){
            if (n % i == 0) return false; // Закрытие метода при выполнение условия
        }

        return true; // Закрытие метода при выполнение условия
    }
}
