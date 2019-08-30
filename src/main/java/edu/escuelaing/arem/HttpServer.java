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
