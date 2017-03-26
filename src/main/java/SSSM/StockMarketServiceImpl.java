package SSSM;

import java.time.LocalDateTime;

/** Representation of a simple stock market and the functions required.
 * 
 * @author Calum
 *
 */
public class StockMarketServiceImpl implements StockMarketService {

	private StockRepository stockRepository;
	private TradeServiceImpl tradeService;
	
	/** Create a new stock repository for the stock market.
	 * 
	 */
	public StockMarketServiceImpl() {
		stockRepository = new StockRepository();
		tradeService = new TradeServiceImpl(stockRepository);
	}
	
	/** Record a new security that has been set up.
	 * 
	 * @param symbol A string containing the securities symbol. 
	 * @param type A StockType representing the security type.
	 * @param lastDividend The last dividend on the security.
	 * @param fixedDividend Value of fixed dividend.
	 * @param parValue Par value of the security.
	 */
	public void recordSecurity(String symbol, StockType type, int lastDividend, int fixedDividend, int parValue) {
		stockRepository.addStock(symbol, type, lastDividend, fixedDividend, parValue);
	}
	
	/** Record a new trade that has been executed.
	 * 
	 * @param symbol A string containing the securities symbol. 
	 * @param timestamp Unique ID when the trade was booked.
	 * @param quantity Quantity of the trade.
	 * @param type Whether the trade is BUY or SELL.
	 * @param price The price the trade was booked at.
	 */
	public void recordTrade(String symbol, LocalDateTime timestamp, int quantity, TradeType type, double price){
		try {
			tradeService.addTrade(symbol,timestamp, quantity, type, price);
		} catch (StockNotFoundException e) {
			// In real world would determine how handle this.
			e.printStackTrace();
		}
		
	}
	
	/** Calculate the dividend yield for a security based on the price input.
	 * 
	 * @param symbol A string containing the securities symbol.
	 * @param price The price the security is currently trading at.
	 * @return double representing the dividend yield.
	 */
	public double calcDividendYield(String symbol, double price) {
		
		Stock stock = stockRepository.getStock(symbol);
		// Would have to handle this as proper exception.
		if(stock == null) {
			return 0;
		}
		
		double dividendYield; 
		
		if(price == 0) {
			return 0;
		}
		
		if(stock.getType() == StockType.COMMON) {
			dividendYield = stock.getLastDividend() / price ;
		}
		else {
			// Need to divide fixed dividend by 100 to get %
			dividendYield = ((stock.getFixedDividend()/100) * stock.getParValue()) / price ;
		}
		
		return dividendYield;
	}
	
	/** Calculate the P/E ratio of a security based on the price input.
	 * 
	 * @param symbol A string containing the securities symbol.
	 * @param price The price the security is currently trading at.
	 * @return double representing the P/E ratio.
	 */
	public double calcPERatio(String symbol, double price) {
		
		Stock stock = stockRepository.getStock(symbol);
		// Would have to handle this as proper exception.
		if(stock == null) {
			return 0;
		}
		double lastDividend = stock.getLastDividend();

		if(lastDividend == 0) {
			return 0;
		}
		else {
			return price / lastDividend ;
		}
	}
	
	/** Calculate the volume weighted price of the security based on the trades
	 *  in the past 15 minutes.
	 *  
	 * @param symbol A string containing the securities symbol.
	 * @return double representing the volume weighted price of the security.
	 */
	public double calcVolumeWeightedPrice(String symbol) {
		
		double sumPriceQuantity = 0;
		double sumQuantity = 0 ;
		
		try {
			for(Trade trade : tradeService.getLast15MinutesTrades(symbol)) {
				sumPriceQuantity += trade.getPrice() * trade.getQuantity() ;
				sumQuantity += trade.getQuantity() ;
			}
		
			if(sumQuantity == 0) {
				return 0 ;
			}
			else {
				return sumPriceQuantity / sumQuantity ;
			}
		} catch (StockNotFoundException e) {
			// Would have to handle this as proper exception.
			return 0;
		}
	}
	/** Calculate the All Share Index using the Weighted Average price 
	 *  of each stock to calculate the geometric mean.
	 * 
	 * @return double representing the geometric mean price of all securities.
	 */
	public double calcAllSharesIndex() {
		
		double volumeWeightedPrice = 0;
		double sumVolumeWeightedPrice = 0;
		double stockCount = 0;
		
		for(Stock stock : stockRepository.getAllStocks()) {
			
			volumeWeightedPrice = calcVolumeWeightedPrice(stock.getSymbol());
			
			// If the volume weighted price of any security is 0 then we exclude
			// from calculation.  Real world would confirm with business the logic
			// for this scenario.
			if(volumeWeightedPrice != 0){
				// Don't want to multiply by 0.
				if(sumVolumeWeightedPrice == 0){
					sumVolumeWeightedPrice = volumeWeightedPrice;
				} 
				else {
					sumVolumeWeightedPrice = sumVolumeWeightedPrice * volumeWeightedPrice;
				}
				stockCount++;
			}
		}
		
		if(stockCount == 0) {
			return 0;
		}
		
		return Math.pow(sumVolumeWeightedPrice, 1.0/stockCount);
	}
}
