package com.endava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Application {

    private static final String PATH1 = "./src/main/resources/menu1.csv";
    private static final String PATH2 = "./src/main/resources/menu2.csv";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        double start = System.currentTimeMillis();

        double menu1AveragePrice = FileUtil.getPriceAverage(PATH1);
        double menu2AveragePrice = FileUtil.getPriceAverage(PATH2);

        double end = System.currentTimeMillis();

        System.out.println("Menu 1 average price: " + menu1AveragePrice);
        System.out.println("Menu 2 average price: " + menu2AveragePrice);
        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");

        start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Double> menu1PriceAverage = () -> FileUtil.getPriceAverage(PATH1);
        Callable<Double> menu2PriceAverage = () -> FileUtil.getPriceAverage(PATH2);

        Future<Double> future1 = executorService.submit(menu1PriceAverage);
        Future<Double> future2 = executorService.submit(menu2PriceAverage);

        executorService.shutdown();

        end = System.currentTimeMillis();

        System.out.println("Menu 1 average price: " + future1.get());
        System.out.println("Menu 2 average price: " + future2.get());
        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");
    }
}
