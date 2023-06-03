package model.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDTO {
	private int commentId;
	private String content;
	private Date createdAt;
	private int likeCnt;
	private String userName;
	private int postId;
}
