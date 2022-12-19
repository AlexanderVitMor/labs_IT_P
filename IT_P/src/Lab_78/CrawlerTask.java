package Lab_78;

import java.io.*;
import java.net.*;

/**
 * Этот интерфейс предназначен для предоставления общего протокола для объектов, которые хотят выполнять код, пока они активны.
 * Например, реализован классом .
 * Быть активным просто означает, что поток был запущен и еще не остановлен. RunnableThread
 */
// Класс потоков для обхода URL-адресов
public class CrawlerTask implements Runnable {
    // Ссылка на пул URL-адресов
    URLPool pool;
    // HTML href tag
    static final String HREF_TAG = "<a href=\"http";

    public CrawlerTask(URLPool pool) {
        this.pool = pool;
    }

    /**
     * Переопределение одной из главной состовляющей многопоточного программирования с помощью Thread
     * Именно в методе run() мы прописываем ту логику, которую наш поток должен выполнить
     */
    @Override
    public void run() {
        while (true) {
            URLDepthPair pair = pool.getNextPair();
            int nowThisDepth = pair.getDepth();
            try {
                // Подключение к серверу и отправка запросов и их принятие.
                Socket sock = new Socket();
                sock.connect(new InetSocketAddress(pair.getHost(), 80), 3000);
                sock.setSoTimeout(3000);
                System.out.println("Connected to " + pair.getURLString());
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                // Отправка HTTP запроса
                out.println("GET " + pair.getPath() + " HTTP/1.1");
                out.println("Host: " + pair.getHost());
                out.println("Connection: close");
                out.println();
                out.flush();

                // Собираем ссылки со страницы и добавляем их в пух URL-адрессов
                String line;
                int lineLength;
                int shiftIdx;
                while ((line = in.readLine()) != null) {
                    // Проверьте, есть ли в текущей строке ссылка
                    boolean foundFullLink = false;
                    int idx = line.indexOf(HREF_TAG);
                    if (idx > 0) {
                        // Извлекаем ссылку
                        StringBuilder sb = new StringBuilder();
                        shiftIdx = idx + 9;
                        char c = line.charAt(shiftIdx);
                        lineLength = line.length();
                        while (c != '"' && shiftIdx < lineLength - 1) {
                            sb.append(c);
                            shiftIdx++;
                            c = line.charAt(shiftIdx);
                            if (c == '"') {
                                foundFullLink = true;
                            }
                        }
                        // Создание новой пары URL-адресс + глубина
                        String newUrl = sb.toString();
                        if (foundFullLink) {
                            URLDepthPair newPair = new URLDepthPair(newUrl, nowThisDepth + 1);
                            // Добавление этой пары в пул.
                            pool.addPair(newPair);
                        }
                    }
                }

                // Закрытие сокета.
                sock.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}