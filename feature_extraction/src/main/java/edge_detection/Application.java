package edge_detection;

import edge_detection.EdgeDetection;
import edge_detection.EdgeDetectionImageUtil.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;  

@SpringBootApplication
@RestController
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	
    @RequestMapping("/")

	public static void main(String[] args) throws IOException{
		//SpringApplication.run(Application.class, args);
    	int chunks = 4;
		long startTime = System.currentTimeMillis();
		String imgPath = "C:\\Users\\jchang\\Pictures\\SF_5_26_2014\\DSC_0131.jpg";
		BufferedImage[] buffImgs = new BufferedImage[chunks];
		System.out.println("getting image data");
		EdgeDetectionImageUtil.chunk(imgPath);
		for (int i=0; i<16; i++) {
			buffImgs[i] = EdgeDetection.runEdgeDetection("C:\\Users\\jchang\\workspace\\feature_extraction\\feature_extraction\\img" + i + ".jpg");
		}
		EdgeDetectionImageUtil.knit(buffImgs);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;    
		System.out.println(elapsedTime);
	}
}
