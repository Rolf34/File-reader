import java.io.*;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Введіть ім'я файлу для роботи: ");
        String filename = scanner.nextLine();
        
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        writeToFile(filename);
                        break;
                    case 2:
                        readFile(filename);
                        break;
                    case 3:
                        System.out.println("Дякуємо за використання редактора. До побачення!");
                        running = false;
                        break;
                    case 4:
                        myNameIsBorat();
                        break;
                    default:
                        System.out.println("Невідомий вибір. Спробуйте знову.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть число від 1 до 4.");
            } catch (Exception e) {
                System.out.println("Виникла помилка: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\n" + "=".repeat(50) + "\n");
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("""
                ===TEXT EDITOR===
                1. Записати до файлу
                2. Прочитати вміст файлу
                3. Вийти з редактора
                4. Моя звай Борат
                """);
        System.out.print("Вибіріть одне: ");
    }
    
    private static void writeToFile(String filename) {
        System.out.print("Бажаєте перезаписати файл (1) чи додати в кінець (2)? ");
        String writeMode = scanner.nextLine();
        
        boolean append = !writeMode.equals("1");
        
        try {
            FileWriter fileWriter = new FileWriter(filename, append);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            
            System.out.println("Введіть текст (для завершення введення кількох рядків введіть ':q'):");
            
            String[] lines = new String[100]; 
            int lineCount = 0;
            
            while (true) {
                String line = scanner.nextLine();
                if (line.equals(":q")) {
                    break;
                }
                
                if (lineCount < lines.length) {
                    lines[lineCount] = line;
                    lineCount++;
                } else {
                    System.out.println("Досягнуто максимальної кількості рядків.");
                    break;
                }
            }
            
            for (int i = 0; i < lineCount; i++) {
                writer.write(lines[i]);
                writer.newLine();
            }
            
            writer.close();
            System.out.println("Текст успішно записано у файл '" + filename + "'");
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }
    
    private static void readFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("Помилка: Файл '" + filename + "' не знайдено.");
                return;
            }
            
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            
            String[] lines = new String[1000]; 
            int lineCount = 0;
            
            String line;
            while ((line = reader.readLine()) != null && lineCount < lines.length) {
                lines[lineCount] = line;
                lineCount++;
            }
            
            reader.close();
            
            if (lineCount > 0) {
                System.out.println("\nВміст файлу '" + filename + "':");
                System.out.println("-".repeat(30));
                
                for (int i = 0; i < lineCount; i++) {
                    System.out.println(lines[i]);
                }
                
                System.out.println("-".repeat(30));
            } else {
                System.out.println("Файл '" + filename + "' порожній.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Помилка: Файл '" + filename + "' не знайдено.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
    
    private static void myNameIsBorat() {
        System.out.println("Моя звай Борат! Нраітса)");
        
        try {
            String url = "https://youtu.be/rB3J3-wk6ds?si=3jKTghwLxgDKX9DE";
            
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("Дабро пажалуста в моя хата...");
            } else {
                System.out.println("Не палучілась зайті в хата.");
                System.out.println("Пастучітесь в моя хата:");
                System.out.println(url);
            }
        } catch (Exception e) {
            System.out.println("Не входіт: " + e.getMessage());
            System.out.println("Пажалста, прахадіте: https://youtu.be/rB3J3-wk6ds?si=3jKTghwLxgDKX9DE");
        }
    }
}