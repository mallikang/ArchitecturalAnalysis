# Multi-Thread-Restaurant

Our restaurant is an upscale à la carte restaurant, that allows customers to order one of several three
course menus. This restaurant has one or more waiters. Each waiter waits on one or more customers. Each
waiter serves each customer a three-course menu (an appetizer, a main course, and a dessert).

The waiter must serve only one course at a time, i.e., the waiter must wait for the customer to complete
one course before serving another course. Once the customer completes a course, he/she must wait for the
waiter to serve the next course. This concept is somewhat similar to the producer-consumer example.

The synchronized object in this assignment is a table. Each waiter serves one or more tables. Each customer
eats at a single table allotted to that customer.

Please see <a href ="MultiThreadedRestaurant.pdf">MultiThreadedRestaurant.pdf</a> for detail
