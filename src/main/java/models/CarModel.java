package models;

/**
 * Created by maksim on 30.09.2015.
 */
public class CarModel {
    public final static String MAKE="make";
    public final static String MODEL="model";
    public final static String YEAR="year";
    public final static String VIN="vin";
    public final static String USERID="userId";

    private String make;
    private String model;
    private int year;
    private String vin;
    private int userID;

    public CarModel(){}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        if(make!=null){
            this.make = make;
        }
        else{
            throw new IllegalArgumentException("illegal car make");
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model!=null){
            this.model = model;
        }
        else{
            throw new IllegalArgumentException("illegal car model");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year>1800&&year<2100){
            this.year = year;
        }
        else{
            throw new IllegalArgumentException("illegal car year");
        }
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        if(vin!=null){
            this.vin = vin;
        }
        else{
            throw new IllegalArgumentException("illegal car vin");
        }
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "{\""+MAKE+"\":\"" + this.make
                + "\",\""+MODEL+"\":\"" + this.model
                + "\",\""+YEAR+"\":\"" + this.year
                + "\",\""+USERID+"\":\"" + this.userID
                + "\",\""+VIN+"\":\"" + this.vin + "\"}";
    }
}
