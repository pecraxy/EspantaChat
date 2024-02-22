package com.br.espantazap.domain;

import java.io.*;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class ChatServer {
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private final List<ClientSocket> clients = new LinkedList<>();


    public void start() throws IOException{
        System.out.println("Servidor iniciado na porta " + PORT);
        serverSocket = new ServerSocket(PORT);
        clientConnectionLop();
    }

    private void clientConnectionLop() throws IOException {
        while (true){
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            clients.add(clientSocket);
            new Thread(() -> clientMessageLoop(clientSocket)).start();
        }
    }

    private void clientMessageLoop(ClientSocket clientSocket){
        String msg;
        try{
            while((msg = clientSocket.getMsg()) != null){
                if ("sair".equalsIgnoreCase(msg)) return;
                System.out.printf("Msg recebida do cliente %s: %s\n",
                        clientSocket.getRemoteSocketAddress(),
                        msg);
            }
        } finally {
            clientSocket.close();
        }
    }

    private void sendMsgToAll(ClientSocket sender, String msg){
        for (ClientSocket clientSocket: this.clients){
            if(!sender.equals(clientSocket)){
                clientSocket.sendMsg(msg);
            }
        }
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
