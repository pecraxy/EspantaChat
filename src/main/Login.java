package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.User;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField caixaTexto;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 619);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, "name_674817081092800");
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(67, 124, 231));
		panel_1.setBounds(0, 0, 828, 572);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/oscar.png")));
		lblNewLabel.setBounds(10, 11, 295, 561);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 245, 245));
		panel_2.setBounds(372, -2, 455, 577);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("EspantaCHAT");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(31, 23, 411, 97);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 60));
		
		JLabel lblTexto = new JLabel("Insira seu apelido.");
		lblTexto.setBounds(80, 235, 295, 59);
		panel_2.add(lblTexto);
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		
		caixaTexto = new JTextField();
		caixaTexto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		caixaTexto.setBackground(new Color(255, 255, 255));
		caixaTexto.setForeground(new Color(0, 0, 0));
		caixaTexto.setBounds(80, 300, 295, 50);
		panel_2.add(caixaTexto);
		caixaTexto.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("O seu local de fofoca.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_2.setBounds(101, 104, 341, 69);
		panel_2.add(lblNewLabel_2);
		
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(129, 377, 197, 43);
		panel_2.add(btnEntrar);

		btnEntrar.addActionListener(event -> {
			initChat();
        });
		
		btnEntrar.setEnabled(false);
		
		JLabel txtCopyright = new JLabel("© 2024 Os Espanta Mulheres - Todos os direitos reservados.");
		txtCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		txtCopyright.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCopyright.setBounds(10, 546, 432, 20);
		panel_2.add(txtCopyright);
		
		JLabel lblNewLabel_3 = new JLabel("Verifique se o servidor está online.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(115, 530, 231, 14);
		panel_2.add(lblNewLabel_3);
		
		caixaTexto.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				if (!caixaTexto.getText().isBlank()) {
					btnEntrar.setEnabled(true);
				} else {
					btnEntrar.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					if (btnEntrar.isEnabled()) {
						initChat();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
	}

	public void initChat() {
		User user = new User(caixaTexto.getText());
		this.dispose();
		Chat chat = new Chat(user);
		chat.start();
	}
	
}
