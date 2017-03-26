package SSSM;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/** Represents a repository holding trades.  Note for the purposes of this
 *  exercise I have assumed that timestamp will be the unique key to a trade.
 *  
 * @author Calum
 *
 */
public class TradeRepository {

	private SortedMap<LocalDateTime, Trade> tradeRepository;

	/** Create a trade repository.
	 */
	public TradeRepository() {
		tradeRepository = new TreeMap<LocalDateTime, Trade>();
	}

	/** Add a trade to the repository.
	 * 
	 * @param Trade A trade object.
	 */
	public void addTrade(Trade Trade) {
		tradeRepository.put(Trade.getTimestamp(), Trade);
	}
	
	/** Remove trade from repository based on unique timestamp.
	 * 
	 * @param timestamp Key for the trade.
	 */
	public void removeTrade(LocalDateTime timestamp) {
		tradeRepository.remove(timestamp);
	}
	
	/** Get trade from repository.
	 * 
	 * @param timestamp Key for the trade.
	 * @return Trade object.
	 */
	public Trade getTrade(LocalDateTime timestamp) {
		if(tradeRepository.containsKey(timestamp)) {
			return(tradeRepository.get(timestamp));
		}
		else {
			return null;
		}
	}

	/** Get the last 15 minutes of trading activity in the repository.
	 * 
	 * @return Collection containing all trades from last 15 minutes.
	 */
	public Collection<Trade> getLast15MinutesTrades()
	{
		LocalDateTime last15 = LocalDateTime.now().minusMinutes(15);
		
        return tradeRepository.tailMap(last15).values();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tradeRepository == null) ? 0 : tradeRepository.hashCode());
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
		TradeRepository other = (TradeRepository) obj;
		if (tradeRepository == null) {
			if (other.tradeRepository != null)
				return false;
		} else if (!tradeRepository.equals(other.tradeRepository))
			return false;
		return true;
	}
	
	
}
