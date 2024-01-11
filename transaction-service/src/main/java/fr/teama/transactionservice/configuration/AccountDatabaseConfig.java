package fr.teama.transactionservice.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "accountEntityManagerFactory",
        transactionManagerRef = "accountTransactionManager",
        basePackages = {
                "fr.teama.transactionservice.repository.account"
        }
)
public class AccountDatabaseConfig {
    @Bean(name = "accountDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean(name = "accountEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder accountEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean(name = "accountEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(@Qualifier("accountEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("accountDataSource") DataSource dataSource) {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("fr.teama.transactionservice.models.account")
                .persistenceUnit("BankAccount")
                .build();
    }

    @Bean(name = "accountTransactionManager")
    public PlatformTransactionManager accountTransactionManager(@Qualifier("accountEntityManagerFactory") EntityManagerFactory accountEntityManagerFactory) {
        return new JpaTransactionManager(accountEntityManagerFactory);
    }
}
