
<h1>**The Food vendor Application**</h1>

The customer and the vendor shoud register through the api endpoint

"/customer/register" and  "/vendor/register" respectively.

then proceed to  set their password through

Customer: /customer/{customerId}/set-password

vendor : /vendor/{vendorId}/set-password

now they can both login with their email and password into the system.


**VENDOR**

To get the list of all vendors: "/vendor/all"

To get a specific vendor: "/vendor/{id}"

The vendor will create menu through: "/home/{vendorId}/create-menu".

To get list of Orders : "/home/orders"

To get a specific customer Order: "/home/customer/{customerId}/orders"

To get a specific Vendor orders : "/home/vendor/{vendorId}/orders"


**CUSTOMER**

To get List of customers:"/customer/all"

To get a specific customer: "/customer/{id}"

To get the list of Menu: "/home/menus"

To create Order: "home/{customerId}/create-order/{vendorId}?menuId=
		 with menuId as an array taking Ids separated with commas




