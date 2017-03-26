package SSSM.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Test;

import SSSM.TradeServiceImpl;
import SSSM.StockNotFoundException;
import SSSM.StockRepository;
import SSSM.StockType;
import SSSM.Trade;
import SSSM.TradeType;

public class TradeServiceTest {

	private StockRepository stockRepository;
	private TradeServiceImpl tradeService;
	
	@Test
	public void testTrade() {
		stockRepository = new StockRepository();
		stockRepository.addStock("TEA", StockType.COMMON, 0, 0, 100);
		
		tradeService = new TradeServiceImpl(stockRepository);
		
		LocalDateTime now = LocalDateTime.now();
		try {
			tradeService.addTrade("TEA", now, 10, TradeType.BUY, 100);
			
			Trade trade = tradeService.getTrade("TEA", now);
			
			assertEquals(now,trade.getTimestamp());
			assertEquals(10,trade.getQuantity(),0);
			assertEquals(TradeType.BUY,trade.getType());
			assertEquals(100,trade.getPrice(),0);
		} catch (StockNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void last15() {
		stockRepository = new StockRepository();
		
		stockRepository.addStock("TEA", StockType.COMMON, 0, 0, 100);
		
		tradeService = new TradeServiceImpl(stockRepository);
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowMinus30 = now.minusMinutes(30);
		LocalDateTime nowMinus20 = now.minusMinutes(20);
		LocalDateTime nowMinus10 = now.minusMinutes(10);
		LocalDateTime nowMinus05 = now.minusMinutes(5);
		
		try {
			tradeService.addTrade("TEA", now, 10, TradeType.BUY, 100);
			tradeService.addTrade("TEA", nowMinus30, 10, TradeType.BUY, 100);
			tradeService.addTrade("TEA", nowMinus20, 10, TradeType.BUY, 100);
			tradeService.addTrade("TEA", nowMinus10, 10, TradeType.BUY, 100);
			tradeService.addTrade("TEA", nowMinus05, 10, TradeType.BUY, 100);
			
			Collection<Trade> last15 = tradeService.getLast15MinutesTrades("TEA");
			assertEquals(3,last15.size());

		} catch (StockNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void noStock() {
		stockRepository = new StockRepository();
		stockRepository.addStock("TEA", StockType.COMMON, 0, 0, 100);
		
		tradeService = new TradeServiceImpl(stockRepository);
		
		LocalDateTime now = LocalDateTime.now();
		try {
			tradeService.addTrade("BOB", now, 10, TradeType.BUY, 100);
			
		} catch (StockNotFoundException e) {
			assertEquals("Stock not found in repository to add trade",e.getMessage());
		}
	}
}
