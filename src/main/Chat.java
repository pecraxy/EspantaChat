/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import net.miginfocom.swing.MigLayout;
import service.ClientService;
import service.ServerService;
import service.SocketService;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;

import model.Message;
import model.User;

import javax.swing.GroupLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Chat extends javax.swing.JFrame implements Runnable{
	
	private javax.swing.JButton cmdRight;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JPanel panel;
	private javax.swing.JTextArea txt;
	private static final String SERVER_ADDRESS = "127.0.0.1";
    private SocketService clientSocket;
    private User user;
	
	
    public Chat() {
        initComponents();
        panel.setLayout(new MigLayout("fillx"));
    }
    
    public Chat(User user) {
    	this();
    	this.user = user;
    }
    
    public void start() throws IOException {
    	try{
            this.clientSocket = new SocketService(new Socket(SERVER_ADDRESS, ServerService.PORT), user);
            System.out.println("Você se conectou ao servidor " + SERVER_ADDRESS + ":" + ServerService.PORT);
            new Thread(this).start();
        } finally {
            if (clientSocket.equals(null)) return;
            clientSocket.close();
        }
    }
    
    @Override
    public void run() {
    	Message msg;
        while ((msg = clientSocket.getMsg()) != null){
            cmdLeftActionPerformed(msg.getMsg());
        }
    }
    
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        //cmdLeft = new javax.swing.JButton();
        cmdRight = new javax.swing.JButton();
        cmdRight.setFont(new Font("Tahoma", Font.BOLD, 15));
        cmdRight.setBounds(EXIT_ON_CLOSE, ABORT, 200, 95);
        cmdRight.setPreferredSize(new Dimension(450, 9));
        cmdRight.setMinimumSize(new Dimension(350, 95));
        cmdRight.setMaximumSize(new Dimension(100, 95));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1051, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane2.setViewportView(txt);

//        cmdLeft.setText("Left");
//        cmdLeft.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                cmdLeftActionPerformed(evt);
//            }
//        });

        cmdRight.setText("Enviar");
        txt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					cmdRightActionPerformed(null);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        cmdRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdRight, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRight)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
        cmdRight.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
    }

    private void cmdLeftActionPerformed(String msg) {//GEN-FIRST:event_cmdLeftActionPerformed
        String text = txt.getText().trim();
        Item_Left item = new Item_Left(text);
        panel.add(item, "wrap, w 80%");
        panel.repaint();
        panel.revalidate();
    }

    private void cmdRightActionPerformed(java.awt.event.ActionEvent evt) {
        String text = txt.getText().trim();
        if (text.isEmpty()) return;
        if (clientSocket.sendMsg(new Message(user, text))) {
        	Item_Right item = new Item_Right(text);
            panel.add(item, "wrap, w 80%, al right");
            txt.setText("");
            panel.repaint();
            panel.revalidate();
        }
        
    }
    
    
    public static void main(String args[], User user) {
       Chat chat = new Chat(user);
       try {
    	   chat.setVisible(true);
    	   chat.start();
       } catch (IOException | NullPointerException e) {
    	   JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao servidor. Inicie o servidor novamente ou cheque se seu firewall está bloqueando a liberação das portas.");
       }
    }
}

