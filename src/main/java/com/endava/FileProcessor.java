package com.endava;

import java.io.IOException;

public class FileProcessor implements Runnable {

    private final String path;

    public FileProcessor(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        double menuAveragePrice = 0;
        try {
            menuAveragePrice = FileUtil.getPriceAverage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String menuNumber = path.replaceAll("\\D+","");
        String result = String.format("Menu %s average price: ", menuNumber);
        System.out.println(result + menuAveragePrice);
    }
}
