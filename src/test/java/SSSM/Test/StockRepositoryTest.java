package SSSM.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SSSM.Stock;
import SSSM.StockRepository;
import SSSM.StockType;

public class StockRepositoryTest {
	
	StockRepository repository ;
	
	@Before
	public void setupData() {
		repository = new StockRepository();
	}
	
	@Test
	public void addTEA() {
		Stock stock = new Stock("TEA", StockType.COMMON, 0, 0, 100);
		repository.addStock(stock);
		assertEquals("TEA",repository.getStock("TEA").getSymbol());
		assertEquals(StockType.COMMON,repository.getStock("TEA").getType());
		assertEquals(0,repository.getStock("TEA").getLastDividend(),0);
		assertEquals(0,repository.getStock("TEA").getFixedDividend(),0);
		assertEquals(100,repository.getStock("TEA").getParValue(),0);
		
	}
	@Test
	public void addPOP() {
		repository.addStock("POP", StockType.COMMON, 8, 0, 100);
		assertEquals("POP",repository.getStock("POP").getSymbol());
		assertEquals(StockType.COMMON,repository.getStock("POP").getType());
		assertEquals(8,repository.getStock("POP").getLastDividend(),0);
		assertEquals(0,repository.getStock("POP").getFixedDividend(),0);
		assertEquals(100,repository.getStock("POP").getParValue(),0);
	}
	@Test
	public void addALE() {
		Stock stock = new Stock("ALE", StockType.COMMON, 23, 0, 60);
		repository.addStock(stock);
		assertEquals("ALE",repository.getStock("ALE").getSymbol());
		assertEquals(StockType.COMMON,repository.getStock("ALE").getType());
		assertEquals(23,repository.getStock("ALE").getLastDividend(),0);
		assertEquals(0,repository.getStock("ALE").getFixedDividend(),0);
		assertEquals(60,repository.getStock("ALE").getParValue(),0);
	}
	@Test
	public void addGIN() {
		repository.addStock("GIN", StockType.PREFERRED, 8, 2, 100);
		assertEquals("GIN",repository.getStock("GIN").getSymbol());
		assertEquals(StockType.PREFERRED,repository.getStock("GIN").getType());
		assertEquals(8,repository.getStock("GIN").getLastDividend(),0);
		assertEquals(2,repository.getStock("GIN").getFixedDividend(),0);
		assertEquals(100,repository.getStock("GIN").getParValue(),0);
	}
	@Test
	public void addJOE() {
		Stock stock = new Stock("JOE", StockType.COMMON, 13, 0, 250 );
		repository.addStock(stock);
		assertEquals("JOE",repository.getStock("JOE").getSymbol());
		assertEquals(StockType.COMMON,repository.getStock("JOE").getType());
		assertEquals(13,repository.getStock("JOE").getLastDividend(),0);
		assertEquals(0,repository.getStock("JOE").getFixedDividend(),0);
		assertEquals(250,repository.getStock("JOE").getParValue(),0);
	}

}
