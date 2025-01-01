package com.apelisser.manager;

import com.apelisser.manager.core.i18n.I18NProperties;
import com.apelisser.manager.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties({ I18NProperties.class })
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

}
