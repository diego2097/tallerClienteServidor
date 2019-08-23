package edu.escuelaing.arem;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws MalformedURLException
    {
        URL google = new URL("http://campusvirtual.escuelaing.edu.co/moodle/course/view.php?id=892");
        System.out.println(google.getProtocol()); //Este metodo retorna el protocolo que se esta usando
        System.out.println(google.getAuthority());//Este metodo retorna el host y el puerto 
        System.out.println(google.getHost());//Este metodo retorna el host
        System.out.println(google.getPort());//Este metodo retorna el puerto
        System.out.println(google.getPath());//Este metodo retorna el path de la url 
        System.out.println(google.getQuery());//Este metodo se usa cuando se manda algun formulario y apartir de este se hace una consulta        
        System.out.println(google.getFile());//Este metodo retorna el path de la url junto con el query
        System.out.println(google.getRef());//Este metodo retora lo que va despues de #        
    }
}
