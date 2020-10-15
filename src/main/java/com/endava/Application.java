package com.endava;

import java.io.IOException;

public class Application {

    public static String path1 = "./src/main/resources/menu1.csv";
    public static String path2 = "./src/main/resources/menu2.csv";

    public static void main(String[] args) throws IOException {

        double start = System.currentTimeMillis();

        double menu1AveragePrice = FileUtil.getPriceAverage(path1);
        double menu2AveragePrice = FileUtil.getPriceAverage(path2);

        double end = System.currentTimeMillis();

        System.out.println("Menu 1 average price: " + menu1AveragePrice);
        System.out.println("Menu 2 average price: " + menu2AveragePrice);
        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");

        start = System.currentTimeMillis();

        Thread thread1 = new Thread(new FileProcessor(path1));
        Thread thread2 = new Thread(new FileProcessor(path2));

        thread1.start();
        thread2.start();

        end = System.currentTimeMillis();

        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");
    }
}
