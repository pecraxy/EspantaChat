package com.br.espantazap.domain;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient implements Runnable{
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private ClientSocket clientSocket;
    private Scanner in;

    public ChatClient(){
        in = new Scanner(System.in);
    }

    public void start() throws IOException {
        clientSocket = new ClientSocket(new Socket(SERVER_ADDRESS, ChatServer.PORT));
        System.out.println("Cliente conectado ao servidor " + SERVER_ADDRESS + ":" + ChatServer.PORT);
        new Thread(this).start();
        messageLoop();
    }

    @Override
    public void run(){
        String msg;
        while ((msg = clientSocket.getMsg()) != null){
            System.out.printf("Msg recebida de: %s\n", msg);
        }
    }

    private void messageLoop() throws IOException {
        String msg;
        do {
            System.out.print("Digite uma mensagem (ou sair para finalizar): ");
            msg = in.nextLine();
            clientSocket.sendMsg(msg);

        } while (!msg.equalsIgnoreCase("sair"));
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        try {
            client.start();
            System.out.println("Chat do Cliente iniciado.");
        } catch (IOException e) {
            System.out.println("Foi encontrado uma exceção.\n" + e.getMessage());
        }
        System.out.println("Cliente finalizado.");
    }
}
