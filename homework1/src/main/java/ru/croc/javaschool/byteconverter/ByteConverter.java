package main.java.ru.croc.javaschool.byteconverter;

import java.util.Locale;
import java.util.Scanner;

public class ByteConverter {

    private static void printBytes(double bytes) {
        String[] units = {"B", "KB", "MB", "GB", "TB", "PB", "EB"};
        int unitIndex = 0;

        while (bytes >= 1024 && unitIndex < units.length - 1) {
            bytes /= 1024;
            unitIndex++;
        }

        String result = String.format(Locale.US, "%.1f %s", bytes, units[unitIndex]);
        System.out.println(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long bytes = scanner.nextLong();
        printBytes((double) bytes);
    }
}
