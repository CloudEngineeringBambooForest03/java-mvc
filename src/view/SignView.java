package view;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.dto.UserDTO;

public class SignView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField idField;
	private JTextField passwordField;
	private JTextField signIdField;
	private JTextField signPasswordField;
	private JTextField signNameField;
	private JTextField signNicknameField;
	private Controller c;

	SignView() {
		c = new Controller();
		setResizable(false);
		setTitle("Cloud Forest");
		setBounds(new Rectangle(600, 300, 310, 160));
		getContentPane().setLayout(new CardLayout(0, 0));
		final CardLayout cl = (CardLayout) getContentPane().getLayout();
		JPanel signInPanel = new JPanel();
		getContentPane().add(signInPanel, "signIn");
		signInPanel.setLayout(new FlowLayout(1, 5, 5));
		JPanel signUpPanel = new JPanel();
		getContentPane().add(signUpPanel, "signUp");
		JPanel panel_1 = new JPanel();
		signInPanel.add(panel_1);
		JLabel lblNewLabel = new JLabel("로그인");
		panel_1.add(lblNewLabel);
		JPanel panel = new JPanel();
		signInPanel.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel idPanel = new JPanel();
		((FlowLayout) idPanel.getLayout()).setAlignment(4);
		panel.add(idPanel);
		JLabel idLabel = new JLabel("ID");
		idPanel.add(idLabel);
		idLabel.setLabelFor(idField);
		idField = new JTextField();
		idPanel.add(idField);
		idField.setColumns(10);
		JPanel passwordPanel = new JPanel();
		((FlowLayout) passwordPanel.getLayout()).setAlignment(4);
		panel.add(passwordPanel);
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordPanel.add(passwordLabel);
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordPanel.add(passwordField);
		JPanel signButtonPanel = new JPanel();
		signInPanel.add(signButtonPanel);
		JButton newIdButton = new JButton("아이디 만들기");
		signButtonPanel.add(newIdButton);
		JButton signInButton = new JButton("로그인!");
		signButtonPanel.add(signInButton);
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView.user = c.login(idField.getText(), passwordField.getText());
				if (MainView.user != null) {
					new PostView();
					setVisible(false);
				} else {
					System.out.println("로그인 실패!");
					idField.setText("");
					passwordField.setText("");
				}
			}
		});
		newIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(new Rectangle(600, 300, 310, 220));
				cl.show(getContentPane(), "signUp");
			}
		});
		JPanel panel_1_1 = new JPanel();
		signUpPanel.add(panel_1_1);
		JLabel lblNewLabel_1 = new JLabel("회원가입");
		panel_1_1.add(lblNewLabel_1);
		JPanel panel_2 = new JPanel();
		signUpPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel signIdPanel = new JPanel();
		((FlowLayout) signIdPanel.getLayout()).setAlignment(4);
		panel_2.add(signIdPanel);
		JLabel signIdLabel = new JLabel("ID");
		signIdPanel.add(signIdLabel);
		signIdField = new JTextField();
		signIdField.setColumns(10);
		signIdPanel.add(signIdField);
		JPanel signPasswordPanel = new JPanel();
		((FlowLayout) signPasswordPanel.getLayout()).setAlignment(4);
		panel_2.add(signPasswordPanel);
		JLabel signPasswordLabel = new JLabel("PASSWORD");
		signPasswordPanel.add(signPasswordLabel);
		signPasswordField = new JTextField();
		signPasswordField.setColumns(10);
		signPasswordPanel.add(signPasswordField);
		JPanel signNamePanel = new JPanel();
		((FlowLayout) signNamePanel.getLayout()).setAlignment(4);
		panel_2.add(signNamePanel);
		JLabel signNameLabel = new JLabel("이름");
		signNamePanel.add(signNameLabel);
		signNameField = new JTextField();
		signNameField.setColumns(10);
		signNamePanel.add(signNameField);
		JPanel signNicknamePanel = new JPanel();
		((FlowLayout) signNicknamePanel.getLayout()).setAlignment(4);
		panel_2.add(signNicknamePanel);
		JLabel signNicknameLabel = new JLabel("닉네임");
		signNicknamePanel.add(signNicknameLabel);
		signNicknameField = new JTextField();
		signNicknameField.setColumns(10);
		signNicknamePanel.add(signNicknameField);

		JPanel signUpButtonPanel = new JPanel();
		signUpPanel.add(signUpButtonPanel);
		JButton signUpBackButton = new JButton("뒤로가기");
		signUpButtonPanel.add(signUpBackButton);
		JButton signUpCompleteButton = new JButton("아이디 생성");
		signUpButtonPanel.add(signUpCompleteButton);
		signUpCompleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDTO user = UserDTO.builder().id(signIdField.getText()).password(signPasswordField.getText())
						.nickName(signNicknameField.getText()).name(signNameField.getText()).build();
				if (c.signUp(user)) {
				} else {
				}
			}
		});
		signUpBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(new Rectangle(600, 300, 310, 160));
				cl.show(getContentPane(), "signIn");
			}
		});
		setDefaultCloseOperation(3);
		setVisible(true);
	}
}
