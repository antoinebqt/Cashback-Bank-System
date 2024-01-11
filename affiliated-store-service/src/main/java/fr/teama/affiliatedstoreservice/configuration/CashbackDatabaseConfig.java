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
        entityManagerFactoryRef = "cashbackEntityManagerFactory",
        transactionManagerRef = "cashbackTransactionManager",
        basePackages = {
                "fr.teama.affiliatedstoreservice.repository.cashback"
        }
)
public class CashbackDatabaseConfig {
    @Bean(name = "cashbackDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cashback")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean(name = "cashbackEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder cashbackEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean(name = "cashbackEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cashbackEntityManagerFactory(@Qualifier("cashbackEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("cashbackDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("fr.teama.affiliatedstoreservice.models.cashback")
                .persistenceUnit("Cashback")
                .build();
    }

    @Bean(name = "cashbackTransactionManager")
    public PlatformTransactionManager cashbackTransactionManager(@Qualifier("cashbackEntityManagerFactory") EntityManagerFactory cashbackEntityManagerFactory) {
        return new JpaTransactionManager(cashbackEntityManagerFactory);
    }
}
