package com.apelisser.manager;

import com.apelisser.manager.core.i18n.I18NProperties;
import com.apelisser.manager.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties({ I18NProperties.class })
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ManagerApplication {

	public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ManagerApplication.class, args);
	}

}
