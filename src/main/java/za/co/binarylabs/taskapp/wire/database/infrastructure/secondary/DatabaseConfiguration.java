package za.co.binarylabs.taskapp.wire.database.infrastructure.secondary;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "za.co.binarylabs.taskapp" }, enableDefaultTransactions = false)
class DatabaseConfiguration {}
