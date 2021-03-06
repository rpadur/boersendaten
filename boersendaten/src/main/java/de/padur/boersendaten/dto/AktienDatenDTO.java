/**
 * 
 */
package de.padur.boersendaten.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ronald
 *
 */
public class AktienDatenDTO {

	private String name;
	private String wkn;
	private String isin;
	private String id;
	private String anzahlAktien;
	private String sektor;
	
	private final Map<String, List<JahresWertTupel>> daten = new HashMap<String, List<JahresWertTupel>>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, List<JahresWertTupel>> getDaten() {
		return daten;
	}
	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Name:\t").append(getName()).append('\n');
		buffer.append("WKN:\t").append(getWkn()).append('\n');
		buffer.append("ISIN:\t").append(getIsin()).append('\n');
		for (Map.Entry<String, List<JahresWertTupel>> element : getDaten().entrySet()) {
			buffer.append(element.getKey()).append('\t').append(element.getValue()).append('\n');
		}
		return buffer.toString();
		
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the anzahlAktien
	 */
	public String getAnzahlAktien() {
		return anzahlAktien;
	}
	/**
	 * @param anzahlAktien the anzahlAktien to set
	 */
	public void setAnzahlAktien(String anzahlAktien) {
		this.anzahlAktien = anzahlAktien;
	}
	/**
	 * @return the sektor
	 */
	public String getSektor() {
		return sektor;
	}
	/**
	 * @param sektor the sektor to set
	 */
	public void setSektor(String sektor) {
		this.sektor = sektor;
	}
}
