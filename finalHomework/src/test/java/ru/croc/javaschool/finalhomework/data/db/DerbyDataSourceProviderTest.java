package ru.croc.javaschool.finalhomework.data.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.property.PropertyContainer;


/**
 * Тест {@link DerbyDataSourceProvider}.
 */
public class DerbyDataSourceProviderTest {

    private final PropertyContainer propertyContainer = new PropertyContainer("app_test.properties");
    /**
     * Проверка создания источника данных.
     */
    @Test
    public void getDataSourceTest() {
        final DataSourceProvider dataProvider = new DerbyDataSourceProvider(propertyContainer);
        Assertions.assertNotNull(dataProvider);
    }
}
