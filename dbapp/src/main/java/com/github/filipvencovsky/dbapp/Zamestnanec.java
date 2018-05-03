package com.github.filipvencovsky.dbapp;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * jednoduchá třída popisující zákaldní atributy zaměstnance
 * 
 * je nutné použít API pro persistenci a identifikovat třídu jako entitu, která je možným předmětem persistence
 * dále je nutné, aby objekt měl jasný identifikátor, ten musíme označit anotací
 *
 */
@Entity
public class Zamestnanec {
	private int id;
	private String jmeno;
	private String prijmeni;
	private int plat;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJmeno() {
		return jmeno;
	}
	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}
	public String getPrijmeni() {
		return prijmeni;
	}
	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}
	public int getPlat() {
		return plat;
	}
	public void setPlat(int plat) {
		this.plat = plat;
	}



}
