package com.example.medoo;

public class member{
    private String hospitalname;
    private String street ;
    private String city;
    private String village;
    private String specification;
    private  String buildingnumber;
    private  Integer pincode;
    private  String timing;
    private  Long number;
    private String state;

    public member() {
    }

    public member(String buildingnumber) {
        this.buildingnumber = buildingnumber;
        this.timing=timing;
        this.state=state;
        this.hospitalname=hospitalname;
        this.street=street;
        this.village=village;
        this.city=city;
        this.specification=specification;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getCity() {
        return city;
    }

    public String getBuildingnumber() {
        return buildingnumber;
    }

    public void setBuildingnumber(String buildingnumber) {
        this.buildingnumber = buildingnumber;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String toString(){
        return this.hospitalname;
    }

}