import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        while (true){
            System.out.println("Введите путь файла и нажмаите Enter:");
            String path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if(isDirectory){
                System.out.println("Указан путь к папке, а не к файлу");
                continue;
            }

            if (fileExists) System.out.println("Путь указан верно, номер файла:" + count++);
            else System.out.println("Файл не существует");
        }
    }
}


