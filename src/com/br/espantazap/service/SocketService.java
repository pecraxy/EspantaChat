package com.br.espantazap.domain;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

//a
public class ClientSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private String name;

    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Cliente " + socket.getRemoteSocketAddress() + " se conectou.");
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
    }
    
    public ClientSocket(Socket socket, String name) throws IOException {
    	this(socket);
    	this.name = name;
    }
    
    
    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public String getMsg(){
        try{
            return this.in.readLine();
        }catch (IOException e){
            return null;
            //System.out.println(e.getMessage());
        }
    }


    public boolean sendMsg(String msg){
        out.println(msg);
        return !out.checkError();
    }


    public void close()  {
        try{
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar.");
        }
    }
}
