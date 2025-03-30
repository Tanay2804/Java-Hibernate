package com.tanay;
import java.io.FileInputStream;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) throws Exception {
        Product product = new Product();
        // Note ID is auto-incremented, so we don't need to set it
        product.setName("Laptop");
        product.setPrice(999.99);
        
        // Once we have the details and the object is ready, we can use hibernate to save it to the database
        //  we need to tell hibernate about the database connection and credentials
        //  we need to create a configuration object and set the properties
        // Go to resources and create a config file and specify the database connection details
        // Refer: https://docs.jboss.org/hibernate/orm/6.6/quickstart/html_single/#obtaining
        Configuration configuration = new Configuration();

        // Load the properties file
        Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/hibernate.properties"));

        // NOTE: Tell hibernate about the Product class to use as a schema and generate queries
        configuration.addAnnotatedClass(com.tanay.Product.class);
        configuration.addProperties(properties);
        
        // Create a session factory and open a session
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();

        // Start a transaction
        session.beginTransaction(); // Returns a transaction object from org.hibernate.config.Transaction

        // Save the product object to the database
        session.persist(product);
        
        
        // Commit the transaction
        session.getTransaction().commit();

        // Close the session & factory
        session.close();
        factory.close();
    }
}