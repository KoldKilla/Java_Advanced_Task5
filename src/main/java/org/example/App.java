package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class App
{
    public static void main( String[] args )
    {
        String filePath = "D://input.txt"; // Путь к файлу

        try {
            // Чтение всех строк из файла и объединение в один список слов
            List<String> words = Files.lines(Paths.get(filePath))
                    .flatMap(line -> Arrays.stream(line.split("\\s+"))) // Разделение строки на слова
                    .filter(word -> !word.isEmpty()) // Исключение пустых строк
                    .collect(Collectors.toList());

            // Сортировка слов в алфавитном порядке
            Collections.sort(words);

            // Подсчет количества каждого слова
            Map<String, Integer> wordCount = new LinkedHashMap<>();
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            // Вывод статистики на консоль
            System.out.println("Слово - Количество");
            wordCount.forEach((word, count) -> System.out.println(word + " - " + count));

            // Нахождение слова с максимальным количеством повторений
            String maxWord = Collections.max(wordCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            int maxCount = wordCount.get(maxWord);

            // Вывод слова с максимальным количеством повторений
            System.out.println("\nСлово с максимальным количеством повторений: " + maxWord);
            System.out.println("Количество повторений: " + maxCount);

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
