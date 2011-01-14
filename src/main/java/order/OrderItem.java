package order;

import java.math.BigDecimal;

public class OrderItem {

	private String sku;
	private String description;
	private BigDecimal price;
	private OrderItemType category;
	private OrderItemState state = OrderItemState.NEW;

	public OrderItem(String sku, String description,
			OrderItemType category, BigDecimal price) {
		this.sku = sku;
		this.description = description;
		this.category = category;
		this.price = price;
	}
	
	public void markVoid() {
		this.state = OrderItemState.VOID;
	}

	public boolean isVoid() {
		return OrderItemState.VOID.equals(state);
	}

	public String getSku() {
		return sku;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
}
