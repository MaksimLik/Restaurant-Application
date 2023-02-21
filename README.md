# Restaraunt-System-Application
This application developed for management a restaurant.
####
You can add products, additions for products, delete comments etc as employees.
If you customer you can order products, additions and add products for delivery and write a comment, update comment etc.
####
Information about orders, deliveries and ... is stored in the database.
I choose MySQL, because this is popular system for managment and create a database and more intuitive to the developer. The database for this application has functions and stored procedures. DB has different queries(simple(standart select operation) and difficult(joins, groups etc.));
####
This application have different folders:
* Customers folder has classes for customers windows and functionality for these windows;
* Employees folder has classes for employees windows and functionality for these windows;
* Mysql folder have common information about our DB such as connect to DB and alerts which inform us about errors and warnings.
* Tables folder has information about our objects for write/read objects from or to DB. This folder has contruction for all objects in our DB and set/get methods for objects.
####
The application has protection from "fool". For example if you want to write an empty comment, the system will not allow you to do it. You will not be able to order delivery for a product that is already being delivered etc.
####
GUI developed in javafx.
####
Application has ~ 17 windows, which was developed in Scene Builder. Examples: 

![Registration window](https://user-images.githubusercontent.com/72620745/220447335-6f30c87c-7ab4-4b89-826c-5a4d286b8117.png)

![Employees-management-menu](https://user-images.githubusercontent.com/72620745/220447724-2b02149c-00ff-4673-9d9b-16f3038a4ccb.png)

![Employees-menu](https://user-images.githubusercontent.com/72620745/220447585-ec9a8f9f-e80f-410d-9098-741c7a4aa138.png)
####
If you want to see the controller class file or, conversely, the window code (buttons, icons and etc.), you must correlate the name of the .java file with the .fxml file for example: EmployeesAdditionsController.java <-> EmployeesAdditionsMenu.fxml, CustomerCommentsController <-> CustomerCommentsMenu.fxml etc.
####
Project has .JAR file and tables, scripts for DB. If you will want opened this application on your PC.






