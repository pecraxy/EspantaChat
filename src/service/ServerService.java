package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Message;

public class ServerService {
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private final List<SocketService> clients = new LinkedList<>();


    public void start() throws IOException{
        System.out.println("Servidor iniciado na porta " + PORT);
        serverSocket = new ServerSocket(PORT);
        clientConnectionLop();
        //ClientService chatClient = new ClientService();
    }

    private void clientConnectionLop() throws IOException {
        while (true){
            SocketService clientSocket = new SocketService(serverSocket.accept());
            clients.add(clientSocket);
            new Thread(() -> clientMessageLoop(clientSocket)).start();
        }
    }

    private void clientMessageLoop(SocketService clientSocket){
        Message msg;
        try{
            while((msg = clientSocket.getMsg()) != null){
                if ("sair".equalsIgnoreCase(msg.getMsg())) return;
                System.out.printf("%s\n", msg.getMsg());
                sendMsgToAll(clientSocket, msg);
            }
        } finally {
            clientSocket.close();
        }
    }

    private void sendMsgToAll(SocketService sender, Message msg){
        Iterator<SocketService> iterator = clients.iterator();
        while (iterator.hasNext()){
            SocketService clientSocket = (SocketService) iterator.next();
            if(!sender.equals(clientSocket)){
                if(!clientSocket.sendMsg(msg)){
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        try{
            ServerService server = new ServerService();
            server.start();
        } catch (IOException e){
            System.out.println("Erro ao iniciar o servidor:\n" + e.getMessage());
        }
        System.out.println("Servidor finalizado.");
    }
}
