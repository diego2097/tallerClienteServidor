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