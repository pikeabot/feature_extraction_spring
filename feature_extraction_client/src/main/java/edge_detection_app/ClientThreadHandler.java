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
import java.lang.Byte;
import javax.imageio.ImageIO;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity; 
import org.springframework.util.LinkedMultiValueMap; 
import org.springframework.util.MultiValueMap; 
import org.springframework.http.HttpMethod; 
import org.springframework.http.ResponseEntity; 

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
        	BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "/tmp/img" + i + ".jpg"));

            RestTemplate restTemplate = new RestTemplate();
            //Resource resource = new FileSystemResource("/Users/user/Documents/MG_0599.jpg");
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			parts.add("Content-Type", "image/jpeg");
			parts.add("byteChunk", EdgeDetectionImageUtil.bufferedImageToByte(img));
            //HttpEntity<MultiValueMap<String, Object>> imageEntity = new HttpEntity<MultiValueMap<String, Object>>(map, imageHeaders);
            ResponseEntity<byte[]> result = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<MultiValueMap<String, Object>>(parts), byte[].class);

            /*
            byte[] result = restTemplate.postForObject(uri, img, byte[].class );
            System.out.println("Sending image");
            */
            processedImg = EdgeDetectionImageUtil.byteToBufferedImage(result.getBody());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	public BufferedImage getImage() {
		return processedImg;
	}
}	

