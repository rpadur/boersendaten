/**
 * 
 */
package de.padur.boersendaten.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ronald.padur
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Page {

	private String aktienname;
	private String wkn;
	private String sektor;
	private String isin;
	/**
	 * @return the aktienname
	 */
	public String getAktienname() {
		return aktienname;
	}
	/**
	 * @param aktienname the aktienname to set
	 */
	public void setAktienname(String aktienname) {
		this.aktienname = aktienname;
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
