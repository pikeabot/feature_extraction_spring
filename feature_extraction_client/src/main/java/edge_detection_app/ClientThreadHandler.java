package edge_detection_app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import edge_detection_app.ImagesHandler;

public class ClientThreadHandler implements Runnable{
	
	int i;
	URL baseUrl;
	
	public ClientThreadHandler(URL baseUrl, int i)  {
		this.i = i;
		this.baseUrl = baseUrl;
	}
	@Override
    public void run() {
        try {
            ImagesHandler imgHandler = new ImagesHandler();
            
            imgHandler.sendImage(baseUrl+"/runEdgeDetection", i);	

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }			
}	

