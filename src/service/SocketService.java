package service;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

import model.Message;
import model.User;

//a
public class SocketService {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private User user;

    public SocketService(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Cliente " + socket.getRemoteSocketAddress() + " se conectou.");
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
    }
    
    public SocketService(Socket socket, User user) throws IOException {
    	this(socket);
    	this.user = user;
    }
 
    public User getUser() {
    	return this.user;
    }
    
    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public Message getMsg(){
        try{
        	String rawMessage = in.readLine();
        	String rawUser = rawMessage.substring(0, rawMessage.indexOf(':'));
        	User newUser = new User(rawUser);
        	Message msg = new Message(newUser, rawMessage);
            return msg;
        }catch (IOException e){
        	e.printStackTrace();
            return null;
        }
    }

    public boolean sendMsg(Message msg){
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
