package SSSM.Test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import SSSM.Stock;
import SSSM.StockType;
import SSSM.Trade;
import SSSM.TradeType;

public class StockTest {
	
	@Test
	public void addTEA() {
		Stock stock = new Stock("TEA", StockType.COMMON, 0, 0, 100 );
		assertEquals("TEA",stock.getSymbol());
		assertEquals(StockType.COMMON,stock.getType());
		assertEquals(0,stock.getLastDividend(),0);
		assertEquals(0,stock.getFixedDividend(),0);
		assertEquals(100,stock.getParValue(),100);
	}
	
	@Test
	public void addPOP() {
		Stock stock = new Stock("POP", StockType.COMMON, 8, 0, 100 );
		assertEquals("POP",stock.getSymbol());
		assertEquals(StockType.COMMON,stock.getType());
		assertEquals(0,stock.getLastDividend(),8);
		assertEquals(0,stock.getFixedDividend(),0);
		assertEquals(100,stock.getParValue(),100);
	}
	
	@Test
	public void addALE() {
		Stock stock = new Stock("ALE", StockType.COMMON, 23, 0, 60 );
		assertEquals("ALE",stock.getSymbol());
		assertEquals(StockType.COMMON,stock.getType());
		assertEquals(0,stock.getLastDividend(),23);
		assertEquals(0,stock.getFixedDividend(),0);
		assertEquals(100,stock.getParValue(),60);
	}
	
	@Test
	public void addGIN() {
		Stock stock = new Stock("ALE", StockType.PREFERRED, 8, 2, 100 );
		assertEquals("ALE",stock.getSymbol());
		assertEquals(StockType.PREFERRED,stock.getType());
		assertEquals(0,stock.getLastDividend(),8);
		assertEquals(0,stock.getFixedDividend(),2);
		assertEquals(100,stock.getParValue(),100);
	}
	@Test
	public void addJOE() {
		LocalDateTime now = LocalDateTime.now();
		Stock stock = new Stock("JOE", StockType.COMMON, 13, 0, 250 );
		Trade trade = new Trade(now, 1000, TradeType.BUY, 100);
		stock.addTrade(trade);
		assertEquals("JOE",stock.getSymbol());
		assertEquals(StockType.COMMON,stock.getType());
		assertEquals(0,stock.getLastDividend(),13);
		assertEquals(0,stock.getFixedDividend(),0);
		assertEquals(100,stock.getParValue(),250);
		assertEquals(now,stock.getTrades().getTrade(now).getTimestamp());
		assertEquals(1000,stock.getTrades().getTrade(now).getQuantity(),0);
		assertEquals(TradeType.BUY,stock.getTrades().getTrade(now).getType());
		assertEquals(100,stock.getTrades().getTrade(now).getPrice(),0);
	}

}
