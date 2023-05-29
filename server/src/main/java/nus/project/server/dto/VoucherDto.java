package nus.project.server.dto;

public class VoucherDto {

    private String dealId;
    private Double newAmount;
    
    public String getDealId() {
        return dealId;
    }
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }
    public Double getNewAmount() {
        return newAmount;
    }
    public void setNewAmount(Double newAmount) {
        this.newAmount = newAmount;
    }
    
    
}
