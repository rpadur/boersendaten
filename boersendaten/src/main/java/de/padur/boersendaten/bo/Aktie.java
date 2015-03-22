/**
 * 
 */
package de.padur.boersendaten.bo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	
	
	
	private Map<String, Fundamentaldaten> jahreswerte = new HashMap<String, Fundamentaldaten>();
	
	public Aktie(){
		
	}

	
	
	public Aktie(String name, String wkn, String isin){
		this.name = name;
		this.wkn = wkn;
		this.isin = isin;
		
	}
	
	
	
	@Override
	public String toString() {
		final StringBuffer ergebnis =  new StringBuffer(String.format("Aktie[Id=%s, Name=%s, WKN=%s, ISIN=%s \n",id, name, wkn,isin));
		Set<Entry<String, Fundamentaldaten>> daten = getJahreswerte().entrySet();
		for (Entry<String, Fundamentaldaten> entry : daten) {
			ergebnis.append(entry.getKey()).append('\n').append(entry.getValue().toString());
		}
		return ergebnis.toString();
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



	/**
	 * @return the jahreswerte
	 */
	public Map<String, Fundamentaldaten> getJahreswerte() {
		return jahreswerte;
	}
	
}
