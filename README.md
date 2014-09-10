#Demo project

##Deployment steps
1. Clone repo
2. Create database
3. Configure database in src/main/java/com/epam/demo/config/ConfigurationDataSource.java
4. Run app on Tomcat

##Resources

| URI       | Method |       Description      |
|-----------|--------|------------------------|
| food      | POST   | create food item       |
| food/{id} | GET    | get food item by id    |
| food/{id} | PUT    | update food item by id |
| food/{id} | DELETE | delete food item       |
| foods     | GET    | get every food item    |
