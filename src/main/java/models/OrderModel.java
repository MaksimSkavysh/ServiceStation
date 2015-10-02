package models;

/**
 * Created by maksim on 30.09.2015.
 */
public class OrderModel {
    public final static String COMPLETED="completed";
    public final static String PROGRESS="in progress";
    public final static String CANCELLED="cancelled";

    public final static String STATUS="status";
    public final static String DATE="date";
    public final static String AMOUNT="amount";
    public final static String VIN="vin";

    private String status;
    private String date;
    private int amount;
    private String vin;
    private int orderId;

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public void setDate(String date) {
        if(date!=null) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("illegal order date");
        }
    }

    public void setAmount(int amount) {
        if(amount>0&&amount<10000){
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("illegal order amount");
        }
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStatus(String status) {
        switch(status){
            case CANCELLED:
                this.status=CANCELLED;
                break;
            case COMPLETED:
                this.status=COMPLETED;
                break;
            case PROGRESS:
                this.status=PROGRESS;
                break;
            default:
                throw new IllegalArgumentException("illegal order status");
        }
    }

    public void cancele(){
        this.status=CANCELLED;
    }

    public void complite(){
        this.status=COMPLETED;
    }

    public void inProgress(){
        this.status=PROGRESS;
    }

    @Override
    public String toString() {
        return "{\"status\":\"" + this.status
                + "\",\"date\":\"" + this.date
                + "\",\"orderId\":\"" + this.orderId
                + "\",\"amount\":\"" + this.amount
                + "\",\"vin\":\"" + this.vin + "\"}";
    }

}
