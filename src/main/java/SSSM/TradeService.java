package SSSM;

import java.time.LocalDateTime;

/** Interface for a trade service.
 * 
 * @author Calum
 *
 */
public interface TradeService {

	public void addTrade(String securitySymbol,LocalDateTime timestamp, int quantity, TradeType type, double price);
	public Trade getTrade(String securitySymbol,LocalDateTime timestamp);
}
