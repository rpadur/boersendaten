/**
 * 
 */
package de.padur.boersendaten.datenquelle.godmodetrader;

import org.jsoup.nodes.Document;

import de.padur.boersendaten.TestHelper;
import de.padur.boersendaten.dto.AktienDatenDTO;

/**
 * @author ronald.padur
 *
 */
public class FundamentalDatenErmittlerTest {

	public static void main(String[] args) {
		final FundamentalDatenErmittlerTest test = new FundamentalDatenErmittlerTest();
		try {
			test.testErmittleFundamentaldaten();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void testErmittleFundamentaldaten() throws Exception {
		final FundamentalDatenErmittler ermittler = new FundamentalDatenErmittler();
		final AktienDatenDTO dto = new AktienDatenDTO();
		final Document doc = new TestHelper().getBeispielDocument();
		ermittler.evaluateStockPageFundamentalData(doc, dto);
		System.out.println(dto);
	}

}
