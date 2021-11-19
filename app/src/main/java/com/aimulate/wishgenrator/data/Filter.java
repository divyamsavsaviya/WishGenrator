package com.aimulate.wishgenrator.data;

public class Filter {
    private String head;
    private String description;

    public Filter() {
    }

    public Filter(String head, String description, String imageURL) {
        this.head = head;
        this.description = description;
    }

    public String getHead() {
        return head;
    }

    public String getDescription() {
        return description;
    }

}
