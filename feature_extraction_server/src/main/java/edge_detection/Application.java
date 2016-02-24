package edge_detection;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;


@SpringBootApplication
public class Application {
    
	public static BufferedImage main(String[] args){
		BufferedImage bImage=null;
		ServerSocket server=null;
        Socket socket;
        
        try {	
        	
            server = new ServerSocket(4000);
            System.out.println("Server Waiting for image");

            socket = server.accept();
            System.out.println("Client connected.");
            
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);

            int len = dis.readInt();
            System.out.println("Image Size: " + len/1024 + "KB");
            
            byte[] data = new byte[len];
            dis.readFully(data);
            dis.close();
            in.close();

            InputStream ian = new ByteArrayInputStream(data);
            bImage = ImageIO.read(ian);
       
           

        } catch (Exception e) {
            System.out.println("Failed to upload => " + e.getMessage());
        }
        return EdgeDetection.runEdgeDetection(bImage);
	}
	
}
