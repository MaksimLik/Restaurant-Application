# Restaraunt-System-Application
This application developed for management a restaurant.
##
You can add products, additions for products, delete comments as employees.
If you customer you can order products, additions and add products for delivery and write a comment, update comment etc.
##
Information about orders, deliveries and ... is stored in the database.
I choose MySQL, because this is popular system for managment and create a database and more intuitive to the developer.
##
This application have different folders:
* Customers folder has classes for customers windows and functionality for these windows;
* Employees folder has classes for employees windows and functionality for these windows;
* Mysql folder have common information about our DB such as connect to DB and alerts which inform us about errors and warnings.
* Tables folder has information about our objects for write/read objects from or to DB. This folder has contruction for all objects in our DB and set/get methods for objects.
##
Project has .JAR file and tables, scripts for DB. If you will want opened this application on your PC.
##
GUI developed in javafx.
##
If you want to see the controller class file or, conversely, the window code, you must correlate the name of the .java file with the .fxml file for example: EmployeesAdditionsController.java <-> EmployeesAdditionsMenu.fxml or CustomerCommentsController <-> CustomerCommentsMenu.fxml etc.
##
The application has protection from "fool". For example if you want to write an empty comment, the system will not allow you to do it. You will not be able to order delivery for a product that is already being delivered etc.
##
The database for this application has functions and stored procedures
