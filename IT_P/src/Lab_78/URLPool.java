package Lab_78;

import java.util.HashSet;
import java.util.LinkedList;

public class URLPool {
    // Список ожидающих обхода URL-адресов
    LinkedList<URLDepthPair> urlsWaitingCheck;
    // Список всех URL-адресов, которые мы видели
    LinkedList<URLDepthPair> urlsSeen;
    // Максимальная глубина обхода
    int maxDepth;
    // Количество ожидающих потоков
    int waitCountThread;
    // Набор всех просмотренных URL-адресов
    HashSet<String> urlsSeenSet;

    public URLPool(int maxDepth) {
        this.maxDepth = maxDepth;
        urlsWaitingCheck = new LinkedList<>();
        urlsSeen = new LinkedList<>();
        waitCountThread = 0;
        urlsSeenSet = new HashSet<>();
    }

    /**
     * Блок кода помечен ключевым словом synchronized, это значит, что блок может выполняться только одним потоком одновременно.
     *
     * @return Получаем следующую пару Url-адресов для обхода
     */
    public synchronized URLDepthPair getNextPair() {
        // Приостановить этот поток до тех пор, пока не будет доступна пара
        while (urlsWaitingCheck.size() == 0) {
            try {
                waitCountThread++;
                // Освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор, пока другой поток не вызовет метод notify()
                wait();
                waitCountThread--;
                //Выбрасывается, когда поток находится в ожидании, спящем режиме или иным образом занят, и поток прерывается до или во время действия.
            } catch (InterruptedException e) {
                System.out.println("Caught unexpected " +
                        "InterruptedException, ignoring...");
            }
        }
        return urlsWaitingCheck.removeFirst();
    }

    /**
     * Добавляем пару URL-адресов в пул. Сначала проверяем, видели ли мы этот URL раньше.
     * Если нет, всегда добавляем его в список просмотренных и добавляем так же
     * в список ожидающих, если его глубина не слишком велика.
     *
     * @param pair URL адресс и глубина.
     */
    public synchronized void addPair(URLDepthPair pair) {
        if (urlsSeenSet.contains(pair.getURLString())) {
            return;
        }
        urlsSeen.add(pair);
        if (pair.getDepth() < maxDepth) {
            urlsWaitingCheck.add(pair);
            // продолжает работу потока, у которого ранее был вызван метод wait()
            notify();
        }
        // Закидываем этот URL в уже просмотренный.
        urlsSeenSet.add(pair.getURLString());
    }

    /**
     * @return количество ожидающих потоков
     */
    public synchronized int getWaitCountThread() {
        return waitCountThread;
    }


    /**
     * @return список всех просмотренных URL-адрессов и их глубина
     */
    public LinkedList<URLDepthPair> getSeenUrls() {
        return urlsSeen;
    }

}