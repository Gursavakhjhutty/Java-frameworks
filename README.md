# WESTERN GOVERNORS UNIVERSITY 
## D287 – JAVA FRAMEWORKS
For this Course I will be making a Shop Inventory Management System for BlockHive, a Crypto Tech Solutions Company. 
## BASIC INSTRUCTIONS
For this project, you will use the Integrated Development Environment (IDE) link in the web links section of this assessment to install the IDE, IntelliJ IDEA (Ultimate Edition). All relevant links are on the course page. Please refer to the course of study for specific links. You will sign up for a free student license using your WGU.edu email address. Please see the “IntelliJ Ultimate Edition Instructions” attachment for instructions on how do this. Next you will download the “Inventory Management Application Template Code” provided in the web links section and open it in IntelliJ IDEA (Ultimate Edition). You will upload this project to a private external GitLab repository and backup regularly. As a part of this, you have been provided with a base code (starting point). 

## Modifications To Existing Files
1. Changes to the mainscreen.html file for Requirment C of Task 1
(line 14) - Title was modified from My Bicycle Shop to BlockHive Tech Solutions
(line 19) - h1 was changed to BlockHive Tech Solutions
(line 23) - Added a placeholder for the input field
(line 50-52) - Added a response for if their is no parts found
(line 58) - Added a placeholder for the input field
(line 90-92) - Added a response for if their is no products found
2. Created about.html file for Requirement D of Task 1 as well as modification of controllers, and mainscreen.html
(about.html) - Created file
(mainscreen.html line 18-20) - Added a container that has a navigation reference to the about page
(MainScreenController.java line 55-62) - Added Mapping for returning to the mainscreen and going to the about page
(mainscreen.html line 11) - The additional selectors attached to the CSS link was causing a error not allowing me to run the program. I am unsure why this happened. I would love a note on this if possible.
(mainscreen.html line 52/91) - Added Null handelling as I have not added any sample inventory to the project yet
(MainScreenController.java line 34-37) - I did some testing with the partrepo but later undid these changes.
3. STEP F
(mainscreen.html line 19) - changed class of the about link to make it look better
(mainscreen.html line 89) - added a Buy Now Button
(mainScreenControllerr line 72-83) - Created the buyProductReturn service which gets the inventory of a product, reduces the inventory by 1 then saves.
(ProductServiceImpl.java line 69-78) - implemented buyProduct into the ProductService
(mainscreen.html line 17) - alert for if the buy now worked or not
4. STEP E
(MainScreenControllerr.java line 109-124) - Added SampleInventoryInitializer
(ProductServiceImpl.java line 75-86) - Added a Multipack maker for products
(PartServiceImpl.java line 62-74) - Added a Multipack Maker for Parts
(InhousePart.Java line 20-22) - Added Constructor
(OutsourcedPart.Java line 20-22) - Added Constructor
5. STEP G
(Part.java line 112-127) - added getting and setting for min and max
(MainScreenController line 95-104) - modified sample parts to comply with min and max settings
(Part.java line 84-88) - added a exception thrown if inventory is outside of min and max values
(AddPartController.java line 68-80) - savePart logic
(OutsourcedPartForm.html line 26-28) - min and max inventory html requirement
(InhousePartForm.html line 25-26) - min and max inventory html requirement
(application.properties line 6) - changed the name of the database where the data is stored
6. STEP H
(PartServiceImpl.java line 74-86) Method to throw errors for min and max validation
(AddPartController.java line 81-90) Added controller Class that manages the logic
(InhousePartForm.html line 11-13) Added error on html
7. STEP I
(PartTest.java lin 160-199) Added tests for min and max
8. 