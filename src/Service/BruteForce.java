package Service;

import static AppConstnt.Alphabet.ALPHABET;
import static AppConstnt.EnglishDictionary.ENG_DICTIONARY;

/**
 * Класс Brute Force принимает параметры типа String помещает его в цыкл перебора ожидаемого результата.
 * В цыкле создаёт переменную сдвиг (Shift) который будет передаваться в качестве ключа в метод дешифровки Decrypted
 * Decrypted метод принимает 2 параметра String переданного текста  int Shift в качестве сдвига(Ключа)
 * Метоз создает обьект StringBuilder для обработки и временного хранения нового результата
 * переменная effectiveShift принимает остаток от деления длинны алфавита для предотвращения выхода
 * за длинну алфавита в цыкле передираем фисволы и смотрим есть ли они в алфавите если есть заменяем на значение сдвига
 * если нет выбрасываеться отрицательное -1 длинны алфавита в таком случае оставляем символ без изменений
 * Получиный результат добавляес в результат и возвращаем его в качестве строки!
 * Метод isTextMeaningful проверяет полученый результат на осмысленность по разделению на не словесных символов на словесные
 * используя оператор регулярных вырожений Метасимвол //W+ за тем сравнивает полученый результат с Директорией
 * Набора слов Английского языка и возвращает Boolean значение для определения Осмысленности текста!
 */
public class BruteForce {

    // Метод дешифровки
    public static String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        int effectiveShift = (shift % ALPHABET.length() + ALPHABET.length()) % ALPHABET.length(); // Приведение к положительному значению

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int index = ALPHABET.indexOf(ch);
            if (index != -1) {
                int newIndex = (index - effectiveShift + ALPHABET.length()) % ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(ch); // Если символ не найден в алфавите, оставить его без изменений
            }
        }
        return result.toString();
    }

    // Метод проверки осмысленности текста
    public static boolean isTextMeaningful(String text) {
        // Разделяем текст на слова, используя регулярное выражение для слов
        String[] words = text.split("\\W+");
        int countDictionary = 0;

        for (String word : words) {
            if (ENG_DICTIONARY.contains(word.toLowerCase())) {
                countDictionary++;
            }
        }
        return countDictionary > 10; // Проверка на наличие достаточного количества слов из словаря
    }

    // Метод перебора возможных значений ключа
    public static String bruteForce(String encryptedText) {
        for (int shift = 0; shift < ALPHABET.length(); shift++) {
            String decryptedText = decrypt(encryptedText, shift);
            if (isTextMeaningful(decryptedText.toLowerCase())) {
                WriteFile.writeFile(decryptedText); // Запись в файл, если найден осмысленный текст
                return decryptedText;
            }
        }
        return null; // Возвращает null, если осмысленный текст не найден
    }
}
