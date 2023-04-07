package ru.croc.javaschool.collections.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Тест для {@link AuthorRating}.
 */
public class AuthorRatingTest {
    /**
     * Первый, второй, третий список строк.
     */
    private List<String>
            articleFirst,
            articleSecond,
            articleThird;

    /**
     * Инициализация списков строк.
     */
    @BeforeEach
    public void init() {

        articleFirst = Arrays.asList(
                "Java 11 выпущена;Первый автор;Java 11 была выпущена...",
                "Kotlin vs Java;Второй автор;Kotlin набирает популярность...",
                "Java concurrency;Третий автор;Параллельность важный аспект программирования...");

        articleSecond = Arrays.asList(
                "Интересный пРимЕр;Первый автор;Если ждешь интересный пример, то примера не будет.",
                "САША и влад;Второй автор;Саша и Владислав лекция бесплатно смс",
                "Книга;Третий автор;Книги. их 9. а тут 0."
        );

        articleThird = Arrays.asList(
                "Стакан;Первый автор;Стакан",
                "Кружка;Второй автор;Кружка",
                "Глоточек пива;Первый автор;Светлое нефильтрованное"
        );
    }

    /**
     * Первый тест сортировки рейтинга авторов по частоте использования слов.
     */
    @Test
    public void getFirstAuthorsRating() {
        AuthorRating authorRating = new AuthorRating(articleFirst);
        Map<String, Double> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Второй автор", 75.0);
        expectedMap.put("Первый автор", ((double) 1 / 3) * 100);
        expectedMap.put("Третий автор", 0.0);

        Assertions.assertEquals(expectedMap, authorRating.getAuthorsRating());
    }
    /**
     * Второй тест сортировки рейтинга авторов по частоте использования слов.
     */
    @Test
    public void getSecondAuthorsRating() {
        AuthorRating authorRating = new AuthorRating(articleSecond);
        Map<String, Double> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Второй автор", ((double) 1 / 3) * 100);
        expectedMap.put("Первый автор", ((double) 1 / 4) * 100);
        expectedMap.put("Третий автор", 0.0);

        Assertions.assertEquals(expectedMap, authorRating.getAuthorsRating());
    }
    /**
     * Третий тест сортировки рейтинга авторов по частоте использования слов.
     */
    @Test
    public void getThirdAuthorsRating() {
        AuthorRating authorRating = new AuthorRating(articleThird);
        Map<String, Double> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Второй автор", 100.0);
        expectedMap.put("Первый автор", 50.0);

        Assertions.assertEquals(expectedMap, authorRating.getAuthorsRating());
    }
}
