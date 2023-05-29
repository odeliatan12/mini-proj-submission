package nus.project.server.dto;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import jakarta.json.Json;

public class ImageDto {

    private Integer id;
    private Blob image;
    private String image_fileType;
    private Integer restaurantId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Blob getImage() {
        return image;
    }
    public void setImage(Blob image) {
        this.image = image;
    }
    public String getImage_fileType() {
        return image_fileType;
    }
    public void setImage_fileType(String image_fileType) {
        this.image_fileType = image_fileType;
    }
    public Integer getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public String toJsonString() throws SQLException {
        byte[] pictureBytes = this.getImage().getBytes(1, (int) this.getImage().length());
        String pictureBase64 = Base64.getEncoder().encodeToString(pictureBytes);
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("picture", pictureBase64)
                .add("image_fileType", this.getImage_fileType())
                .build()
                .toString();
    }

    
    
}
