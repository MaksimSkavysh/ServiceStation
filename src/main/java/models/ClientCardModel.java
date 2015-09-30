package models;

import java.lang.IllegalArgumentException;
/**
 * Created by maksim on 30.09.2015.
 */
public class ClientCardModel {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String email;
    private String phone;

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

    @Override
    public String toString() {
        return "{\"firstName\":\"" + this.firstName
                + "\",\"lastName\":\"" + this.lastName
                + "\",\"birthDate\":\"" + this.birthDate
                + "\",\"address\":\"" + this.address
                + "\",\"email\":\"" + this.email
                + "\",\"phone\":\"" + this.phone + "\"}";
    }
}
