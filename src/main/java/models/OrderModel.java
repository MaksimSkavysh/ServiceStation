package models;

/**
 * Created by maksim on 30.09.2015.
 */
public class OrderModel {
    public final static String COMPLETED="completed";
    public final static String PROGRESS="in progress";
    public final static String CANCELLED="cancelled";

    private String status;
    private String date;
    private int amount;

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
                + "\",\"phone\":\"" + this.amount + "\"}";
    }

}
