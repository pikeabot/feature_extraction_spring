package edge_detection;
import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;  

public class EdgeDetectionImageUtil {
  
    private static int rows = 2;   //we assume the no. of rows and cols are known and each chunk has equal width and height  
    private static int cols = 2;  
    
	    public static void chunk(String imgPath) throws IOException {  
	  
	        File file = new File(imgPath); // I have bear.jpg in my working directory  
	        FileInputStream fis = new FileInputStream(file);  
	        BufferedImage image = ImageIO.read(fis); //reading the image file  

	        int chunks = rows * cols;  
	  
	        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
	        int chunkHeight = image.getHeight() / rows;  
	        int count = 0;  
	        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks  
	        for (int x = 0; x < rows; x++) {  
	            for (int y = 0; y < cols; y++) {  
	                //Initialize the image array with image chunks  
	                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
	  
	                // draws the image chunk  
	                Graphics2D gr = imgs[count++].createGraphics();  
	                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
	                gr.dispose();  
	            }  
	        }  
	        System.out.println("Splitting done");  
	  
	        //writing mini images into image files  
	        for (int i = 0; i < imgs.length; i++) {  
	            ImageIO.write(imgs[i], "jpg", new File("img" + i + ".jpg"));  
	        }  
	        System.out.println("Mini images created");    
	}
	    
	
	public static void knit(BufferedImage[] buffImages) throws IOException {  
	    
        int chunks = rows * cols;  
  
        int chunkWidth, chunkHeight;  
        int type;  /*
        //fetching image files  
        File[] imgFiles = new File[chunks];  
        for (int i = 0; i < chunks; i++) {  
            imgFiles[i] = new File(imgPath + "img" + i + ".jpg");  
        }  
  
       //creating a bufferd image array from image files  
        BufferedImage[] buffImages = new BufferedImage[chunks];  
        for (int i = 0; i < chunks; i++) {  
            buffImages[i] = ImageIO.read(imgFiles[i]);  
        }  
        */
        type = buffImages[0].getType();  
        chunkWidth = buffImages[0].getWidth();  
        chunkHeight = buffImages[0].getHeight();  
  
        //Initializing the final image  
        BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);  
  
        int num = 0;  
        for (int i = 0; i < rows; i++) {  
            for (int j = 0; j < cols; j++) {  
                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);  
                num++;  
            }  
        }  
        System.out.println("Image concatenated.....");  
        ImageIO.write(finalImg, "jpeg", new File("finalImg.jpg"));
	}
}
