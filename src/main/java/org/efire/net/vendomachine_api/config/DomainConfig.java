package org.efire.net.vendomachine_api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = {"org.efire.net.vendomachine_api.domain"})
@EnableJpaRepositories(basePackages = {"org.efire.net.vendomachine_api.repos"})
@EnableTransactionManagement
public class DomainConfig {
}
