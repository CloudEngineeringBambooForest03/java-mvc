package model.dto;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
	private String name;
	private String id;
	private String password;
	private String nickName;
	private ArrayList<PostDTO> postList;
	private ArrayList<CommentDTO> commentList;
}
