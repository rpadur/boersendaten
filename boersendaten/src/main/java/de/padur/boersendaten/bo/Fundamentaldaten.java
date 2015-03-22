/**
 * 
 */
package de.padur.boersendaten.bo;


/**
 * @author ronald.padur
 *
 */
public class Fundamentaldaten {

	private double kgv;
	private double kbv;
	private double marketvalue;
	private long amountOfStocks;
	private double eigenkapitalquote;
	private double fremdkapitalquote;
	/**
	 * @return the kgv
	 */
	public double getKgv() {
		return kgv;
	}
	/**
	 * @param kgv the kgv to set
	 */
	public void setKgv(double kgv) {
		this.kgv = kgv;
	}
	/**
	 * @return the kbv
	 */
	public double getKbv() {
		return kbv;
	}
	/**
	 * @param kbv the kbv to set
	 */
	public void setKbv(double kbv) {
		this.kbv = kbv;
	}
	/**
	 * @return the marketvalue
	 */
	public double getMarketvalue() {
		return marketvalue;
	}
	/**
	 * @param marketvalue the marketvalue to set
	 */
	public void setMarketvalue(double marketvalue) {
		this.marketvalue = marketvalue;
	}
	/**
	 * @return the amountOfStocks
	 */
	public long getAmountOfStocks() {
		return amountOfStocks;
	}
	/**
	 * @param amountOfStocks the amountOfStocks to set
	 */
	public void setAmountOfStocks(long amountOfStocks) {
		this.amountOfStocks = amountOfStocks;
	}
	/**
	 * @return the eigenkapitalquote
	 */
	public double getEigenkapitalquote() {
		return eigenkapitalquote;
	}
	/**
	 * @param eigenkapitalquote the eigenkapitalquote to set
	 */
	public void setEigenkapitalquote(double eigenkapitalquote) {
		this.eigenkapitalquote = eigenkapitalquote;
	}
	/**
	 * @return the fremdkapitalquote
	 */
	public double getFremdkapitalquote() {
		return fremdkapitalquote;
	}
	/**
	 * @param fremdkapitalquote the fremdkapitalquote to set
	 */
	public void setFremdkapitalquote(double fremdkapitalquote) {
		this.fremdkapitalquote = fremdkapitalquote;
	}
	
	@Override
	public String toString() {
		return String.format("Eigenkapitalquote = %s, Fremdkapitalquote = %s\n", eigenkapitalquote,fremdkapitalquote);
		
	}
}
