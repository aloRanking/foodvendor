
<h1>**The Food vendor Application**</h1>

The customer and the vendor should register through the api endpoint

"api/v1/customers/register" and  "api/v1/vendors/register" respectively.

then proceed to  set their password through

Customer: api/v1/customers/{customerId}/set-password

vendor : api/v1/vendors/{vendorId}/set-password

now they can both login with their email and password into the system.


**VENDOR**
all API endpoints should  be added to this 'api/v1' as suffix

To get the list of all vendors: "/vendors"

To get a specific vendor: "/vendor/{id}"

The vendor will create menu through: "/vendor/{vendorId}/create-menu".

To get list of Orders : "/orders"

To get a specific customer Order: "/customer/{customerId}/orders"

To get a specific Vendor orders : "vendor/{vendorId}/orders"


**CUSTOMER**

To get List of customers:"/customers/all"

To get a specific customer: "/customers/{id}"

To get the list of Menu: "/menus"

To create Order: "customer/{customerId}/create-order/vendor/{vendorId}?menuId=
		 with menuId as an array taking Ids separated with commas




