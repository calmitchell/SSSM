package SSSM;

import java.time.LocalDateTime;
import java.util.Collection;

/** Class representing the trade activities performed by the simple stock market.
 *  
 * @author Calum
 *
 */
public class TradeServiceImpl implements TradeService {

	private StockRepository stockRepository ;
	
	public TradeServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
	/** Get the stock repository.
	 * 
	 * @return StockRepository The stock repository.
	 */
	public StockRepository getStockRepository() {
		return stockRepository;
	}

	/** Add a trade to the appropriate stock.
	 * 
	 * @param securitySymbol A string containing the securities symbol. 
	 * @param timestamp Unique ID when the trade was booked.
	 * @param quantity Quantity of the trade.
	 * @param type Whether the trade is BUY or SELL.
	 * @param price The price the trade was booked at.
	 */
	
	public void addTrade(String securitySymbol, LocalDateTime timestamp, int quantity, TradeType type, double price) throws StockNotFoundException {
		Trade trade = new Trade(timestamp, quantity, type, price);
		Stock stock = stockRepository.getStock(securitySymbol);
		if(stock == null) {
			throw new StockNotFoundException("Stock not found in repository to add trade");
		}
		
		stock.addTrade(trade);
	}

	/** Get trade from the appropriate stock.
	 * 
	 * @param securitySymbol A string containing the securities symbol. 
	 * @param timestamp Unique ID when the trade was booked.
	 * 
	 * @return Trade The trade from the stock repository. 
	 * 	 */
	public Trade getTrade(String securitySymbol,LocalDateTime timestamp) throws StockNotFoundException {
		Stock stock =stockRepository.getStock(securitySymbol);
		if(stock == null) {
			throw new StockNotFoundException("Stock not found in repository to add trade");
		}
		return stock.getTrades().getTrade(timestamp);
	}
	
	/** Get the last 15 minutes of trade activity for appropriate stock from the stock repository.
	 * 
	 * @param securitySymbol A string containing the securities symbol.
	 * @return Collection A collection of the last 15 minutes of trade activity for stock.
	 */
	public Collection<Trade> getLast15MinutesTrades(String securitySymbol) throws StockNotFoundException {
		Stock stock =stockRepository.getStock(securitySymbol);
		if(stock == null) {
			throw new StockNotFoundException("Stock not found in repository to add trade");
		}
		return stock.getTrades().getLast15MinutesTrades();
	}

}
