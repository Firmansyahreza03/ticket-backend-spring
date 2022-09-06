package com.lawencon.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_thread_dtl")
public class ThreadDetail extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "hdr_id")
	private ThreadHeader header;

	@Column(name = "comment_text", columnDefinition = "TEXT")
	private String commentText;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	public ThreadHeader getHeader() {
		return header;
	}

	public void setHeader(ThreadHeader header) {
		this.header = header;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
