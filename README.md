# Taller clientes y servicios - AREP

### git clone https://github.com/diego2097/tallerClienteServidor

## Construido en
- Maven: una herramienta de software para la gestión y construcción de proyectos java

## Prerrequisitos
Se debe tener instalados los siguientes programas en nuestro sistema operativo: 
- Maven 
- Git
- Java

## Instalacion

### Descagar maven
descarga en http://maven.apache.org/download.html 
para la instalacion seguir las instrucciones http://maven.apache.org/download.html#Installation
para comprobar que quedo bien instalado usar el comando: 

''' 
mvn -version
'''

### Descagar git
Para descargar e instalar seguir las instrucciones en https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

### Descargar java 
Para descagar Java acudir al link https://www.java.com/es/download/win10.jsp



## taller 

### Ejercicio 1 
Escriba un programa en el cual usted cree un objeto URL e imprima en pantalla cada uno de los datos que retornan los 8 metodos:

- getProtocol
- getAuthority
- getHost
- getPort
- getPath
- getQuery
- getFile
- getRef

Cree la clase App, en el metodo main utilice los 8 metodos como se puede observar: 
```java
package edu.escuelaing.arem;

import java.net.MalformedURLException;
import java.net.URL;

java
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
```

### Ejercicio 2

Escriba una aplicacion browser que pregunte una direccion URL al usuario y que lea datos de esa direccion y que los almacene en un archivo con el nombre resultado.html.
Luego intente ver este archivo en el navegador.

```java
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
```
### Ejercicio 4.3

Escriba un servidor que reciba un numero y responda el cuadrado de este numero.
```java 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author USUARIO
 */
public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();

        } catch (IOException e) {

            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine = "";
        int numero;
        while ((inputLine = in.readLine()) != null) {
            try {
                numero = Integer.parseInt(inputLine);
                numero = (int) Math.pow(numero, 2);
                System.out.println("Mensaje:" + numero);
                outputLine = "Respuesta: " + numero;
                out.println(outputLine);
            } catch (NumberFormatException e) {
                if (inputLine.equals("Bye")) {
                    outputLine = "Respuesta: " + inputLine;
                    out.println(outputLine);
                    break;
                } else {
                    out.println("Ingrese un numero");
                }
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
```

```java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author USUARIO
 */
public class Cliente {

        public static void main(String[] args) throws IOException {
            
            Socket socket = null;
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                socket = new Socket("127.0.0.1", 4445);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(
                          socket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Don’t know about host!.");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn’t get I/O for "
                        + "the connection to: localhost.");
                System.exit(1);
            }

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
            out.close();
            in.close();
            stdIn.close();
            socket.close();
        }
    }
```
### Ejercicio 4.5.1

Escriba un servidor web que soporte m´ultiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo
paginas html e imagenes.

```
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author USUARIO
 */
public class HttpServer {

    public static void main(String[] args) throws IOException {
        boolean seguir = true;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(12345);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        while (seguir) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            String path = controlRequests(in);
            if (path.equals("/html1")) {
                html1(out);
            }
            if (path.equals("/html2")) {
                html2(out);
            }
            if (path.equals("/img1")) {
                img1(out);
            }
            if (path.equals("/img2")) {
                img2(out);
            }

            System.out.println(path);

        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static String controlRequests(BufferedReader in) throws IOException {
        String inputLine;
        String path = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (!in.ready()) {
                break;
            }
            if (inputLine.contains("GET")) {
                String[] get = inputLine.split(" ");
                path = get[1];
            }
        }
        return path;
    }

    private static void html1(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html" + "\r\n");
        out.println("<!DOCTYPE html>" + "\r\n");
        out.println("<html>" + "\r\n");
        out.println("<head>" + "\r\n");
        out.println("<meta charset=\"UTF-8\">" + "\r\n");
        out.println("<title>Title of the document</title>" + "\r\n");
        out.println("</head>" + "\r\n");
        out.println("<body>" + "\r\n");
        out.println("<h1>My Web Site</h1>" + "\r\n");
        out.println("</body>" + "\r\n");
        out.println("</html>" + "\r\n");
        out.flush();
    }

    private static void html2(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html" + "\r\n");
        out.println("<!DOCTYPE html>" + "\r\n");
        out.println("<html>" + "\r\n");
        out.println("<head>" + "\r\n");
        out.println("<meta charset=\"UTF-8\">" + "\r\n");
        out.println("<title>Title of the document</title>" + "\r\n");
        out.println("</head>" + "\r\n");
        out.println("<body>" + "\r\n");
        out.println("<h1>My Second Web Site</h1>" + "\r\n");
        out.println("</body>" + "\r\n");
        out.println("</html>" + "\r\n");
        out.flush();
    }

    private static void img2(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html" + "\r\n");
        out.println("<!DOCTYPE html>" + "\r\n");
        out.println("<html>" + "\r\n");
        out.println("<head>" + "\r\n");
        out.println("<meta charset=\"UTF-8\">" + "\r\n");
        out.println("<title>Title of the document</title>" + "\r\n");
        out.println("</head>" + "\r\n");
        out.println("<body>" + "\r\n");
        out.println("<img src=\"https://www.tekcrispy.com/wp-content/uploads/2017/12/bancos-imagenes-gratis.jpg\"></img>" + "\r\n");
        out.println("</body>" + "\r\n");
        out.println("</html>" + "\r\n");
        out.flush();

    }

    private static void img1(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html" + "\r\n");
        out.println("<!DOCTYPE html>" + "\r\n");
        out.println("<html>" + "\r\n");
        out.println("<head>" + "\r\n");
        out.println("<meta charset=\"UTF-8\">" + "\r\n");
        out.println("<title>Title of the document</title>" + "\r\n");
        out.println("</head>" + "\r\n");
        out.println("<body>" + "\r\n");
        out.println("<img src=\"https://cdn.pixabay.com/photo/2018/03/03/03/11/tree-3194803_960_720.jpg\"></img>" + "\r\n");
        out.println("</body>" + "\r\n");
        out.println("</html>" + "\r\n");
        out.flush();
    }

}
```

### 5.2. Ejercicios

Utilizando Datagramas escriba un programa que se conecte a un servidor que responde la hora actual en el servidor. El programa debe actualizar la horacada 5 segundos seg´un los datos del servidor. Si una hora no es recibida debe
mantener la hora que tenla. Para la prueba se apagar´a el servidor y despues de unos segundos se reactivar´a. El cliente debe seguir funcionando y actualizarse
cuando el servidor este nuevamente funcionando.
```
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2125509
 */
public class DatagramTimeClient {

    public static void main(String[] args) {
        byte[] sendBuf = new byte[256];
        try {
            while (true) {
                DatagramSocket socket = new DatagramSocket();
                byte[] buf = new byte[256];
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Date: " + received);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```

```java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2125509
 */
public class DatagramTimeServer {

    DatagramSocket socket;

    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startServer() {
        byte[] buf = new byte[256];
        int cont = 0;
        while (true) {
            cont = 0;
            while (cont < 5) {
                try {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    String dString = new Date().toString();
                    buf = dString.getBytes();
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    packet = new DatagramPacket(buf, buf.length, address, port);
                    socket.send(packet);
                    cont++;
                } catch (IOException ex) {
                    Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        DatagramTimeServer ds = new DatagramTimeServer();
        ds.startServer();
    }
}

```












## Autor  
- Diego Alejandro Corredor Tolosa 

## Licencia 
- GNU General Public License v3.0

