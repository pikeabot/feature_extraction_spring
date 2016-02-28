package edge_detection_app;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.xml.ws.spi.http.HttpExchange;

import com.sun.net.httpserver.HttpHandler;


public class ImagesHandler {
	
	public void sendImage(String urlString, int i) throws IOException {

		HttpURLConnection connection=null;
		ByteArrayOutputStream baos = null;
		try {
		    //URL url = new URL("http://www.somewebsite.com");
			URL url = new URL(urlString);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setDoOutput(true);
		    connection.setRequestProperty("Content-Type", "image/jpeg");
		    connection.setRequestMethod("POST");
		    baos = new ByteArrayOutputStream();
		    BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "\\tmp\\img" + i + ".jpg"));
		    baos.write(extractBytes(img)); // your bytes here         
		    baos.writeTo(connection.getOutputStream());
		    
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);

			ByteArrayInputStream in = new ByteArrayInputStream(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
		}
		finally {
		    if(baos != null)
		       baos.close();
		    if(connection != null) 
		    	connection.disconnect();
		}  
	}
	
		public byte[] extractBytes (BufferedImage img) throws IOException {
			 // get DataBufferBytes from Raster
			 WritableRaster raster = img.getRaster();
			 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

			 return ( data.getData() );
			}
	}
