/**
 * 
 */
package de.padur.boersendaten.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author ronald.padur
 *
 */
public class FileToDocumementConverter {

	/**
	 * Liefert für ein File das Document zurück
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Document getDocument(final File file) throws Exception{
		
		final BufferedReader reader = new BufferedReader(new FileReader(
				file));
		StringBuffer htmlBuffer = new StringBuffer();
		String line = new String();
		while ((line = reader.readLine()) != null) {
			htmlBuffer.append(line).append('\n');
		}
		reader.close();
		final Document doc = Jsoup.parse(htmlBuffer.toString());
		return doc;
	}
	
}
