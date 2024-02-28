package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import service.ClientService;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Font;
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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 850);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, "name_674817081092800");
		panel.setLayout(null);
		
		JPanel navbar = new JPanel();
		navbar.setBackground(new Color(114, 224, 133));
		navbar.setBounds(0, 0, 576, 133);
		panel.add(navbar);
		navbar.setLayout(null);
		
		JLabel lblTitulo = new JLabel("EspantaZAP");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(91, 27, 401, 83);
		navbar.add(lblTitulo);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 60));
//		Image img = new ImageIcon(this.getClass().getResource("/oscar.png")).getImage();
		
		JLabel imagem = new JLabel();
		//imagem.setIcon(new ImageIcon(Login.class.getResource("/com/br/espantazap/images/oscar.png")));
		imagem.setBounds(-40, 131, 238, 560);
		panel.add(imagem);
		
		JLabel lblTexto = new JLabel("Digite o seu nome");
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setBounds(271, 198, 295, 59);
		panel.add(lblTexto);
		
		caixaTexto = new JTextField();

		caixaTexto.setBounds(281, 268, 285, 50);
		panel.add(caixaTexto);
		caixaTexto.setColumns(10);
		
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(event -> {
			User user = new User(caixaTexto.getText());
			this.dispose();
			Chat chat = new Chat(user);
			chat.start();
        });
		
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
		btnEntrar.setEnabled(false);
		btnEntrar.setBounds(361, 359, 140, 43);
		panel.add(btnEntrar);
	}
}
