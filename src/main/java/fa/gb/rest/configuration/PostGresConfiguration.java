package fa.gb.rest.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class PostGresConfiguration {
	
	private String deploymentUrl = "jdbc:postgresql://db:5432/postgres";
	private String developmentUrl = "jdbc:postgresql://172.17.0.2:5432/FA-GB-DB";
	
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource driver = new DriverManagerDataSource();
	    driver.setDriverClassName("org.postgresql.Driver");
	    driver.setUrl(deploymentUrl);
	    driver.setUsername("postgres");
	    driver.setPassword("postgres");
	    return driver;
	}
	
}
