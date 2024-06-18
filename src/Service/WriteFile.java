package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteFile {
    /**
     * Клас имеющий лишь 1 метод для записи результатов обработаный данных В файл !!
     * Для записи используеться клас Files по указаному Path пути !!
     * @param textWrite
     */
    public static void writeFile(String textWrite) {
        try {
            String outputFilePath = "D:\\FileResult.txt"; // Создаем новый файл для записи
            Files.write(Path.of(outputFilePath), textWrite.getBytes()); // Записываем результат в файл
            System.out.println("Result save in: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Sorry it's ERROR in processed");
            e.printStackTrace();
        }
    }
}
