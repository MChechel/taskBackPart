# taskBackPart

Application settings:
  In the file application.properties you will have to update lines that responsible for DB connection:
    spring.datasource.url=jdbc:mysql:**//127.0.0.1:3306/homework**
    spring.datasource.username=**root**
    spring.datasource.password=**root**
    
   To initiate application, you need to run main method of **HomeworkTaskBankApplication**.class
   
   Back end runs on **localhost:8080**, but there is no need to use it on its own with PostMan or something like that - it is connected to the front end and all created functionality can be accessed at **localhost:4200**
   

  As requested, user can create dump file with DB data. It will be saved in repository folder: dump.sql
