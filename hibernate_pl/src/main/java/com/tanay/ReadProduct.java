package com.tanay;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadProduct {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/hibernate.properties"));
        configuration.addAnnotatedClass(Product.class);
        configuration.addProperties(properties);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();

        // Start a transaction
        session.beginTransaction();

        // Read the product object from the database
        // Assuming we have a product with ID 1 that we want to read
        int productId = 1;
        Product product = session.find(Product.class, productId);
        if (product != null) {
            System.out.println(product.toString());
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session & factory
        session.close();
        factory.close();
    }
}
