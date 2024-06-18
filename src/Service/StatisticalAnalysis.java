package Service;

import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalysis {
        // Ожидаемые частоты букв в английском языке (можно заменить на другой язык при необходимости)
        private static final Map<Character, Double> EXPECTED_FREQUENCIES = new HashMap<>();
        static {
            EXPECTED_FREQUENCIES.put('a', 8.2);
            EXPECTED_FREQUENCIES.put('b', 1.5);
            EXPECTED_FREQUENCIES.put('c', 2.8);
            EXPECTED_FREQUENCIES.put('d', 4.3);
            EXPECTED_FREQUENCIES.put('e', 13.0);
            EXPECTED_FREQUENCIES.put('f', 2.2);
            EXPECTED_FREQUENCIES.put('g', 2.0);
            EXPECTED_FREQUENCIES.put('h', 6.1);
            EXPECTED_FREQUENCIES.put('i', 7.0);
            EXPECTED_FREQUENCIES.put('j', 0.2);
            EXPECTED_FREQUENCIES.put('k', 0.8);
            EXPECTED_FREQUENCIES.put('l', 4.0);
            EXPECTED_FREQUENCIES.put('m', 2.4);
            EXPECTED_FREQUENCIES.put('n', 6.7);
            EXPECTED_FREQUENCIES.put('o', 7.5);
            EXPECTED_FREQUENCIES.put('p', 1.9);
            EXPECTED_FREQUENCIES.put('q', 0.1);
            EXPECTED_FREQUENCIES.put('r', 6.0);
            EXPECTED_FREQUENCIES.put('s', 6.3);
            EXPECTED_FREQUENCIES.put('t', 9.1);
            EXPECTED_FREQUENCIES.put('u', 2.8);
            EXPECTED_FREQUENCIES.put('v', 1.0);
            EXPECTED_FREQUENCIES.put('w', 2.4);
            EXPECTED_FREQUENCIES.put('x', 0.2);
            EXPECTED_FREQUENCIES.put('y', 2.0);
            EXPECTED_FREQUENCIES.put('z', 0.1);
        }

        // Метод для анализа частоты букв в тексте
        public static double calculateChiSquared(String text) {
            text = text.toLowerCase().replaceAll("[^a-z]", ""); // Приведение текста к нижнему регистру и удаление не-буквенных символов

            // Подсчет фактических частот букв
            Map<Character, Integer> actualFrequencies = new HashMap<>();
            for (char ch : text.toCharArray()) {
                actualFrequencies.put(ch, actualFrequencies.getOrDefault(ch, 0) + 1);
            }

            // Рассчет chi-squared статистики
            double chiSquared = 0.0;
            int totalChars = text.length();
            for (char ch : EXPECTED_FREQUENCIES.keySet()) {
                double observed = actualFrequencies.getOrDefault(ch, 0);
                double expected = totalChars * (EXPECTED_FREQUENCIES.get(ch) / 100.0); // Преобразуем в проценты
                if (expected > 0) {
                    chiSquared += Math.pow(observed - expected, 2) / expected;
                }
            }

            return chiSquared;
        }

        // Метод для определения, является ли текст правильно расшифрованным
        public static boolean isDecryptedText(String text) {
            double chiSquared = calculateChiSquared(text);
            // Пороговое значение для определения правильности расшифровки (можно настроить)
            double threshold = 20.0;
            return chiSquared < threshold;
        }
    }

