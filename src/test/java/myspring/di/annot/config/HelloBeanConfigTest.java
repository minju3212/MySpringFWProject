package myspring.di.annot.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import myspring.di.annot.HelloBean;
import myspring.di.annot.PrinterBean;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloBeanConfig.class, loader = AnnotationConfigContextLoader.class)
public class HelloBeanConfigTest {
	@Autowired
	HelloBean hello;
	
	@Autowired
	@Qualifier("stringPrinter")
	PrinterBean printer;//printer라고 할거면 위에 qualifier를 쓰고 괄호 안에 하나를 지정해야 함.
	
//	PrinterBean stringPrinter;//stringPrinter라고 안하고 printer라고 하면 string, console 두가지라 밑에서 검증할 때 에러남.
	
	@Test
	void helloBean() {
		assertEquals("Hello 어노테이션", hello.sayHello());
		hello.print();
		assertEquals("Hello 어노테이션", printer.toString());
		
		assertEquals(3, hello.getNames().size());
	}
}
