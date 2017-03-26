package SSSM;

import java.time.LocalDateTime;

/** Interface for a simple stock market.
 * 
 * @author Calum
 *
 */
public interface StockMarketService {

	public void recordSecurity(String symbol, StockType type, int lastDividend, int fixedDividend, int parValue);
	public void recordTrade(String symbol, LocalDateTime timestamp, int quantity, TradeType type, double price);
	public double calcDividendYield(String symbol, double price);
	public double calcPERatio(String symbol, double price);
	public double calcVolumeWeightedPrice(String symbol);
	public double calcAllSharesIndex();
	
}
