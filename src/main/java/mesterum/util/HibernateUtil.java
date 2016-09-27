/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.util;

 
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
/*import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
 
@Component
@Configuration
public class HibernateUtil {
    private SessionFactory sessionFactory;
//    @Autowired 
//    private static HibernateUtil hu;
    @Autowired
    public HibernateUtil(EntityManagerFactory factory) {
		this.sessionFactory = factory.unwrap(SessionFactory.class);
	    if(this.sessionFactory == null){
	        throw new NullPointerException("factory is not a hibernate factory");
	      }
	}
    public HibernateUtil(){}

	@Bean
	public SessionFactory getSessionFactory() {
        /*if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }*/
         
        return sessionFactory;
    }
}
