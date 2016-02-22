package edge_detection;

import java.awt.image.BufferedImage;
import java.io.IOException;
import edge_detection.EdgeDetection;
import edge_detection.EdgeDetectionImageUtil.*;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;  

@Controller
public class AppController {
	
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "HELLO!");
        return "hello";
    }
    /*
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    
    @RequestMapping("/run")
    public void home() throws IOException {
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
 	*/
}
