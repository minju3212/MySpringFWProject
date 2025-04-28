package myspring.di.xml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import myspring.di.xml.Printer;
import myspring.di.xml.StringPrinter;

@Configuration
@PropertySource(value = "classpath:values.properties")
public class HelloConfig {
	@Bean
	public Printer stringPrinter() {
		return new StringPrinter();
	}
}
