Demonstrate that an order can have 0, 1 or multiple order items
Demonstrate that order items have cost, type, description and sku properties
The order item type is constrained to one of the following: electronics, appliances, miscellaneous
Demonstrate that order items cannot have negative prices
All order item properties are required and cannot be nullable

Story: Add Item To Order

Scenario: An order can have 0, 1, or multiple order items
Given a new order
When item (1, iPad, ELECTRONICS, 350.00) is added to the order
Then the order item count is 1
When item (2, Dishwasher, APPLIANCES, 450.00) is added to the order
Then the order item count is 2

Scenario: Order items have cost, type, description, and sku properties
Given a new order
When items are added to the order
|sku|description|type|price|
|1|iPad|ELECTRONICS|350.00|
|1|Dishwasher|APPLIANCES|450.00|
Then the order item count is 2