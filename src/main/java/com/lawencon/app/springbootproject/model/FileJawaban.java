package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity @Table(name = "file_jawaban")
public class FileJawaban {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idFileJawaban;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
    
    private String uploadTime;

    public FileJawaban() {

    }

    public FileJawaban(String fileName, String fileType, byte[] data, String uploadTime) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.uploadTime = uploadTime;
    }

    public String getIdFileJawaban() {
		return idFileJawaban;
	}

	public void setIdFileJawaban(String idFileJawaban) {
		this.idFileJawaban = idFileJawaban;
	}

	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
}
