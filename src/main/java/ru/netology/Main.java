package ru.netology;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        //Создание файла конфигураций с номером порта и количеством потоков, которые обрабатывают запросы на сервере
        int port = 9999;
        int threadQuantity = 64;
        File configs = new File("src/main/resources/configs.csv");
        if (!configs.exists()) {
            try {
                configs.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(configs))) {
                bw.write("port:" + port +"\n" +
                         "thread quantity:" + threadQuantity);
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // чтение файла конфигураций
        try(BufferedReader br = new BufferedReader(new FileReader(configs))) {
            port = Integer.parseInt(br.readLine().split(":")[1]);
            threadQuantity = Integer.parseInt(br.readLine().split(":")[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        var server = new Server();
        server.start(port, threadQuantity);
    }
}