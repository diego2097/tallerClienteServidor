/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author 2125509
 */
public class UrlFileReader {
     public static void main( String[] args ) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String path = in.readLine();
        String data = "";
        URL url = new URL(path);
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                data += inputLine;
                System.out.println(inputLine);
            }
        } catch (IOException x) {}
        
        
        BufferedWriter buffer = null;
	FileWriter writer = null;
        
	try {
            File pagina = new File("pagina.html");
            pagina.createNewFile();
            writer = new FileWriter(pagina.getAbsoluteFile(), true);
            buffer = new BufferedWriter(writer);
            buffer.write(data);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        finally{
               try{
                   buffer.close();
                   writer.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }      
    }
}
