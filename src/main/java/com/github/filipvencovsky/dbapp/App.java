package com.github.filipvencovsky.dbapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Jednoduchá aplikace pro demonstraci objektově-relačního mapování
 *
 */
public class App {

	public static void main(String[] args) throws Exception {
		
		vytvorZamestnance();
		
		nactiZaměstnance(0);

	}

	private static void vytvorZamestnance() throws Exception {
		// vytvoření objektu pro uložení do DB
		Zamestnanec zam = new Zamestnanec();
		zam.setId(0);
		zam.setJmeno("Jan");
		zam.setPrijmeni("Novák");
		zam.setPlat(50000);

		// vytvoření prázdné konfigurace
		Configuration conf = new Configuration();

		// načtení konfiguračního souboru hibernate.cfg.xml, při defaultním jménu se
		// nemusí nic uvádět
		conf.configure();
		
		//chceme vytvořit tabulku a smazat pokud existovala
		conf.setProperty("hibernate.hbm2ddl.auto", "create");

		// načtení anotací z třídy
		conf.addAnnotatedClass(Zamestnanec.class);

		// získání odkazu na budoucí databázovou relaci, předá nám ji konfigurační
		// objekt, který jsme předtím vytvořili
		SessionFactory factory = conf.buildSessionFactory();

		// otevření databázové relace
		Session ses = factory.openSession();
		Transaction tx = null;
		try {

			// otevření transakce
			tx = ses.beginTransaction();

			// uložení stavu objektu
			ses.save(zam);

			// odeslání transakce
			tx.commit();
		} catch (Exception e) {
			// rollback při problému
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			// ukončení databázové relace po všech požadovaných operacích s DB, úklid pod
			// připojení
			ses.close();
		}

		// ukončení běžící instance, která vrací databázové relace - jinak program
		// neskončí
		factory.close();
	}
	
	private static void nactiZaměstnance(int id) throws Exception {
		// vytvoření prázdnéo objektu pro načtení zaměstnance z DB
		Zamestnanec zam = new Zamestnanec();

		// vytvoření prázdné konfigurace
		Configuration conf = new Configuration();

		// načtení konfiguračního souboru hibernate.cfg.xml, při defaultním jménu se
		// nemusí nic uvádět
		conf.configure();
		
		//neupravuje se schéma DB
		conf.setProperty("hibernate.hbm2ddl.auto", "validate");

		// načtení anotací z třídy
		conf.addAnnotatedClass(Zamestnanec.class);

		// získání odkazu na budoucí databázovou relaci, předá nám ji konfigurační
		// objekt, který jsme předtím vytvořili
		SessionFactory factory = conf.buildSessionFactory();

		// otevření databázové relace
		Session ses = factory.openSession();
		Transaction tx = null;
		try {

			// otevření transakce
			tx = ses.beginTransaction();

			// uložení stavu objektu
			ses.load(zam, id);
			
			System.out.println(zam.getPrijmeni()+", "+zam.getJmeno()+", "+zam.getPlat());

			// odeslání transakce
			tx.commit();
			
		} catch (Exception e) {
			// rollback při problému
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			// ukončení databázové relace po všech požadovaných operacích s DB, úklid pod
			// připojení
			ses.close();
		}

		// ukončení běžící instance, která vrací databázové relace - jinak program
		// neskončí
		factory.close();
	}
}
