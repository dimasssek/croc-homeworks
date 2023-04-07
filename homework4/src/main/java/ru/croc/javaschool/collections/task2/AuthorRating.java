package ru.croc.javaschool.collections.task2;

import java.util.*;

import static java.util.Collections.replaceAll;

/**
 * Рейтинг авторов.
 */
public class AuthorRating {
    /**
     * Статьи авторов.
     */
    private List<String> articles;

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
     * @return рейтинг авторов.
     */
    public Map<String, Double> getAuthorsRating() {
        Map<String, Double> authorsRating = new HashMap<>();
        for (String article: articles) {
            String[] partsOfArticle = article.split(";");

            List<String> title = List.of(toFormat(partsOfArticle[0]));
            List<String> text = List.of(toFormat(partsOfArticle[2]));

        }

    }

    /**
     * Преобразование строк в требуемый для работы вид.
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
     * Считает количество вхождений слова из названия статьи в её тексте.
     *
     * @param word       слово
     * @param occurrencesMap словарь слов из текста статьи с их количеством вхождений
     * @return количество вхождений указанного слова в тексте статьи
     */
    private int countOccurrences(String word, Map<String, Integer> occurrencesMap) {
        return occurrencesMap.getOrDefault(word, 0);
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

}
