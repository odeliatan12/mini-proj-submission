package nus.project.server.dto;

public class CapacityDto {

    private Integer capacity;
    private String starttiming;
    private String endtiming;
    private Integer restaurantId;
    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public String getStarttiming() {
        return starttiming;
    }
    public void setStarttiming(String starttiming) {
        this.starttiming = starttiming;
    }
    public String getEndtiming() {
        return endtiming;
    }
    public void setEndtiming(String endtiming) {
        this.endtiming = endtiming;
    }
    public Integer getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    
}
