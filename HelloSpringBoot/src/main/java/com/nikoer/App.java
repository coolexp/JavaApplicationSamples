package com.nikoer;

import java.net.URL;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements EmbeddedServletContainerCustomizer
{
	public final static String CHINESE = "Chinese";
	public final static String AMERICAN = "American";

	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext ctx = new FileSystemXmlApplicationContext("bean.xml");
        Human human = null;
        human = (Human) ctx.getBean(CHINESE);
        human.eat();
        human.walk();
        human = (Human) ctx.getBean(AMERICAN);
        human.eat();
        human.walk();
        
        SpringApplication.run(App.class, args);
    }
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container){
    	container.setPort(8088);
    }
}
