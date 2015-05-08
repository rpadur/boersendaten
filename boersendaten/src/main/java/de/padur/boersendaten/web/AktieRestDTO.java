/**
 * 
 */
package de.padur.boersendaten.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ronald.padur
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AktieRestDTO {

	private String id;
	private String name;
	private String wkn;
	private String sektor;
	private String isin;
	private String anzahlAktien;

	/**
	 * @return the wkn
	 */
	public String getWkn() {
		return wkn;
	}

	/**
	 * @param wkn
	 *            the wkn to set
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
	 * @param sektor
	 *            the sektor to set
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
	 * @param isin
	 *            the isin to set
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the anzahlAktien
	 */
	public String getAnzahlAktien() {
		return anzahlAktien;
	}

	/**
	 * @param anzahlAktien
	 *            the anzahlAktien to set
	 */
	public void setAnzahlAktien(String anzahlAktien) {
		this.anzahlAktien = anzahlAktien;
	}

	@Override
	public String toString() {
		final StringBuffer ergebnis = new StringBuffer(String.format(
				"Aktie[Id=%s, Name=%s, WKN=%s, ISIN=%s, Sektor=%s \n", id,
				name, wkn, isin, sektor));
		ergebnis.append("Anzahl Aktien=").append(anzahlAktien).append('\n');
		return ergebnis.toString();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
