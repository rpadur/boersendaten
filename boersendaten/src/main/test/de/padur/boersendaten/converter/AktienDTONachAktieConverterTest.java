/**
 * 
 */
package de.padur.boersendaten.converter;



import de.padur.boersendaten.TestHelper;
import de.padur.boersendaten.bo.Aktie;
import de.padur.boersendaten.dto.AktienDatenDTO;



/**
 * @author ronald.padur
 *
 */
public class AktienDTONachAktieConverterTest {

	public static void main(String[] args)  {
		final AktienDTONachAktieConverterTest test= new AktienDTONachAktieConverterTest();
		try {
			test.testConvertFundamentaldaten();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testConvertFundamentaldaten() throws Exception {
		TestHelper helper = new TestHelper();
		AktienDatenDTO dto = helper.getBeispielFundamentalDatenAktienDatenDTO();
		final AktienDTONachAktieConverter converter = new AktienDTONachAktieConverter();
		Aktie convertAktienDTO = converter.convertAktienDTO(dto);
		System.out.println(convertAktienDTO);
	}
	
}
