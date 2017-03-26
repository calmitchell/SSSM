package SSSM.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SSSM.StockMarketServiceImpl;
import SSSM.StockType;
import SSSM.TradeType;

import java.time.LocalDateTime;

public class StockMarketServiceImplTest {
	
	StockMarketServiceImpl stockMarket ;
	
	@Before
	public void setupData() {
		stockMarket= new StockMarketServiceImpl();
		
		stockMarket.recordSecurity("TEA", StockType.COMMON, 0, 0, 100);
		stockMarket.recordSecurity("POP", StockType.COMMON, 8, 0, 100);
		stockMarket.recordSecurity("ALE", StockType.COMMON, 23, 0, 60);
		stockMarket.recordSecurity("GIN", StockType.PREFERRED, 8, 2, 100);
		stockMarket.recordSecurity("JOE", StockType.COMMON, 13, 0, 250);
		
		LocalDateTime  now = LocalDateTime.now();
		LocalDateTime nowMinus30 = now.minusMinutes(30);
		LocalDateTime nowMinus20 = now.minusMinutes(20);
		LocalDateTime nowMinus10 = now.minusMinutes(10);
		LocalDateTime nowMinus05 = now.minusMinutes(5);

		stockMarket.recordTrade("TEA", nowMinus30, 10, TradeType.BUY, 1);
		stockMarket.recordTrade("TEA", nowMinus20, 10, TradeType.SELL, 2);
		stockMarket.recordTrade("TEA", nowMinus10, 10, TradeType.BUY, 3);
		stockMarket.recordTrade("TEA", nowMinus05, 10, TradeType.SELL, 4);
		stockMarket.recordTrade("TEA", now, 10, TradeType.BUY, 5);
		
		stockMarket.recordTrade("POP", nowMinus30, 1, TradeType.BUY, 1);
		stockMarket.recordTrade("POP", nowMinus20, 2, TradeType.SELL, 2);
		stockMarket.recordTrade("POP", nowMinus10, 3, TradeType.BUY, 3);
		stockMarket.recordTrade("POP", nowMinus05, 4, TradeType.SELL, 4);
		stockMarket.recordTrade("POP", now, 5, TradeType.BUY, 5);
		
		stockMarket.recordTrade("ALE", nowMinus10, 100, TradeType.BUY, 10);
		stockMarket.recordTrade("ALE", nowMinus05, 200, TradeType.BUY, 20);
		stockMarket.recordTrade("ALE", now, 300, TradeType.BUY, 30);
		
		stockMarket.recordTrade("GIN", nowMinus10, 50, TradeType.BUY, 100);
		stockMarket.recordTrade("GIN", nowMinus05, 50, TradeType.BUY, 200);
		stockMarket.recordTrade("GIN", now, 50, TradeType.BUY, 300);
		
		stockMarket.recordTrade("JOE", nowMinus10, 30, TradeType.BUY, 50);
		stockMarket.recordTrade("JOE", nowMinus05, 40, TradeType.BUY, 51);
		stockMarket.recordTrade("JOE", now, 50, TradeType.BUY, 52);
	}
	
	@After
	public void cleanData() {
		stockMarket = null;
	}
	

	// last dividend = 0 => yield = 0
	@Test
	public void dividendYieldTEA() {
		double dividendYield = stockMarket.calcDividendYield("TEA",100);
		assertEquals(0,dividendYield,0);
	}
	
	// last dividend = 8, price = 10 => 0.80
	@Test
	public void dividendYieldPOP() {
		double dividendYield = stockMarket.calcDividendYield("POP",10.0);
		assertEquals(0.8,dividendYield,0);
	}
	
	// last dividend = 23, price = 50 => 0.46
	@Test
	public void dividendYieldALE() {
		double dividendYield = stockMarket.calcDividendYield("ALE",50.0);
		assertEquals(0.46,dividendYield,0);
	}
	
	// Fixed dividend of 2% on par value of 100 => 2 per share.  Yield
	// therefore should be 2 / price (100) making 0.02
	@Test
	public void dividendYieldGIN() {
		double dividendYield = stockMarket.calcDividendYield("GIN",100.0);
		assertEquals(0.02,dividendYield,0);
	}
	
	// last dividend = 13, price = 10 => 1.3
	@Test
	public void dividendYieldJOE() {
		double dividendYield = stockMarket.calcDividendYield("JOE",10.0);
		assertEquals(1.3,dividendYield,0);
	}
	
	// Price = 100, dividend = 0 => 0
	@Test
	public void pERatioTEA() {
		double pERatio = stockMarket.calcPERatio("TEA", 100.0);
		assertEquals(0.0,pERatio,0);
	}
	
	// Price = 10, dividend = 8 => 1.25
	@Test
	public void pERatioPOP() {
		double pERatio = stockMarket.calcPERatio("POP", 10.0);
		assertEquals(1.25,pERatio,0);
	}

	// Price = 50, dividend = 23 => 2.17
	@Test
	public void pERatioALE() {
		double pERatio = stockMarket.calcPERatio("ALE", 50.0);
		assertEquals(2.17,pERatio,0.005);
	}

	// Price = 100, dividend = 8 => 12.5
	@Test
	public void pERatioGIN() {
		double pERatio = stockMarket.calcPERatio("GIN", 100.0);
		assertEquals(12.5,pERatio,0);
	}

	// Price = 10, dividend = 13 => 0.77
	@Test
	public void pERatioJOE() {
		double pERatio = stockMarket.calcPERatio("JOE", 10.0);
		assertEquals(0.77,pERatio,0.005);
	}
	
	// Time | Traded price | Quantity | Traded price * quantity
	// -30  | 1            | 10       | 10  --exclude due to time
	// -20  | 2            | 10       | 20  --exclude due to time
	// -10  | 3            | 10       | 30
	// -5   | 4            | 10       | 40
	// now  | 5            | 10       | 50
	// => Sum Traded price * quantity = 30 + 40 + 50 = 120
	// => Sum Quantity = 30
	// => Volume Weighted Price = 120 / 30 = 4.
	@Test
	public void volumeWeightedPriceTEA() {
		double volumeWeightedPrice =stockMarket.calcVolumeWeightedPrice("TEA");
		assertEquals(4,volumeWeightedPrice,0);
	}
	
	// Time | Traded price | Quantity | Traded price * quantity
	// -30  | 1            | 1        | 1  --exclude due to time
	// -20  | 2            | 2        | 4  --exclude due to time
	// -10  | 3            | 3        | 9
	// -5   | 4            | 4        | 16
	// now  | 5            | 5        | 25
	// => Sum Traded price * quantity = 9 + 16 + 25 = 50
	// => Sum Quantity = 12
	// => Volume Weighted Price = 50 / 12 = 4.17.
	@Test
	public void volumeWeightedPricePOP() {
		double volumeWeightedPrice = stockMarket.calcVolumeWeightedPrice("POP");
		assertEquals(4.17,volumeWeightedPrice,0.005);
	}
	
	// Time | Traded price | Quantity | Traded price * quantity
	// -10  | 10           | 100      | 1000
	// -5   | 20           | 200      | 4000
	// now  | 30           | 300      | 9000
	// => Sum Traded price * quantity = 14000
	// => Sum Quantity = 600
	// => Volume Weighted Price = 14000 / 600 = 23.33
	@Test
	public void volumeWeightedPriceALE() {
		double volumeWeightedPrice = stockMarket.calcVolumeWeightedPrice("ALE");
		assertEquals(23.33,volumeWeightedPrice,0.005);
	}
	
	// Time | Traded price | Quantity | Traded price * quantity
	// -10  | 100          | 50       | 5000
	// -5   | 200          | 50       | 10000
	// now  | 300          | 50       | 15000
	// => Sum Traded price * quantity = 30000
	// => Sum Quantity = 150
	// => Volume Weighted Price = 30000 / 150 = 200
	@Test
	public void volumeWeightedPriceGIN() {
		double volumeWeightedPrice = stockMarket.calcVolumeWeightedPrice("GIN");
		assertEquals(200,volumeWeightedPrice,0);
	}

	// Time | Traded price | Quantity | Traded price * quantity
	// -10  | 50           | 30       | 1500
	// -5   | 51           | 40       | 2040
	// now  | 52           | 50       | 2600
	// => Sum Traded price * quantity = 6140
	// => Sum Quantity = 120
	// => Volume Weighted Price = 6140 / 120 = 51.17.	
	@Test
	public void volumeWeightedPriceJOE() {
		double volumeWeightedPrice = stockMarket.calcVolumeWeightedPrice("JOE");
		assertEquals(51.17,volumeWeightedPrice,0.005);
	}
	
	// Symbol | VWPrice
	// TEA    | 4 
	// POP    | 4.17
	// ALE    | 23.33
	// GIN    | 200
	// JOE    | 51.17
	// 5th root of 3982503.7896 = 20.89
	@Test
	public void geometricMean() {
		double geometricMean = stockMarket.calcAllSharesIndex();
		assertEquals(20.89,geometricMean,0.005);
	}
}
