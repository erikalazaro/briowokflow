
package com.briomax.briobpm.rest.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.briomax.briobpm.persistence.config.JPAConfig;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
@ComponentScans({@ComponentScan("com.briomax.briobpm")})
@Slf4j
@EnableAsync
@EnableScheduling
public class BackBrioBPMApplication extends SpringBootServletInitializer { 

	/**
	 * El método principal.
	 * @param args los argumentos.
	 */
	public static void main(String[] args) {
		log.info(" Initializing the Application BackBrioBPM >>>>>>>>>>>>>>>>>>>> ");
		SpringApplication.run(BackBrioBPMApplication.class, args);
		log.info("<<<<<<<<<<<<<<<<<<<< Successful initialization of the application BackBrioBPM.");
	}

	/** 
	 * {@inheritDoc}
	 * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}
	
	/**
	 * Configure application.
	 * @param builder the builder
	 * @return the spring application builder
	 */
	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(BackBrioBPMApplication.class).bannerMode(Banner.Mode.OFF);
	}

}
