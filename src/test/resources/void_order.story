Story: Void Order

Scenario: An order can be marked void
Given a new order
When items are added to the order
|sku|description|type|price|
|1|iPad|electronics|350.00|
Then the order item count is 1
When order is marked void
Then the order state is void

Scenario: A void order will not accept any new order items
Given a void order
When items are added to the order
|sku|description|type|price|
|1|iPad|electronics|350.00|
Then the order item count is 0

Scenario: An order can be voided with zero, one or many order items. Associated order items are also marked void when order is voided.
!-- This could be condensed into "Given an order with many items" but this shows the different scnenarios
Given a new order
When order is marked void
Then the order state is void
Given a new order
When items are added to the order
|sku|description|type|price|
|1|iPad|electronics|350.00|
When order is marked void
Then the order state is void
And the item with sku 1 is void
Given a new order
When items are added to the order
|sku|description|type|price|
|1|iPad|electronics|350.00|
|2|Toaster|appliances|20.00|
|3|Bjork Debut CD|miscellaneous|19.95|
When order is marked void
Then the order state is void
And the item with sku 1 is void
And the item with sku 2 is void
And the item with sku 3 is void

Scenario: A void order can not be un-voided.  Once void, it stays void.
!-- How do you un-void a voided order?
 
Scenario: Order total for a voided order is zero.
Given a new order
When items are added to the order
|sku|description|type|price|
|1|iPad|electronics|350.00|
Then the order item count is 1
When order is marked void
Then the order total is 0.00

Scenario: An order can be queried for void state
Given a void order
When querying its state
Then void is returned