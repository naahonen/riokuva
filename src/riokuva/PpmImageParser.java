
package riokuva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class PpmImageParser {
    
        private BufferedReader br;
        private static int width, height, maxcolours;
        private static int[] image;
        
        PpmImageParser(BufferedReader passedBufferedReader) {
            this.br = passedBufferedReader;
            parsePpmHeader();
        }
        
        PpmImageParser(File passedFile) {
            this.br = openBufferedReaderForFile(passedFile);
        }
        
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getMaxcolours() {
        return maxcolours;
    }
    
    public int[] getPpmImageData() {
        parsePpmData();
        return image;
    }
    
    @Override
    public String toString() {
        return  "Width: "+this.getWidth()
                +", height: "+this.getHeight()
                +", maxcolours: "+this.getMaxcolours();
    }
    
        private BufferedReader openBufferedReaderForFile(File file) {
        try {
            BufferedReader newBr = new BufferedReader(new FileReader(file));
            return newBr;
        } catch(IOException e) {
            System.err.println(e);
        }
        
        return null;
    }
    
    private void parsePpmHeader() {
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
    
    private void parsePpmData() {
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
    
    private static void concParsePpmData(BufferedReader br) {
        
    }
    
}
