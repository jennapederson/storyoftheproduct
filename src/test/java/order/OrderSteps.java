package order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.AsParameterConverter;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

public class OrderSteps {

	private Order order;
	private Exception exception;
	private List<Order> orders = new ArrayList<Order>();
	
	@AsParameterConverter
	public OrderItemType convertOrderItemType(String type) {
		OrderItemType result = null;
		try {
			if (type != null) {
				result = OrderItemType.valueOf(type.toUpperCase().trim());
			}
		} catch (Exception e) {
			// nop
		}
		return result;
	}
	
	@Given("a new order")
	public void createNewOrder(){
		this.order = new Order();
	}
	
	@Given("a void order")
	public void createVoidOrder() {
		order = new Order();
		order.markVoid();
		orders.add(order);
	}

	@When("order is marked void")
	public void markOrderVoid() {
		order.markVoid();
	}

	@When("querying its state")
	public void getOrderState() {

	}

	@When("item ($sku, $description, $type, $price) is added to the order")
	public void addItem(String sku, String description, OrderItemType type, BigDecimal price){
		OrderItem item = new OrderItem(sku, description, type, price);
		this.order.addItem(item);
	}
	
	@When("items are added to the order $items")
	public void addItems(ExamplesTable items){
		for (Map<String, String> row : items.getRows()) {
			String sku = row.get("sku");
			String description = row.get("description");
			String type = row.get("type");
			String price = row.get("price");
			
			addItem(sku, description, convertOrderItemType(type), new BigDecimal(price));
			
		}
	}
	
	@Then("the order item count is $expected")
	public void assertOrderItemCount(int expected){
		Assert.assertEquals(expected, this.order.getOrderItemCount());
	}
	
	@Then("there is no error")
	public void assertNoError(){
		Assert.assertNull(exception);
	}
	
	@Then("the order state is void")
	@Alias("void is returned")
	public void assertOrderVoid() {
		Assert.assertTrue(order.isVoid());
	}

	@Then("the item with sku $sku is void")
	public void assertItemVoid(String sku) {
		Assert.assertTrue(order.getOrderItem(sku).isVoid());
	}

	@Then("the order total is $expected")
	public void assertOrderTotal(BigDecimal expected) {
		Assert.assertEquals(expected, this.order.getTotal());
	}
}
