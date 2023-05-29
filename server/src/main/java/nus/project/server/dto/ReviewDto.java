package nus.project.server.dto;

public class ReviewDto {

    private Integer ratings;
    private String description;
    public Integer getRatings() {
        return ratings;
    }
    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
