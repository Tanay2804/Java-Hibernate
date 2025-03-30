package com.tanay;
import java.io.FileInputStream;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateProduct {
    public static void main(String[] args) throws Exception {
        // Create a configuration object and set the properties
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/hibernate.properties"));
        configuration.addAnnotatedClass(Product.class);
        configuration.addProperties(properties);

        // Create a session factory and open a session
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();

        // Start a transaction
        session.beginTransaction();

        // Update the product object in the database
        // Assuming we have a product with ID 1 that we want to update
        int productId = 1;
        Product product = session.find(Product.class, productId);
        if (product != null) {
            product.setName("Updated Laptop");
            product.setPrice(899.99);
            session.merge(product);
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
