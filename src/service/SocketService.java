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
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
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
        	if (socket.isClosed()) return null;
        	String tempMsg = in.readLine();
        	if (tempMsg == null || tempMsg.equals("end")) return null;
        	String rawMsg = tempMsg.substring(tempMsg.indexOf(':') + 2, tempMsg.length());
        	String tempUser = tempMsg.substring(0, tempMsg.indexOf(':'));
        	User newUser = new User(tempUser);
        	Message msg = new Message(newUser, rawMsg);
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
    public boolean sendMsg(User user, boolean endConn) {
    	try {
    		out.println(user.getNickname()+ ": end");
			socket.shutdownOutput();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
