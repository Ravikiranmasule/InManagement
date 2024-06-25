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
	private byte[] data;

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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Photo(long photoId, @NotNull String photoName, byte[] data) {
		super();
		this.photoId = photoId;
		this.photoName = photoName;
		this.data = data;
	}

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
