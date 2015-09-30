package models;

/**
 * Created by maksim on 30.09.2015.
 */
public class CarModel {
    private String make;
    private String model;
    private int year;
    private String vin;

    public CarModel(String make,String model,int year,String vin){
        this.make=make;
        this.model=model;
        this.year=year;
        this.vin=vin;
    }

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

    @Override
    public String toString() {
        return "{\"make\":\"" + this.make
                + "\",\"model\":\"" + this.model
                + "\",\"year\":\"" + this.year
                + "\",\"vin\":\"" + this.vin + "\"}";
    }
}
