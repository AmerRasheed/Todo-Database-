package org.example;

import org.example.data.*;
import org.example.model.Person;
import org.example.model.Todo;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello World!" );
        dbConnection.getConnection();

        PersonDAO personDAO= new PersonDAO();
        Person man=new Person("Tobias","Carlsson");

      //  System.out.println(man);
       Person value = personDAO.create(man);
       // System.out.println(value);

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

//PersonInterfaceClass personDAO1= new PersonInterfaceClassRepository();
PIClass personDAO1= new PIClassRep();
      //  System.out.println(personDAO1.findById(50));


        personDAO1.findByName("Amer").forEach(System.out::println);    //

//personDAO1.findAll().forEach(System.out::println);
//personDAO1.deleteById(22);


        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

        ToodDAO itemInDB = new ToodDAO();
        Todo item = new Todo("Handla","Från Willys", LocalDate.parse("2020-02-05"),true);

        item = itemInDB.create(item);
        System.out.println(item);

    }
}
