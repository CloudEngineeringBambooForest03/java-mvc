package view;

import controller.Controller;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.dto.PostDTO;

public class CreateView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField title;
	private JTextArea content;
	private Controller c;

	CreateView() {
		c = new Controller();
		setBounds(new Rectangle(600, 300, 450, 260));
		setTitle("Cloud Forest");
		getContentPane().setLayout((LayoutManager) null);
		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setBounds(12, 25, 57, 15);
		getContentPane().add(lblNewLabel);
		title = new JTextField();
		title.setBounds(81, 22, 340, 21);
		getContentPane().add(title);
		title.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("내용");
		lblNewLabel_1.setBounds(12, 59, 57, 15);
		getContentPane().add(lblNewLabel_1);
		content = new JTextArea();
		content.setLineWrap(true);
		content.setRows(5);
		content.setBounds(81, 53, 340, 69);
		getContentPane().add(content);
		JButton btnWrite = new JButton("완료");
		btnWrite.setBounds(306, 180, 116, 23);
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.createPost(PostDTO.builder().userName(MainView.user.getName())
						.title(title.getText()).content(content.getText()).build());
				new PostView();
				setVisible(false);
			}
		});
		getContentPane().add(btnWrite);
		JButton btnBack = new JButton("뒤로가기");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PostView();
				setVisible(false);
			}
		});
		btnBack.setBounds(180, 180, 116, 23);
		getContentPane().add(btnBack);
		setDefaultCloseOperation(3);
		setVisible(true);
	}
}
