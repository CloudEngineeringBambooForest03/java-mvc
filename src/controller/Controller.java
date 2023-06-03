package controller;

import java.util.ArrayList;

import model.dto.CommentDTO;
import model.dto.PostDTO;
import model.dto.UserDTO;
import model.service.Service;

public class Controller {
	private Service s = new Service();

	public boolean signUp(UserDTO user) {
		if (s.check(user.getName()) || s.dupCheck(user.getId())) {
			System.out.println(user.toString());
			return s.signUp(user);
		} else {
			return false;
		}
	}

	public UserDTO login(String id, String pwd) {
		try {
			return s.login(id, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(UserDTO user) {
		try {
			return s.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String name) {
		try {
			return s.delete(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public UserDTO getUserInfo(String name) {
		try {
			return s.getUserInfo(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean createPost(PostDTO post) {
		try {
			return s.createPost(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<PostDTO> readAllPosts() {
		try {
			return s.readAllPosts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PostDTO readPost(int postId) {
		try {
			return s.readPost(postId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updatePost(PostDTO post) {
		try {
			return s.updatePost(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletePost(int postId) {
		try {
			return s.deletePost(postId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean createComment(CommentDTO comment) {
		try {
			return s.createComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<CommentDTO> readComment(int postId) {
		try {
			return s.readComment(postId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateComment(CommentDTO comment) {
		try {
			return s.updateComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteComment(int commentId) {
		try {
			return s.deleteComment(commentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void doLike(String userName, int commentId) {
		try {
			s.doLike(userName, commentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}