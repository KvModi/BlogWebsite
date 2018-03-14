package com.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.model.Job;
import com.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.*")
public class DBConfiguration 
{
  public DBConfiguration()
  {
	 System.out.println("DBConfiguration class is instantiated"); 
  }
  
  	@Bean
	public SessionFactory sessionFactory() {
  		System.out.println("DBConfiguration : sessionFactory entry ");
		LocalSessionFactoryBuilder lsf=	new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties=new Properties();
		System.out.println("DBConfiguration :sessionFactory : lsf and prop creation ");
		hibernateProperties.setProperty(
				"hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		System.out.println("DBConfiguration : sessionFactory: added properties ");
		//Class classes[]=new Class[]{User.class,Job.class};//class objects of all entities
		System.out.println("DBConfiguration :sessionFactory : exit ");
		lsf.scanPackages("com.*");
	    return lsf.buildSessionFactory();
	}
  	@Bean
	public HibernateTransactionManager hibTransManagement(SessionFactory sessionFactory){
		System.out.println("hibernate transaction manager class called");
		return new HibernateTransactionManager(sessionFactory);
	}
	@Bean
	public DataSource getDataSource() {
		System.out.println("DBConfiguration : getDataSource entry");
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/XE");
	    dataSource.setUsername("TESTS");
	    dataSource.setPassword("tests");
	    System.out.println("DBConfiguration : getDataSource exit");
	    return dataSource;
	    
	}
	
}
