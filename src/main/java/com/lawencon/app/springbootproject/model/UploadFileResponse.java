package com.lawencon.app.springbootproject.model;

public class UploadFileResponse {
    private String fileName;
//    private String fileDownloadUri;
    private String fileType;
//    private long size;
    private byte[] data;

    public UploadFileResponse(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
//        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
//        this.size = size;
        this.setData(data);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    public String getFileDownloadUri() {
//        return fileDownloadUri;
//    }
//
//    public void setFileDownloadUri(String fileDownloadUri) {
//        this.fileDownloadUri = fileDownloadUri;
//    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

//    public long getSize() {
//        return size;
//    }
//
//    public void setSize(long size) {
//        this.size = size;
//    }

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
