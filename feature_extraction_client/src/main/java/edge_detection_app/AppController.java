package edge_detection_app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;  

import edge_detection_app.ClientThreadHandler;

@Controller
public class AppController {
	
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Ready....");
        return "index";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException{
    	int chunks = 4;
	
        if (!file.isEmpty()) {
            try {
            	
                byte[] imageInBytes = file.getBytes();
            	InputStream in = new ByteArrayInputStream(imageInBytes);
            	  
            	BufferedImage image = ImageIO.read(in);
            	
        		long startTime = System.currentTimeMillis();

        		BufferedImage[] buffImgs = new BufferedImage[chunks];
        		System.out.println("getting image data");
        		//Split the original image into subimages and save to tmp dir
        		EdgeDetectionImageUtil.chunk(image);
            	Thread[] t = new Thread[chunks];
        		for (int i=1; i<chunks+1; i++) {
        			 URL baseUrl = new URL("http://localhost");
	        	     BufferedImage img = null; 
	        	     ClientThreadHandler newClient = new ClientThreadHandler(baseUrl, i);
	        	     t[i] = new Thread(newClient);
	        	     t[i].start();
	        	     buffImgs[i] = newClient.getImage();
        		}
        		//Combine the images back into one image
        		EdgeDetectionImageUtil.knit(buffImgs);
        		long stopTime = System.currentTimeMillis();
        		long elapsedTime = stopTime - startTime;    
        		System.out.println(elapsedTime);
        		return "Edge detection complete in " + Long.toString(elapsedTime);
            } catch (Exception e) {
                return "Failed to upload => " + e.getMessage();
            }
        } else {
            return "Upload failed because the file was empty.";
        }
        
    }
 	
}
