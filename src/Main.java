import java.util.*;

public class Main {
    public static void main(String[] args) {
        String [] wordsArray = {"apple", "banana", "apple", "orange", "banana", "grape", "kiwi",
                "orange", "apple", "mango", "kiwi"};

        List<String> wordsList = Arrays.asList(wordsArray);

        Set<String> uniqueWords = new HashSet<>(wordsList); //уникальные слова

        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : wordsList) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        // Выводим список уникальных слов и количество их встречаний
        System.out.println("Уникальные слова: " + uniqueWords);
        for (String uniqueWord : uniqueWords) {
            System.out.println("Слово: " + uniqueWord + " встречается - " + wordCountMap.get(uniqueWord) + " раз(а)");}
    }
}

