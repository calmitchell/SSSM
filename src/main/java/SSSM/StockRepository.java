package SSSM;

import java.util.Collection;
import java.util.HashMap;

/** Represents a repository used to hold securities.
 * 
 * @author Calum
 *
 */
public class StockRepository {

	private HashMap<String, Stock> stockRepository;

	/** Create a stock repository in the form of a hash map.
	 */
	public StockRepository() {
		stockRepository = new HashMap<String, Stock>();
	}

	/** Add a security to the repository.
	 * 
	 * @param stock A Stock object representing the security. 
	 */
	public void addStock(Stock stock) {
		stockRepository.put(stock.getSymbol(), stock);
	}
	
	/** Add a security to the repository.
	 * 
	 * @param symbol A string containing the securities symbol. 
	 * @param type A StockType representing the security type.
	 * @param lastDividend The last dividend on the security.
	 * @param fixedDividend Value of fixed dividend.
	 * @param parValue Par value of the security.
	 */
	public void addStock(String symbol, StockType type, int lastDividend, int fixedDividend, int parValue) {
		Stock stock = new Stock(symbol, type, lastDividend, fixedDividend, parValue);
		stockRepository.put(symbol, stock);
	}
	
	/** Get's a stock from repository based on symbol.
	 * 
	 * @param symbol A string containing the securities symbol. 
	 * @return Stock The security.
	 */
	public Stock getStock(String symbol) {
		if(stockRepository.containsKey(symbol)) {
			return stockRepository.get(symbol);
		}
		else {
			return null;
		}
	}
	
	/** Removes stock from repository based on symbol.
	 * 
	 * @param symbol A string containing the securities symbol. 
	 */
	public void removeStock(String symbol) {
		stockRepository.remove(symbol);
	}

	/** Get all of the stocks from the repository and return as collection.
	 *  
	 * @return Collection containing all stocks.
	 */
	public Collection<Stock> getAllStocks() {
		return  stockRepository.values();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockRepository == null) ? 0 : stockRepository.hashCode());
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
		StockRepository other = (StockRepository) obj;
		if (stockRepository == null) {
			if (other.stockRepository != null)
				return false;
		} else if (!stockRepository.equals(other.stockRepository))
			return false;
		return true;
	}
}
