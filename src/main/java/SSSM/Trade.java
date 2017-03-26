package SSSM;

import java.time.LocalDateTime;

/** Represents a simple trade.
 * 
 * @author Calum
 *
 */
public class Trade {

	private LocalDateTime timestamp;
	private int quantity;
	private TradeType type;
	private double price;
	
	/** Create trade object.
	 * 
	 * @param timestamp Unique ID when the trade was booked.
	 * @param quantity Quantity of the trade.
	 * @param type Whether the trade is BUY or SELL.
	 * @param price The price the trade was booked at.
	 */
	public Trade(LocalDateTime timestamp, int quantity, TradeType type, double price) {
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
	}

	/** Get the type of the trade.
	 * 
	 * @return TradeType enumeration for the trade type.
	 */
	public TradeType getType() {
		return type;
	}
	
	/** Set the type of the trade.
	 * 
	 * @param TradeType enumeration for the trade type.
	 */
	public void setType(TradeType type) {
		this.type = type;
	}
	
	/** Get the timestamp of the trade.
	 * 
	 * @return timestamp of the trade
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	/** Set the timestamp of the trade.
	 * 
	 * @param timestamp of the trade
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	/** Get the quantity of the trade.
	 * 
	 * @return int representing the quantity of the trade.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/** Set the quantity of the trade.
	 * 
	 * @param int representing the quantity of the trade.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/** Get the price of the trade.
	 * 
	 * @return double representing the price of a trade.
	 */
	public double getPrice() {
		return price;
	}
	
	/** Set the price of the trade.
	 * 
	 * @param double representing the price of a trade.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Trade other = (Trade) obj;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [timestamp=" + timestamp + ", quantity=" + quantity + ", type=" + type + ", price=" + price + "]";
	}
	
}
