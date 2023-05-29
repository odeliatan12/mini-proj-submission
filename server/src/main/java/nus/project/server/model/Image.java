package nus.project.server.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;

public class Image {

    private Integer id;
    private Blob picture;
    private String fileType;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Blob getPicture() {
        return picture;
    }
    public void setPicture(Blob picture) {
        this.picture = picture;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public static Image create(MultipartFile image, Integer restaurantId) throws SerialException, SQLException, IOException{
        Image i = new Image();
        i.setId(restaurantId);
        Blob b = new SerialBlob(image.getBytes());
        i.setPicture(b);
        i.setFileType(image.getContentType());
        return i;
    }
    
    
}
