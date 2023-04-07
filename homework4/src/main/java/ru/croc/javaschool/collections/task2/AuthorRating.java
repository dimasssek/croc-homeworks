package ru.croc.javaschool.collections.task2;

import java.util.*;

/**
 * Рейтинг авторов.
 */
public class AuthorRating {
    /**
     * Статьи авторов.
     */
    private final List<String> articles;

    /**
     * Создаёт {@link AuthorRating}.
     *
     * @param articles список строк, являющихся статьями по шаблону:
     *                 "<Название статьи>;<Автор>;<Текст статьи>".
     */
    public AuthorRating(List<String> articles) {
        this.articles = articles;
    }

    /**
     * Рейтинг авторов по частоте использования слов из названия статьи в тексте.
     *
     * @return рейтинг авторов.
     */
    public Map<String, Double> getAuthorsRating() {
        Map<String, Double> authorsRating = new HashMap<>();
        Map<String, ArrayList<Double>> authorFrequencies = new HashMap<>();

        for (String article : articles) {
            String[] partsOfArticle = article.split(";");

            List<String> title = List.of(toFormat(partsOfArticle[0]));
            List<String> text = List.of(toFormat(partsOfArticle[2]));

            Map<String, Integer> textWithOccurrences = countWordOccurrences(text);
            Map<String, Integer> occurrencesOfWords = new HashMap<>();
            for (String word : title) {
                if (textWithOccurrences.containsKey(word)) {
                    occurrencesOfWords.put(word, countOccurrences(word, textWithOccurrences));
                }
            }
            double fullFrequencies = sumOfFrequencies(text, occurrencesOfWords);
            authorFrequencies.computeIfAbsent(partsOfArticle[1], k -> new ArrayList<>()).add(fullFrequencies);
        }

        for (Map.Entry<String, ArrayList<Double>> author : authorFrequencies.entrySet()) {
            if (author.getValue().size() == 0) {
                authorsRating.put(author.getKey(), 0.0);
            } else {
                double sumForAvg = 0.0;
                for (Double element : author.getValue()) {
                    sumForAvg += element;
                }
                sumForAvg = (sumForAvg / (double) author.getValue().size()) * 100;
                authorsRating.put(author.getKey(), sumForAvg);
            }
        }

        return sortAuthorsRating(authorsRating);
    }

    /**
     * Вспомогательная функция для подсчета общей частоты.
     *
     * @param text               слова в тексте.
     * @param occurrencesOfWords мапа, где ключ - это слово, значение - сколько раз встретилось.
     * @return общая частота.
     */
    private double sumOfFrequencies(
            List<String> text,
            Map<String, Integer> occurrencesOfWords
    ) {
        double sumOfFrequencies = 0.0;
        for (Map.Entry<String, Integer> entry : occurrencesOfWords.entrySet()) {
            sumOfFrequencies += (double)entry.getValue() / (double) text.size();
        }
        return sumOfFrequencies;
    }

    /**
     * Преобразование строк в требуемый для работы вид.
     *
     * @param partOfArticle название, автор или текст статьи
     * @return отформатированные строки.
     */
    private String[] toFormat(String partOfArticle) {
        return partOfArticle
                .replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", "")
                .toLowerCase()
                .strip()
                .split("\\s+");
    }

    /**
     * Считает количество вхождений каждого слова в текст.
     *
     * @param words список всех слов из текста.
     * @return словарь слов с количеством их вхождений в текст.
     */
    private Map<String, Integer> countWordOccurrences(List<String> words) {
        Map<String, Integer> wordsOccurrences = new HashMap<>();
        for (String word : words) {
            wordsOccurrences.put(word, wordsOccurrences.getOrDefault(word, 0) + 1);
        }
        return wordsOccurrences;
    }

    /**
     * Считает количество вхождений слова из названия статьи в её тексте.
     *
     * @param word           слово
     * @param occurrencesMap словарь слов из текста статьи с их количеством вхождений
     * @return количество вхождений указанного слова в тексте статьи
     */
    private int countOccurrences(String word, Map<String, Integer> occurrencesMap) {
        return occurrencesMap.getOrDefault(word, 0);
    }

    /**
     * Вспомогательная функция. Сортирует авторов по частоте использования слов из названия в тексте.
     *
     * @param unsortedRating неотсортированный рейтинг.
     * @return отсортированный рейтинг.
     */
    private Map<String, Double> sortAuthorsRating(Map<String, Double> unsortedRating) {
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(unsortedRating.entrySet());

        entryList.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Double> author1,
                               Map.Entry<String, Double> author2) {
                return author2.getValue().compareTo(author1.getValue());
            }
        });

        Map<String, Double> sortedAuthorsRating = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entryList) {
            sortedAuthorsRating.put(entry.getKey(), entry.getValue());
        }
        return sortedAuthorsRating;
    }
}
