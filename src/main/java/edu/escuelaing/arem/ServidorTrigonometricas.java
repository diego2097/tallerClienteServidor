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
public class ServidorTrigonometricas {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(34000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 34000.");
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

        String inputLine, outputLine = "", operacion = "fun:cos";
        double numero;
        int bandera=0;
        while ((inputLine = in.readLine()) != null) {
            try {
                numero = Double.parseDouble(inputLine);
                switch (bandera) {
                    case 0:
                        numero = Math.cos(numero); 
                        break;
                    case 1:
                        numero = Math.sin(numero); 
                        break;
                    case 2:
                        numero = Math.tan(numero); 
                        break;
                    default:
                        break;
                }
                System.out.println("Mensaje:" + numero);
                outputLine = "Respuesta: " + numero;
                out.println(outputLine);
            } catch (NumberFormatException e) {
                if(inputLine.equals("fun:cos")){
                    bandera = 0;
                    outputLine = "operaciones coseno";
                    out.println(outputLine);
                }
                else if(inputLine.equals("fun:sin")){
                    bandera = 1;
                    outputLine = "operaciones seno";
                    out.println(outputLine);
                }
                else if(inputLine.equals("fun:tan")){
                    bandera = 2;
                    outputLine = "operaciones tangente";
                    out.println(outputLine);
                }
                else if(inputLine.equals("Bye")){
                    outputLine = "Respuesta: " + inputLine;
                    out.println(outputLine);
                    break;
                }
                else {
                    out.println("Comando invalido.");
                }
                
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }
}
