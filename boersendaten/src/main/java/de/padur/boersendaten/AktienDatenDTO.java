/**
 * 
 */
package de.padur.boersendaten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ronald
 *
 */
public class AktienDatenDTO {

	private String name;
	
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
		
		for (Map.Entry<String, List<JahresWertTupel>> element : getDaten().entrySet()) {
			buffer.append(element.getKey()).append('\t').append(element.getValue()).append('\n');
		}
		return buffer.toString();
		
	}
}
