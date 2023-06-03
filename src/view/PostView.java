package view;

import controller.Controller;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.dto.CommentDTO;
import model.dto.PostDTO;
import javax.swing.UIManager;

public class PostView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable commentTable;
	private JLabel detailTitle;
	private JTextArea detailContentText;
	private JLabel detailUser;
	private JTextField commentField;
	private JPanel detailButtonPanel;
	private PostDTO post;
	private CardLayout cl;
	private Controller c;

	int selectedRow;

	int selectedComment;

	public void refreshDetail(int postId) {
		post = c.readPost(postId);
		detailTitle.setText(post.getTitle());
		detailUser.setText(post.getUserName());
		detailContentText.setText(post.getContent());
		JButton btnDelete = new JButton("글 삭제");
		JButton btnUpdate = new JButton("글 수정");

		if (post.getUserName().equals(MainView.user.getName())) {
			detailButtonPanel.add(btnDelete);
			detailButtonPanel.add(btnUpdate);

			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.deletePost(postId);
					refreshTable();
					cl.show(getContentPane(), "main");
				}
			});

			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new UpdateView(post);
					setVisible(false);
				}
			});
		}

		List<CommentDTO> commentList = post.getCommentList();
		String[] colNames = { "", "댓글 내용", "작성자", "좋아요 수" };
		Object[][] rowDatas = new Object[commentList.size()][colNames.length];

		for (int i = 0; i < commentList.size(); i++) {
			rowDatas[i] = new Object[] { commentList.get(i).getCommentId(), commentList.get(i).getContent(),
					commentList.get(i).getUserName(), commentList.get(i).getLikeCnt() };
		}

		commentTable.setModel(new DefaultTableModel(rowDatas, (Object[]) colNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		commentTable.getColumnModel().getColumn(0).setResizable(false);
		commentTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		commentTable.getColumnModel().getColumn(1).setResizable(false);
		commentTable.getColumnModel().getColumn(1).setPreferredWidth(350);
		commentTable.getColumnModel().getColumn(2).setResizable(false);
		commentTable.getColumnModel().getColumn(2).setPreferredWidth(45);
		commentTable.getColumnModel().getColumn(3).setResizable(false);
		commentTable.getColumnModel().getColumn(3).setPreferredWidth(45);
	}

	public void refreshTable() {
		List<PostDTO> postList = c.readAllPosts();
		String[] colNames = { "", "제목", "작성자", "조회 수", "댓글 수" };
		Object[][] rowDatas = new Object[postList.size()][colNames.length];

		for (int i = 0; i < postList.size(); i++) {
			rowDatas[postList.size() - i - 1] = new Object[] { postList.get(i).getPostId(), postList.get(i).getTitle(),
					postList.get(i).getUserName(), postList.get(i).getViewCnt(),
					postList.get(i).getCommentList().size() };
		}
		table.setModel(new DefaultTableModel(rowDatas, (Object[]) colNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(45);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
	}

	public PostView() {
		c = new Controller();
		setResizable(false);
		setTitle("Cloud Forest");
		setBounds(new Rectangle(600, 300, 600, 400));
		getContentPane().setLayout(new CardLayout(0, 0));
		cl = (CardLayout) getContentPane().getLayout();
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(UIManager.getBorder("Button.border"));
		mainPanel.setLayout(new BoxLayout(mainPanel, 1));
		getContentPane().add(mainPanel, "main");
		JPanel searchPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) searchPanel.getLayout();
		flowLayout.setAlignment(2);
		mainPanel.add(searchPanel);
		JButton btnEditUser = new JButton("회원정보 수정");
		searchPanel.add(btnEditUser);

		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditUserView();
				setVisible(false);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedRow = table.getSelectedRow();
				refreshDetail((int) table.getValueAt(selectedRow, 0));
				cl.show(getContentPane(), "detail");
			}
		});
		refreshTable();
		scrollPane.setViewportView(table);
		JPanel writePanel = new JPanel();
		FlowLayout fl_writePanel = new FlowLayout(2, 5, 5);
		fl_writePanel.setAlignOnBaseline(true);
		writePanel.setLayout(fl_writePanel);
		JButton btnWrite = new JButton("글 작성");
		writePanel.add(btnWrite);
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateView();
				setVisible(false);
			}
		});
		mainPanel.add(writePanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, 1));
		JPanel detailPanel = new JPanel();
		getContentPane().add(detailPanel, "detail");
		detailPanel.setLayout(new BoxLayout(detailPanel, 1));
		detailPanel.setBorder(UIManager.getBorder("Button.border"));
		JPanel detailTitlePanel = new JPanel();
		detailPanel.add(detailTitlePanel);
		detailTitlePanel.setLayout(new FlowLayout(3, 5, 5));
		detailTitle = new JLabel("detailTitle");
		detailTitle.setHorizontalAlignment(2);
		detailTitle.setFont(new Font("굴림", 1, 14));
		detailTitlePanel.add(detailTitle);
		JPanel detailTitlePanel_1 = new JPanel();
		detailPanel.add(detailTitlePanel_1);
		detailTitlePanel_1.setLayout(new FlowLayout(3, 5, 5));
		detailUser = new JLabel("detailUser");
		detailTitlePanel_1.add(detailUser);
		JPanel detailContentPanel = new JPanel();
		detailPanel.add(detailContentPanel);
		detailContentPanel.setLayout(new BoxLayout(detailContentPanel, 0));
		detailContentText = new JTextArea();
		detailContentText.setRows(20);
		detailContentText.setLineWrap(true);
		detailContentText.setEditable(false);
		detailContentPanel.add(detailContentText);
		JScrollPane commentScrollPanel = new JScrollPane();
		detailPanel.add(commentScrollPanel);
		commentTable = new JTable();
		commentScrollPanel.setViewportView(commentTable);
		JLabel label = new JLabel("New label");
		commentScrollPanel.setColumnHeaderView(label);
		commentTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedComment = commentTable.getSelectedRow();
			}
		});
		JPanel commentPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) commentPanel.getLayout();
		flowLayout_1.setAlignment(2);
		detailPanel.add(commentPanel);
		commentField = new JTextField();
		commentField.setColumns(40);
		commentPanel.add(commentField);
		JButton btnComment = new JButton("댓글 작성");
		commentPanel.add(btnComment);
		detailButtonPanel = new JPanel();
		detailPanel.add(detailButtonPanel);
		FlowLayout fl_detailButtonPanel = new FlowLayout(2, 5, 5);
		detailButtonPanel.setLayout(fl_detailButtonPanel);
		JButton btnBack = new JButton("뒤로가기");
		detailButtonPanel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
				cl.show(getContentPane(), "main");
			}
		});
		JButton btnCommentLike = new JButton("댓글 좋아요");
		detailButtonPanel.add(btnCommentLike);
		JButton btnCommentDelete = new JButton("댓글 삭제");
		detailButtonPanel.add(btnCommentDelete);
		JButton btnCommentUpdate = new JButton("댓글 수정");
		detailButtonPanel.add(btnCommentUpdate);

		btnCommentLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.doLike(MainView.user.getName(), ((int) commentTable.getValueAt(selectedComment, 0)));
				refreshDetail(((int) table.getValueAt(selectedRow, 0)));
			}
		});

		btnCommentDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.deleteComment((int) commentTable.getValueAt(selectedComment, 0));
				refreshDetail(((int) table.getValueAt(selectedRow, 0)));
			}
		});

		btnCommentUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.updateComment(CommentDTO.builder().content(commentField.getText())
						.commentId((int) commentTable.getValueAt(selectedComment, 0)).build());
				refreshDetail(((int) table.getValueAt(selectedRow, 0)));
			}
		});

		btnComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.createComment(CommentDTO.builder().content(commentField.getText())
						.postId(((Integer) table.getValueAt(selectedRow, 0)).intValue())
						.userName(MainView.user.getName()).build());
				commentField.setText("");
				refreshDetail(((int) table.getValueAt(selectedRow, 0)));
			}
		});

		setDefaultCloseOperation(3);
		setVisible(true);
	}
}
