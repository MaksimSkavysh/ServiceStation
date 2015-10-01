package models;

import java.lang.IllegalArgumentException;
/**
 * Created by maksim on 30.09.2015.
 */
public class ClientCardModel {

    public final static String FIRSTNAME="firstName";
    public final static String LASTNAME="lastName";
    public final static String BIRTHDATE="birthDate";
    public final static String ADDRESS="address";
    public final static String EMAIL="email";
    public final static String PHONE="phone";
    public final static String ID="id";

    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String email;
    private String phone;
    private String id;

    public ClientCardModel(){
    }

    public ClientCardModel(String firstName, String lastName, String birthDate, String address, String email, String phone, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public ClientCardModel(String firstName, String lastName, String birthDate, String address, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstName(String firstName) {
        if(firstName!=null){
            this.firstName = firstName;
        }
        else{
            throw new IllegalArgumentException("illegal first name in user card");
        }
    }

    public void setLastName(String lastName) {
        if(lastName!=null){
            this.lastName = lastName;
        }
        else{
            throw new IllegalArgumentException("illegal last name in user card");
        }
    }

    public void setBirthDate(String birthDate) {
        if(birthDate!=null){
            this.birthDate = birthDate;
        }
        else{
            throw new IllegalArgumentException("illegal birth date in user card");
        }
    }

    public void setAddress(String address) {
        if(address!=null){
            this.address = address;
        }
        else{
            throw new IllegalArgumentException("illegal address in user card");
        }
    }

    public void setEmail(String email) {
        if(email!=null){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("illegal email in user card");
        }
    }

    public void setPhone(String phone) {
        if(phone!=null){
            this.phone = phone;
        }
        else{
            throw new IllegalArgumentException("illegal phone in user card");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        //TODO: normal id
        this.id = id;
    }

    @Override
    public String toString() {
        return "{\""+FIRSTNAME+"\":\"" + this.firstName
                + "\",\""+LASTNAME+"\":\"" + this.lastName
                + "\",\""+BIRTHDATE+"\":\"" + this.birthDate
                + "\",\""+ADDRESS+"\":\"" + this.address
                + "\",\""+EMAIL+"\":\"" + this.email
                + "\",\""+PHONE+"\":\"" + this.phone
                + "\",\""+ID+"\":\"" + this.id + "\"}";
    }
}
