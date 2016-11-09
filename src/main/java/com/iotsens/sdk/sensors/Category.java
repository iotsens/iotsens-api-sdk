package com.iotsens.sdk.sensors;

public class Category {

    private Integer id;
    private Integer listingOrder;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListingOrder() {
        return listingOrder;
    }

    public void setListingOrder(Integer listingOrder) {
        this.listingOrder = listingOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", listingOrder=" + listingOrder +
                ", name='" + name + '\'' +
                '}';
    }
}
