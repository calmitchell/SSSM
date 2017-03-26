package SSSM.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Test;

import SSSM.Trade;
import SSSM.TradeRepository;
import SSSM.TradeType;

public class TradeRepositoryTest {

	@Test
	public void addTrade1() {
		TradeRepository repository;
		repository = new TradeRepository();
		
		LocalDateTime now = LocalDateTime.now();
		Trade trade = new Trade(now, 10, TradeType.BUY, 100);
		repository.addTrade(trade);
		
		assertEquals(10,repository.getTrade(now).getQuantity(),0);
		assertEquals(TradeType.BUY,repository.getTrade(now).getType());
		assertEquals(100,repository.getTrade(now).getPrice(),0);

	}
	
	@Test
	public void addTrade2() {
		TradeRepository repository;
		repository = new TradeRepository();
		
		LocalDateTime now = LocalDateTime.now();
		Trade trade = new Trade(now, 50, TradeType.SELL, 500);
		repository.addTrade(trade);
		
		assertEquals(50,repository.getTrade(now).getQuantity(),0);
		assertEquals(TradeType.SELL,repository.getTrade(now).getType());
		assertEquals(500,repository.getTrade(now).getPrice(),0);

	}
	
	@Test
	public void last15() {
		TradeRepository repository;
		repository = new TradeRepository();
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowMinus30 = now.minusMinutes(30);
		LocalDateTime nowMinus20 = now.minusMinutes(20);
		LocalDateTime nowMinus10 = now.minusMinutes(10);
		LocalDateTime nowMinus05 = now.minusMinutes(5);
		
		Trade tnow = new Trade(now, 10, TradeType.BUY, 100);
		Trade tm30 = new Trade(nowMinus30, 10, TradeType.BUY, 100);
		Trade tm20 = new Trade(nowMinus20, 10, TradeType.BUY, 100);
		Trade tm10 = new Trade(nowMinus10, 10, TradeType.BUY, 100);
		Trade tm05 = new Trade(nowMinus05, 10, TradeType.BUY, 100);

		repository.addTrade(tnow);
		repository.addTrade(tm30);
		repository.addTrade(tm20);
		repository.addTrade(tm10);
		repository.addTrade(tm05);
		
		Collection<Trade> last15 = repository.getLast15MinutesTrades();
		
		assertEquals(true,last15.contains(tnow));
		assertEquals(false,last15.contains(tm30));
		assertEquals(false,last15.contains(tm20));
		assertEquals(true,last15.contains(tm10));
		assertEquals(true,last15.contains(tm05));
	}

}
