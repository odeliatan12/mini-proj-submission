package nus.project.server.dto;

public class ReservationDto {

    private Integer pax;
    private Integer timeReserve;
    private String dateReserve;
    public Integer getPax() {
        return pax;
    }
    public void setPax(Integer pax) {
        this.pax = pax;
    }
    public Integer getTimeReserve() {
        return timeReserve;
    }
    public void setTimeReserve(Integer timeReserve) {
        this.timeReserve = timeReserve;
    }
    public String getDateReserve() {
        return dateReserve;
    }
    public void setDateReserve(String dateReserve) {
        this.dateReserve = dateReserve;
    }
}
