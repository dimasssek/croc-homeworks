package ru.croc.javaschool.finalhomework.data.db;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.finalhomework.property.PropertyContainer;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Реализует провайдер источника данных для Derby.
 */
public class DerbyDataSourceProvider implements DataSourceProvider {
    /**
     * Доступ к Derby.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Получение источника данных для Derby.
     *
     * @return источник данных
     */
    @Override
    public DataSource getDataSource() {
        if (Objects.isNull(dataSource)) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(PropertyContainer.getProperty("database.name"));
            var username = PropertyContainer.getProperty("database.username");
            var password = PropertyContainer.getProperty("database.password");
            if (!username.isEmpty() && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }
        return dataSource;
    }
}
