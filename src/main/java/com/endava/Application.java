package com.endava;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    private static final String PATH1 = "./src/main/resources/menu1.csv";
    private static final String PATH2 = "./src/main/resources/menu2.csv";

    public static void main(String[] args) throws IOException {

        double start = System.currentTimeMillis();

        double menu1AveragePrice = FileUtil.getPriceAverage(PATH1);
        double menu2AveragePrice = FileUtil.getPriceAverage(PATH2);

        double end = System.currentTimeMillis();

        System.out.println("Menu 1 average price: " + menu1AveragePrice);
        System.out.println("Menu 2 average price: " + menu2AveragePrice);
        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");

        start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable runnableTask1 = createRunnableTask(PATH1);
        Runnable runnableTask2 = createRunnableTask(PATH2);

        executorService.execute(runnableTask1);
        executorService.execute(runnableTask2);

        executorService.shutdown();

        end = System.currentTimeMillis();

        System.out.println("Time to process: " + (end - start) / 1000 + " seconds");

    }

    public static Runnable createRunnableTask(String path) {
        return () -> {
            double averagePrice = 0;
            try {
                averagePrice = FileUtil.getPriceAverage(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String menuNumber = path.replaceAll("\\D+", "");
            String result = String.format("Menu %s average price: %f", menuNumber, averagePrice);
            System.out.println(result);
        };
    }
}
