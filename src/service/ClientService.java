package service;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import model.Message;
import model.User;
//o
public class ClientService implements Runnable{
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private SocketService clientSocket;
    private final Scanner in;
    private User user;

    public ClientService(){
        in = new Scanner(System.in);
    }
    
    public ClientService(User user) {
    	this();
    	this.user = new User("zé");
    }

    public void start() throws IOException {
        try{
            clientSocket = new SocketService(new Socket(SERVER_ADDRESS, ServerService.PORT), user);
            System.out.println("Você se conectou ao servidor " + SERVER_ADDRESS + ":" + ServerService.PORT);
            new Thread(this).start();
            messageLoop();
        } finally {
            clientSocket.close();
        }
    }

    @Override
    public void run(){
        Message msg;
        while ((msg = clientSocket.getMsg()) != null){
            System.out.printf("\n%s\n" , msg.getMsg());
        }
    }

    private void messageLoop() {
        Message msg;
        String rawMessage;
        do {
            System.out.print("Digite uma mensagem (ou sair para finalizar): ");
            rawMessage = in.nextLine();
            msg = new Message(user, rawMessage);
            clientSocket.sendMsg(msg);
        } while (!rawMessage.equalsIgnoreCase("sair"));
    }

    public static void main(String[] args) {
        ClientService client = new ClientService();
        try {
            client.start();
            System.out.println("Chat do Cliente iniciado.");
        } catch (IOException e) {
            System.out.println("Foi encontrado uma exceção.\n" + e.getMessage());
        }
        System.out.println("Cliente finalizado.");
    }
}
