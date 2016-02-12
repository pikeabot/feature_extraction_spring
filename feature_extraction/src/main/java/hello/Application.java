package hello;

import edge_detection.Canny;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@SpringBootApplication
@RestController
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	
    @RequestMapping("/")
	public String home() {
            Canny canny = new Canny();
		return canny.home();
	}


	public static void main(String[] args) throws IOException{
		//SpringApplication.run(Application.class, args);
            Canny canny = new Canny();
            System.out.println("getting image data");
            canny.runCanny(canny.uploadImage());
	}
}
