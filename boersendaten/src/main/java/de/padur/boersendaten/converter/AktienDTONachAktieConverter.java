/**
 * 
 */
package de.padur.boersendaten.converter;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.padur.boersendaten.bo.Aktie;
import de.padur.boersendaten.bo.Fundamentaldaten;
import de.padur.boersendaten.dto.AktienDatenDTO;
import de.padur.boersendaten.dto.JahresWertTupel;

/**
 * @author ronald.padur
 *
 */
@Component
public class AktienDTONachAktieConverter {

	public Aktie convertAktienDTO(final AktienDatenDTO aktienDatenDTO) {
		final Aktie aktie = new Aktie(aktienDatenDTO.getName(),
				aktienDatenDTO.getWkn(), aktienDatenDTO.getIsin());
		String anzahlAktien = aktienDatenDTO.getAnzahlAktien().replace(".", "").replace(",", ".");
		System.out.println(Double.valueOf(anzahlAktien).longValue());
			aktie.setAnzahlAktien(Double.valueOf(
					anzahlAktien).longValue());
		aktie.setSektor(aktienDatenDTO.getSektor());
		convertJahresdaten(aktie, aktienDatenDTO.getDaten());
		return aktie;

	}

	private void convertJahresdaten(Aktie aktie,
			Map<String, List<JahresWertTupel>> daten) {

		for (String keyword : daten.keySet()) {

			final List<JahresWertTupel> tupel = daten.get(keyword);
			for (JahresWertTupel jahresWertTupel : tupel) {
				Fundamentaldaten fundamentaldaten = aktie.getJahreswerte().get(
						jahresWertTupel.getJahr());
				if (fundamentaldaten == null) {
					fundamentaldaten = new Fundamentaldaten();
					aktie.getJahreswerte().put(jahresWertTupel.getJahr(),
							fundamentaldaten);
				}
				if (keyword.equalsIgnoreCase("Fremdkapitalquote")
						&& !jahresWertTupel.getWert().equals("-")) {

					fundamentaldaten
							.setFremdkapitalquote(convertProzentToDouble(jahresWertTupel
									.getWert()));
				}
				if (keyword.equalsIgnoreCase("Eigenkapitalquote")
						&& !jahresWertTupel.getWert().equals("-")) {

					fundamentaldaten
							.setEigenkapitalquote(convertProzentToDouble(jahresWertTupel
									.getWert()));
				}
				System.out.println(keyword + '\t' + jahresWertTupel.getJahr()
						+ '\t' + jahresWertTupel.getWert());
			}

		}

	}

	private double convertProzentToDouble(final String prozentwert) {
		final String quote = prozentwert.replace("%", "").replace(',', '.')
				.trim();

		final Double quoteDouble = Double.valueOf(quote) / 100.;
		return quoteDouble.doubleValue();
	}

}
