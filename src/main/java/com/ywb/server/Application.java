package com.ywb.server;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@MapperScan("com.ywb.server.mapper")
public class Application {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://xzh2.cloudapp.net:5432/ywbserver");
		ds.setUsername("wjbb");
		ds.setPassword("wjbb111");
		return ds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource());
		//sfb.setConfigLocation(new Resource("classpath:Mybatis3-Config.xml"));
		return sfb.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		DataSourceTransactionManager trans = new DataSourceTransactionManager();
		trans.setDataSource(dataSource());
		return trans;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    	
    	@Autowired
    	private SecurityProperties security;
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated().and()
    			.formLogin()
    			//.loginPage("/login").loginProcessingUrl("/loginprocess").failureUrl("/login?error")
    			.permitAll();
    	}
    	
    	public void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth.inMemoryAuthentication().withUser("xueyu").password("xueyu123")
    				.roles("ADMIN", "USER").and().withUser("duanlei").password("duanlei123")
    				.roles("USER");
    	}
    	
    	
    }
}
