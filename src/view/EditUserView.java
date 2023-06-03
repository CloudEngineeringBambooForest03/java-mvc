package view;

import controller.Controller;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditUserView extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField id;
	private JTextField password;
	private JTextField nickname;
	private Controller c;

	EditUserView() {
		c = new Controller();
		setBounds(new Rectangle(600, 300, 400, 200));
		getContentPane().setLayout((LayoutManager) null);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		JLabel lblNewLabel = new JLabel("회원 정보 수정");
		panel_1.add(lblNewLabel);
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel idPanel = new JPanel();
		((FlowLayout) idPanel.getLayout()).setAlignment(4);
		panel.add(idPanel);
		JLabel idLabel = new JLabel("ID");
		idPanel.add(idLabel);
		idLabel.setLabelFor(id);
		id = new JTextField(MainView.user.getId());
		id.setEditable(false);
		idPanel.add(id);
		id.setColumns(10);
		JPanel passwordPanel = new JPanel();
		((FlowLayout) passwordPanel.getLayout()).setAlignment(4);
		panel.add(passwordPanel);
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordPanel.add(passwordLabel);
		password = new JTextField(MainView.user.getPassword());
		password.setColumns(10);
		passwordPanel.add(password);
		JPanel nicknamePanel = new JPanel();
		((FlowLayout) nicknamePanel.getLayout()).setAlignment(4);
		JLabel nicknameLabel = new JLabel("닉네임");
		nicknamePanel.add(nicknameLabel);
		nickname = new JTextField(MainView.user.getNickName());
		nickname.setColumns(10);
		nicknamePanel.add(nickname);
		panel.add(nicknamePanel);
		JPanel signButtonPanel = new JPanel();
		getContentPane().add(signButtonPanel);
		JButton btnDelete = new JButton("탈퇴하기");
		signButtonPanel.add(btnDelete);
		JButton btnBack = new JButton("뒤로가기");
		signButtonPanel.add(btnBack);
		JButton btnEdit = new JButton("수정하기");
		signButtonPanel.add(btnEdit);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.delete(MainView.user.getName());
				new SignView();
				setVisible(false);
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PostView();
				setVisible(false);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView.user.setPassword(password.getText());
				MainView.user.setNickName(nickname.getText());
				c.update(MainView.user);
				new PostView();
				setVisible(false);
			}
		});
		setDefaultCloseOperation(3);
		setVisible(true);
	}
}
