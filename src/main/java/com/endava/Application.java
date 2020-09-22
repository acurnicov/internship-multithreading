package com.endava;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        double start = System.currentTimeMillis();

        double menu1AveragePrice = FileUtil.getPriceAverage("./src/main/resources/menu1.csv");
        double menu2AveragePrice = FileUtil.getPriceAverage("./src/main/resources/menu2.csv");

        double end = System.currentTimeMillis();

        System.out.println("Menu 1 average price: " + menu1AveragePrice);
        System.out.println("Menu 2 average price: " + menu2AveragePrice);
        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");
    }
}
