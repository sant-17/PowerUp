package com.pragma.powerup.domain.model;

public class RestaurantModel {
    private Long id;
    private String name;
    private String address;
    private Long owner;
    private String phoneNumber;
    private Long nit;
    private String logoUrl;

    public RestaurantModel() {
    }

    public RestaurantModel(Long id, String name, String address, Long owner, String phoneNumber, Long nit, String logoUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.phoneNumber = phoneNumber;
        this.nit = nit;
        this.logoUrl = logoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
