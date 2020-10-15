package com.endava;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Application {

    public static String path1 = "./src/main/resources/menu1.csv";
    public static String path2 = "./src/main/resources/menu2.csv";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        double start = System.currentTimeMillis();

        double menu1AveragePrice = FileUtil.getPriceAverage(path1);
        double menu2AveragePrice = FileUtil.getPriceAverage(path2);

        double end = System.currentTimeMillis();

        System.out.println("Menu 1 average price: " + menu1AveragePrice);
        System.out.println("Menu 2 average price: " + menu2AveragePrice);
        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");

        start = System.currentTimeMillis();

        Callable<Double> menu1PriceAverage = () -> FileUtil.getPriceAverage(path1);
        Callable<Double> menu2PriceAverage = () -> FileUtil.getPriceAverage(path2);

        FutureTask<Double> menu1AverageFuture = new FutureTask<>(menu1PriceAverage);
        FutureTask<Double> menu2AverageFuture = new FutureTask<>(menu2PriceAverage);

        Thread menu1Thread = new Thread(menu1AverageFuture);
        Thread menu2Thread = new Thread(menu2AverageFuture);

        menu1Thread.start();
        menu2Thread.start();

        Double averageValueOfMenu1 = menu1AverageFuture.get();
        Double averageValueOfMenu2 = menu2AverageFuture.get();

        end = System.currentTimeMillis();

        System.out.println("Menu 1 average price: " + averageValueOfMenu1);
        System.out.println("Menu 2 average price: " + averageValueOfMenu2);
        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");
    }
}

