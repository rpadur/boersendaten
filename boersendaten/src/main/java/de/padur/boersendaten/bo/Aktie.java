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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the wkn
	 */
	public String getWkn() {
		return wkn;
	}

	/**
	 * @param wkn the wkn to set
	 */
	public void setWkn(String wkn) {
		this.wkn = wkn;
	}

	/**
	 * @return the isin
	 */
	public String getIsin() {
		return isin;
	}

	/**
	 * @param isin the isin to set
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}
	
}
