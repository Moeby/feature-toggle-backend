package com.moeby.featuretoggle;

import com.moeby.featuretoggle.service.FeatureToggleService;
import com.moeby.featuretoggle.service.PersonService;
import com.moeby.featuretoggle.service.converter.FeatureToggleConverter;
import com.moeby.featuretoggle.service.converter.PersonConverter;
import com.moeby.featuretoggle.service.converter.StatusConverter;
import com.moeby.featuretoggle.service.dao.impl.FeatureToggleDAO;
import com.moeby.featuretoggle.service.dao.impl.PersonDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FeatureToggleApplication {

	@Autowired
	private SessionFactory sessionFactory;

	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleApplication.class, args);
	}

	@Bean
	public FeatureToggleDAO featureToggleDAO() {
		return new FeatureToggleDAO(sessionFactory);
	}

	@Bean
	public PersonDAO personDAO() {
		return new PersonDAO(sessionFactory);
	}

	@Bean
	public FeatureToggleConverter featureToggleConverter() {
		return new FeatureToggleConverter(personService(), statusConverter());
	}

	@Bean
	public FeatureToggleService featureToggleService() {
		return new FeatureToggleService(featureToggleDAO(), featureToggleConverter());
	}

	@Bean
	public PersonService personService() {
		return new PersonService(personDAO(), personConverter());
	}

	@Bean
	public StatusConverter statusConverter() {
		return new StatusConverter();
	}

	@Bean
	public PersonConverter personConverter() {
		return new PersonConverter();
	}

}
