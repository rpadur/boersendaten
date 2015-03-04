/**
 * 
 */
package de.padur.boersendaten;

import org.jsoup.nodes.Element;

/**
 * @author Ronald
 *
 */
public class JahresWertTupel {

	private String jahr;
	private String wert;

	public JahresWertTupel(String jahr, String wert) {
		this.jahr=jahr;
		this.wert=wert;
	}

	public String getJahr() {
		return jahr;
	}

	public void setJahr(String jahr) {
		this.jahr = jahr;
	}

	public String getWert() {
		return wert;
	}

	public void setWert(String wert) {
		this.wert = wert;
	}

	@Override
	public String toString() {
		return getJahr() + '\t' +getWert();
	}
	
}
