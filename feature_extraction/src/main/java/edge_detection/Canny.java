/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge_detection;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*; 
import java.util.Arrays;
/**
 *
 * @author jchang
 */
public class Canny {
    
    public String home() {
		return "Hello Docker World from Canny";
	}
    public BufferedImage uploadImage() {
        Image img = null;
        BufferedImage bimage = null;
        try {
      
            System.out.println("Reading image from disk. ");
            img = ImageIO.read(new File("C:\\Users\\jchang\\Pictures\\images.jpg"));
            img = GrayFilter.createDisabledImage(img);
                // Create a buffered image with transparency
            bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return bimage;
    }
    
    public Image runCanny(BufferedImage img) {
        try {
            img = applyGaussianFilter(img, 5);
            //img = findIntensityGradients(img);
            //img = applyNonMaximumSuppresion(img);
            //img = applyDoubleThreshold(img);
            //img = getEdges(img);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return img;
    }
    
    private BufferedImage applyGaussianFilter(BufferedImage img, int kernel_size) {
        int[][] gfilter = {{2,4,5,4,2},
                            {4,9,12,9,4},
                            {5,12,15,12,65},
                            {4,9,12,9,4},
                            {2,4,5,4,2}};
        int mask_sum = 159;
        
        for (int i=0; i < img.getWidth()-kernel_size; i++) {
            for (int j=0; j < img.getHeight()-kernel_size; j++) {
                img.setRGB(i, j, getConvolution(getArraySlice(img, i, j, kernel_size), gfilter, mask_sum));
            }
        }
        return img;
    }
    
    private int[][] getArraySlice(BufferedImage img, int startX, int startY, int kernel_size) {
        int[][] arraySlice = new int[kernel_size][];
            int k = 0;
            int l = 0;
            for (int i = startX; i < startX+kernel_size ; i++) {
                for (int j = startY; j < startY+kernel_size; j++) {
                    arraySlice[k][l] = img.getRGB(i, j);
                    k++;
                    l++;
                }     
    }
        return arraySlice;
    }
    
    private int getConvolution(int[][] imgSlice, int[][]gfilter, int maskSum) {
        int conv = 0;
            for (int i = 0; i < imgSlice.length; i++) {
                for (int j = 0; i < imgSlice.length; j++) {
                    conv = conv + (imgSlice[i][j] * gfilter[i][j] * 1/maskSum);
                }
            }
        return conv;
    }
    private BufferedImage findIntensityGradients(BufferedImage img) {
        return img;
    }
    
    private BufferedImage applyNonMaximumSuppresion(BufferedImage img) {
        return img;
    }
    private BufferedImage applyDoubleThreshold(BufferedImage img) {
        return img;
    }
    private BufferedImage getEdges(BufferedImage img) {
        return img;
    }
    
    public void displayImage(BufferedImage img) {
        JFrame f = new JFrame("test");
        ImageIcon icon = new ImageIcon(img);
        JLabel l = new JLabel();

        l.setIcon(icon);
        f.add(l);
        f.pack();
        f.setVisible(true);
    }
}
