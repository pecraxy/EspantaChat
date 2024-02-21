package com.br.espantazap.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private BufferedReader in;


    public void start() throws IOException{
        System.out.println("Servidor iniciado na porta " + PORT);
        serverSocket = new ServerSocket(PORT);
        clientListening();
    }

    private void clientListening() throws IOException {
        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " se conectou.");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String msg = in.readLine();
            System.out.println(msg);
        }
    }

    public int getPORT() {
        return PORT;
    }

    public static void main(String[] args) {
        try{
            ChatServer server = new ChatServer();
            server.start();
        } catch (IOException e){
            System.out.println("Erro ao iniciar o servidor:\n" + e.getMessage());
        }
        System.out.println("Servidor finalizado.");
    }
}
