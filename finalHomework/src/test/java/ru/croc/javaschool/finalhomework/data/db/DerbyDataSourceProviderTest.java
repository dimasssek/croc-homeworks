package ru.croc.javaschool.finalhomework.data.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

/**
 * Тест {@link DerbyDataSourceProvider}.
 */
public class DerbyDataSourceProviderTest {

    /**
     * Проверка создания источника данных.
     */
    @Test
    public void getDataSourceTest() {
        final DataSource dataSource = new DerbyDataSourceProvider().getDataSource();
        Assertions.assertNotNull(dataSource);
    }
}
