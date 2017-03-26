package SSSM;

/** Represents a security.
 * 
 * @author Calum
 *
 */
public class Stock {

	private String symbol;
	private StockType type;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	private TradeRepository tradeRepository;
	
	/** Create a security for the specified symbol.
	 * 
	 * @param symbol A string containing the securities symbol. 
	 * @param type A StockType representing the security type.
	 * @param lastDividend The last dividend on the security.
	 * @param fixedDividend Value of fixed dividend.
	 * @param parValue Par value of the security.
	 */
	public Stock(String symbol, StockType type, int lastDividend, int fixedDividend, int parValue){
		this.symbol = symbol;
		this.type =  type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.tradeRepository = new TradeRepository();
	}
	
	/** Get's the security's symbol.
	 * 
	 * @return symbol A string containing the security's symbol. 
	 */
	public String getSymbol() {
		return symbol;
	}

	/** Set's the security's symbol.
	 * 
	 * @param symbol A string containing the security's symbol. 
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/** Get's the security's type.
	 * 
	 * @return type StockType representing the security's type.
	 */
	public StockType getType() {
		return type;
	}

	/** Get's the security's type.
	 * 
	 * @param type StockType representing the security's type.
	 */
	public void setType(StockType type) {
		this.type = type;
	}

	/** Get's the security's last dividend.
	 * 
	 * @return lastDividend double representing the security's last dividend.
	 */
	public double getLastDividend() {
		return lastDividend;
	}

	/** Set's the security's last dividend.
	 * 
	 * @param lastDividend double representing the security's last dividend.
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/** Get's the security's fixed dividend
	 * 
	 * @return fixedDividend double representing the security's fixed dividend.
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}

	/** Set's the security's fixed dividend
	 * 
	 * @param fixedDividend double representing the security's fixed dividend.
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/** Get's the security's par value.
	 * 
	 * @return parValue double representing the security's par value.
	 */
	public double getParValue() {
		return parValue;
	}

	/** Set's the security's par value.
	 * 
	 * @param parValue double representing the security's par value.
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	
	/** Adds a trade to the security's trade repository.
	 * 
	 * @param Trade .
	 */
	public void addTrade(Trade trade) {
		tradeRepository.addTrade(trade);
	}
	
	/** Get the trade repository.
	 * 
	 * @return tradeRepository the security's trade repository.
	 */
	public TradeRepository getTrades() {
		return tradeRepository;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(fixedDividend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lastDividend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(parValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((tradeRepository == null) ? 0 : tradeRepository.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (Double.doubleToLongBits(fixedDividend) != Double.doubleToLongBits(other.fixedDividend))
			return false;
		if (Double.doubleToLongBits(lastDividend) != Double.doubleToLongBits(other.lastDividend))
			return false;
		if (Double.doubleToLongBits(parValue) != Double.doubleToLongBits(other.parValue))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (tradeRepository == null) {
			if (other.tradeRepository != null)
				return false;
		} else if (!tradeRepository.equals(other.tradeRepository))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", type=" + type + ", lastDividend=" + lastDividend + ", fixedDividend="
				+ fixedDividend + ", parValue=" + parValue + "]";
	}
	
}
