package com.github.filipvencovsky.dbapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Jednoduchá aplikace pro demonstraci objektově-relačního mapování
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//vytvoření objektu pro uložení do DB
    	Zamestnanec zam = new Zamestnanec();
    	zam.setId(0);
    	zam.setJmeno("Jan");
    	zam.setPrijmeni("Novák");
    	zam.setPlat(50000);
    	
    	//vytvoření prázdné konfigurace
    	Configuration conf = new Configuration();
    	
    	//načtení konfiguračního souboru hibernate.cfg.xml, při defaultním jménu se nemusí nic uvádět
    	conf.configure();
    	
    	//načtení anotací z třídy
    	conf.addAnnotatedClass(Zamestnanec.class);
 
    	//získání odkazu na budoucí databázovou relaci, předá nám ji konfigurační objekt, který jsme předtím vytvořili
    	SessionFactory sf = conf.buildSessionFactory();
    	
    	//otevření databázové relace
    	Session ses = sf.openSession();
    	
    	//otevření transakce
    	Transaction tx = ses.beginTransaction();
    	
    	//uložení stavu objektu
    	ses.save(zam);
    	
    	//odeslání transakce
    	tx.commit();
    }
}
