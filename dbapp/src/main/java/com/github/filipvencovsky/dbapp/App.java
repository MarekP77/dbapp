package com.github.filipvencovsky.dbapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Zamestnanec zam = new Zamestnanec();
    	zam.setId(0);
    	zam.setJmeno("Jan");
    	zam.setPrijmeni("Novák");
    	zam.setPlat(50000);
    	
    	Configuration conf = new Configuration().addAnnotatedClass(Zamestnanec.class);
    	
    	SessionFactory sf = conf.buildSessionFactory();
    	
    	Session ses = sf.openSession();
    	ses.save(zam);
    }
}
