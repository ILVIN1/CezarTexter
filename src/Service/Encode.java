package Service;

import static AppConstnt.Alphabet.ALPHABET;

/**
 * Класс использует метод для шифрования данныйх который принимает текст String и int ключ по которому и
 * Будет просиходить смещение символов методом Цезаря !!!
 */
public class Encode {
    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        int effectiveKey = key % ALPHABET.length(); // Обработка ключа, чтобы он не превышал длину алфавита

        for (int i = 0; i < text.length(); i++) {   // Цикл перебора символов
            char ch = text.charAt(i);
            int index = ALPHABET.indexOf(ch); // Получение индекса символа из константы
            if (index != -1) { // Проверка есть ли текущий символ в константе
                int newIndex = (index + effectiveKey) % ALPHABET.length(); // Получение сдвинутого символа для шифрации
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(ch); // Неизвестные символы остаются без изменений
            }
        }
        WriteFile.writeFile(result.toString());
        return result.toString(); // Возврат результата в виде строки
    }
}
