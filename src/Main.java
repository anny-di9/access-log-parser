import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path;
        Statistics statistics = new Statistics();
        int count = 0;
        while (true) {
            System.out.println("Введите путь к файлу и нажмите <Enter>:");
            path = new Scanner(System.in).nextLine();
            File file = new File(path);

            boolean pathOfFile = file.exists();
            boolean isNotDirectory = !file.isDirectory();
            if (pathOfFile && isNotDirectory) {
                System.out.println("Путь к файлу является правильным");
                System.out.println("Номер файла" + ++count);
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);

                    String line;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();

                        if (length > 1024) {
                            throw new RuntimeException("Length of line > 1024");
                        }

                        LogEntry e = new LogEntry(line);
                        statistics.addEntry(e);

//                linesCount++;
                    }

                    System.out.println("Total traffic: " + statistics.totalTraffic + " bytes");
                    System.out.println("Min time: " + statistics.minTime);
                    System.out.println("Max time: " + statistics.maxTime);
                    System.out.println("Traffic rate: " + statistics.getTrafficRate() + " bytes per hour");
                } catch (IOException e) {
                    System.out.println(e.fillInStackTrace());
                }


            } else {
                System.out.println("Файл отсутствует или путь ведет к директории файла");
            }
        }
    }
}