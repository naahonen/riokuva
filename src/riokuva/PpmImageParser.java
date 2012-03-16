/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package riokuva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 *
 * @author elmerfudd
 */
public class PpmImageParser {
        final static int BUFFERSIZE = 8192;
        private static int width, height, maxcolours;
        private static int[] image;
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        final long endTime;
        final long startTime = System.nanoTime();
        final String filename = args[0]; 

        int[] kuva = readPpmImage(filename);

        endTime = System.nanoTime();
        long readTime = (endTime - startTime)/1000000;
        System.out.println("read time (ms): " + readTime);
    }
        
    public static int[] readPpmImage(String filename) {
        try {
            File imageFile = new File(filename); 
            BufferedReader br = new BufferedReader(new FileReader(imageFile), BUFFERSIZE);
            
            parsePpmHeader(br);

            parsePpmData(br);
            
            return image;
            
        } catch (IOException e) {
            System.err.println(e);
        }
        // jos jotain outoa tapahtui ilman poikkeusta
        return null;
    }   
    
    private static void parsePpmHeader(BufferedReader br) {
       int headerTemp;
     
       try {
            if(!br.readLine().equalsIgnoreCase("P3")) 
                throw new IOException("Väärä magic number, osataan vain ASCII PPM (P3)");
            
            StringTokenizer t = new StringTokenizer(br.readLine());            
            headerTemp = Integer.parseInt(t.nextToken());
            if(headerTemp > 0) { 
                width = headerTemp;
            } else throw new IOException("Leveysarvo on negatiivinen!");
            
            headerTemp = Integer.parseInt(t.nextToken());
            if(headerTemp > 0) { 
                height = headerTemp;
            } else throw new IOException("Korkeusarvo on negatiivinen!");
            
            headerTemp = Integer.parseInt(br.readLine());
            if(headerTemp > 0) { 
                maxcolours = headerTemp;
            } else throw new IOException("Maxcolours on negatiivinen!");
            

    } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    private static void parsePpmData(BufferedReader br) {
        String s;  
        int j = 0;
        image = new int[width*height*3];
        
        try {
            while((s = br.readLine()) != null) {
                StringTokenizer tok = new StringTokenizer(s);
                while(tok.hasMoreTokens()) {
                    image[j++] = Integer.parseInt(tok.nextToken());
                }
            }
                } catch (IOException e) {
                    System.err.println(e);
            }
    }
}