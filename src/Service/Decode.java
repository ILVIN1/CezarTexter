package Service;

import static AppConstnt.Alphabet.ALPHABET;

public class Decode {

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        int effectiveKey = key % ALPHABET.length(); // Обработка ключа, чтобы он не превышал длину алфавита

        for (int i = 0; i < text.length(); i++) {   //Цыкл перебора символов
            char ch = text.charAt(i);
            int index = ALPHABET.indexOf(ch); // Получение индекса символа из константы
            if (index != -1) {                // Проверка есть ли текущий Символ в Константе
                int newIndex = (index + effectiveKey) % ALPHABET.length();  // Получение сдвинутого символа для шифрации и добавление!
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(ch); // Неизвестные символы остаются без изменений
            }
        }
        return result.toString();  //Возврат результата в виде строки
    }

    //  Метод Разшифровки использует Шифрование в обратном порядке !!
    public static String decrypt(String text, int key) {
        try {
            int effectiveKey = (ALPHABET.length() - (key % ALPHABET.length())) % ALPHABET.length();
            String result = encrypt(text, effectiveKey);
            WriteFile.writeFile(result);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
