package nus.project.server.model;

public class Vouchers {

    private String id;
    private String dealId;
    private Double amount;
    private Integer restaurantId;
    private Integer userId;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDealId() {
        return dealId;
    }
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Integer getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
}
