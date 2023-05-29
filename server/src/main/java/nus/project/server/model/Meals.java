package nus.project.server.model;

public class Meals {

    private Integer restaurantId;
    private Integer nameId;
    private Integer categoryId;
    private Double amount;
    public Integer getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    public Integer getNameId() {
        return nameId;
    }
    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
