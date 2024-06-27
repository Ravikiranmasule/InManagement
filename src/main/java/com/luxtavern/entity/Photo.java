package com.luxtavern.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long photoId;
	
	@NotNull
	private String photoName;
	
	@Lob
	private byte[] image;
	
	
	@NotNull
    private String contentType;


	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Photo(long photoId, @NotNull String photoName, byte[] image, @NotNull String contentType) {
		super();
		this.photoId = photoId;
		this.photoName = photoName;
		this.image = image;
		this.contentType = contentType;
	}


	public long getPhotoId() {
		return photoId;
	}


	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}


	public String getPhotoName() {
		return photoName;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}



}
