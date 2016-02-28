package edge_detection_app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.web.client.RestTemplate;


public class ClientThreadHandler implements Runnable{
	
	int i;
	URL baseUrl;
	BufferedImage processedImg;
	
	public ClientThreadHandler(URL baseUrl, int i)  {
		this.i = i;
		this.baseUrl = baseUrl;
	}
	@Override
    public void run() {
        try {
        	final String uri = baseUrl + ":808" + Integer.toString(i) + "/runEdgeDetection";
        	
        	BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "\\tmp\\img" + i + ".jpg"));
             
            RestTemplate restTemplate = new RestTemplate();
            byte[] result = restTemplate.getForObject(uri, byte[].class, img);
            processedImg = EdgeDetectionImageUtil.byteToBufferedImage(result);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	public BufferedImage getImage() {
		return processedImg;
	}
}	

