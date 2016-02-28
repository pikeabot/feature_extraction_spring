package edge_detection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;  

@RestController
public class AppController {

    @RequestMapping(value="/runEdgeDetection", method=RequestMethod.GET, produces = "image/jpg")
    public ResponseEntity<byte[]>  processImage(@RequestBody byte[] byteChunk) throws IOException{
    	try {
    		//read in image
    		InputStream in = new ByteArrayInputStream(byteChunk);
    		//convert to buffered image
    		BufferedImage imgChunk = ImageIO.read(in);
    		
	        // Run edge detection
	        BufferedImage processedImage = EdgeDetection.runEdgeDetection(imgChunk);
	
	        // Create a byte array output stream.
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        // Write to output stream
	        ImageIO.write(processedImage, "jpg", baos);
	        baos.flush();
	        byte[] imageInByte = baos.toByteArray();
	    	baos.close();
	    	//create headers
	    	final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);

	        return new ResponseEntity<byte[]>(imageInByte, headers, HttpStatus.CREATED);
	        
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	 	
    }
}
