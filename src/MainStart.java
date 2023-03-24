import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainStart {

    public static void main(String[] args) {

        File path = new File("access.log");

        int numberOfLines = 0;
        int yandexBots = 0;
        int googleBots = 0;

        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                if (length > 1024)
                    throw new StringLongException("имеется строка больше 1024 символов ");
                numberOfLines++;
                ProcessLog processLog = ProcessLog.of(line);
                if (processLog.isYandexBot())
                    yandexBots++;
                if (processLog.isGoogleBot())
                    googleBots++;
            }
            System.out.println("общее кол-во строк: " + numberOfLines);
            System.out.println("yandexBots " + yandexBots);
            System.out.println("googleBots " + googleBots);
            System.out.println("googleBots " + numberOfLines);


            double yb = (double) yandexBots / (double) numberOfLines;
            double gb = (double) googleBots / (double) numberOfLines;

            System.out.println("часть запросов от YandexBot: " + yb);
            System.out.println("часть запросов от GoogleBot: " + gb);
        } catch (IOException | ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
