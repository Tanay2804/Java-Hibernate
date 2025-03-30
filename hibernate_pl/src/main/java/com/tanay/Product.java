package com.tanay;
import jakarta.persistence.*;
// Refer: https://docs.jboss.org/hibernate/orm/6.6/quickstart/html_single/#obtaining

// We Use Jakarta Persistence API (JPA) for annotating which ensures hibernate to create tables etc 
// and compile-time checking of the queries
@Entity
@Table(name = "products") // Tell Hibernate to create a table automatically 
// refer hibernate.properties file for this detail 

public class Product {
    // Explore Lombok which is an annotation-based Java library that allows you to
    // reduce boilerplate code
    // we will not be using it in this project but it is a good library to know
    // details of the product
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String name;
    private double price;

    // generate getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    // to string method to later print the product details
    // this method is used to print the product details
    @Override
    public String toString() {
        return "{\n  id: " + id + ",\n  name: \"" + name + "\",\n  price: " + price + "\n}";
        // return "Product{" +
        // "id=" + id +
        // ", name='" + name + '\'' +
        // ", price=" + price + '\'' +
        // '}';
    }

}
