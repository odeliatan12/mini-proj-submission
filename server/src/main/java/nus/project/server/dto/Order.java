package nus.project.server.dto;

public class Order {
    
    private String dealId;
    private Double price;
    private String currency;
	private String method;
	private String intent;
	private String description;
    
    public String getDealId() {
        return dealId;
    }
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getIntent() {
        return intent;
    }
    public void setIntent(String intent) {
        this.intent = intent;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
