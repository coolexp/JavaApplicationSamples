package spring.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
public class App 
{
	@Bean
    MessageService mockMessageService() {
        return new MessageService() {
            public String getMessage() {
              return "Hello World!";
            }
        };
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context =  new AnnotationConfigApplicationContext(App.class);
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        printer.printMessage();
    }
}
