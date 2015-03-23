/**
 * 
 */
package de.padur.boersendaten.datenquelle.godmodetrader;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import de.padur.boersendaten.dto.AktienDatenDTO;
import de.padur.boersendaten.dto.JahresWertTupel;

/**
 * @author ronald.padur
 *
 */
@Service
public class FundamentalDatenErmittler {
	
	
	
	public void evaluateStockPageFundamentalData(final Document doc,
			AktienDatenDTO dto) {
		String title = doc.title();
		System.out.println("title : " + title);

		Elements fundametaldaten = doc.select("table:contains(2013) > tbody");
		for (Element daten : fundametaldaten) {

			// zuerst kommt der Tabellenkopf
			Elements tabellendaten = daten.select("tr");
			for (Element element : tabellendaten) {
				Elements spaltendaten = element.select("td");
				if (spaltendaten.size() != 3) {
					continue;
				}

				final JahresWertTupel tupel2013 = new JahresWertTupel("2013",
						spaltendaten.get(1).text());
				final JahresWertTupel tupel2014 = new JahresWertTupel("2014",
						spaltendaten.get(2).text());
				final List<JahresWertTupel> tupel = new ArrayList<JahresWertTupel>();
				tupel.add(tupel2013);
				tupel.add(tupel2014);
				dto.getDaten().put(spaltendaten.get(0).text(), tupel);
			}

		}
	}
	
}
