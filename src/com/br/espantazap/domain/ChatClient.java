package com.br.espantazap.domain;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner in;
    private PrintWriter out;

    public ChatClient(){
        in = new Scanner(System.in);
    }

    public void start() throws IOException {

        System.out.println("Cliente conectado ao servidor " + SERVER_ADDRESS + ":" + ChatServer.PORT);
        clientSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        messageLoop("zezao");
    }

    private void messageLoop(String nome) throws IOException {
        String msg;
        do {
            System.out.print("Digite uma mensagem (ou sair para finalizar): ");
            msg = in.nextLine();
            out.println(nome + ": " + msg);
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
