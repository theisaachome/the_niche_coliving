package com.theniche.colivin.config;

import com.theniche.core.domain.common.BaseRepository;
import com.theniche.core.domain.repository.AccountRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages ={"com.theniche.core.domain.repository"}, repositoryBaseClass = BaseRepository.class )
public class RepositoryConfig {
}
