package SSSM.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import SSSM.Trade;
import SSSM.TradeType;

import java.time.LocalDateTime;

public class TradeTest {
	@Test
	public void addTrade() {
		Trade trade= new Trade(LocalDateTime.now(), 10, TradeType.BUY, 100);
		
		assertEquals(10,trade.getQuantity(),0);
		assertEquals(TradeType.BUY,trade.getType());
		assertEquals(100,trade.getPrice(),0);
	}

}
