/**
 * 
 */
package de.padur.boersendaten.bo;

import org.springframework.data.annotation.Id;


/**
 * @author ronald.padur
 *
 */
public class Aktie {
	@Id
	private String id;
	private String name;
	
	private String wkn;
	private String isin;
	
	public Aktie(){
		
	}

	public Aktie(String name, String wkn, String isin){
		this.name = name;
		this.wkn = wkn;
		this.isin = isin;
		
	}
	
	
	
	@Override
	public String toString() {
		return String.format("Aktie[Id=%s, Name=%s, WKN=%s, ISIN=%s",id, name, wkn,isin);
	}
	
}
