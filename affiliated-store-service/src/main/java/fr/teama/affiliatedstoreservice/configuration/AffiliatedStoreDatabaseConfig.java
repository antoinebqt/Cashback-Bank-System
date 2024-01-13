package fr.teama.affiliatedstoreservice.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "affiliatedStoreEntityManagerFactory",
        transactionManagerRef = "affiliatedStoreTransactionManager",
        basePackages = {
                "fr.teama.affiliatedstoreservice.repository.affiliatedstore"
        }
)
public class AffiliatedStoreDatabaseConfig {
    @Bean(name = "affiliatedStoreDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.affiliatedstore")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean(name = "affiliatedStoreEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder affiliatedStoreEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean(name = "affiliatedStoreEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean affiliatedStoreEntityManagerFactory(@Qualifier("affiliatedStoreEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("affiliatedStoreDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("fr.teama.affiliatedstoreservice.models.affiliatedstore")
                .persistenceUnit("AffiliatedStore")
                .build();
    }

    @Bean(name = "affiliatedStoreTransactionManager")
    public PlatformTransactionManager affiliatedStoreTransactionManager(@Qualifier("affiliatedStoreEntityManagerFactory") EntityManagerFactory affiliatedStoreEntityManagerFactory) {
        return new JpaTransactionManager(affiliatedStoreEntityManagerFactory);
    }
}
