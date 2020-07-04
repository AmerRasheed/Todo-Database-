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
     //91  Person value = personDAO.create(man);
    //92    System.out.println(value);

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
              PIClass personDAO1= new PIClassRep();
              personDAO1.findByName("Amer").forEach(System.out::println);

              // 90 System.out.println(personDAO1.findAll());
              // 91 System.out.println(personDAO1.findById(23));
              // 92 System.out.println(personDAO1.findByName("Hamid"));

        personDAO1.deleteById(94);

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
// putting items
        ToodDAO itemInDB = new ToodDAO();
        Todo item = new Todo("Shopping","From Willys", LocalDate.parse("2020-07-04"),true);
        Todo item1 = new Todo("Work","Doing TodoJDBC assignment By Erik", LocalDate.parse("2020-07-03"),true);
        Todo item2 = new Todo("Work","Covering previous assigments", LocalDate.parse("2020-06-26"),true);
        Todo item3 = new Todo("House","Fixation during vacations", LocalDate.parse("2020-07-17"),true);
        Todo item4 = new Todo("Car","Besiktning", LocalDate.parse("2020-11-01"),true);

        //PIClass personDAO1= new PIClassRep();        personDAO1.findByName("Amer").forEach(System.out::println);

        TodoItemsInterface todoItems = new TodoItemsInterfaceRepositoty();
       // System.out.println(todoItems.findById(30));
       // System.out.println(todoItems.findAll());
        System.out.println(todoItems.findByAssignee(47));
       // todoItems.deleteById(130);


// Assigning item (task) to Assignee
        //93     item = itemInDB.create(item);
        //94     item1 = itemInDB.create(item1);
        //95    item2 = itemInDB.create(item2);
        //96     item3 = itemInDB.create(item3);
        //97    item4 = itemInDB.create(item4);

        //98    System.out.println(item);
      //99   System.out.println(item1);
        // 100 System.out.println(item2);

    }
}
/*

Since THIS CODE was not GENERAting itemID
        ToodDAO toodDAO= new ToodDAO();
        Todo itemAssigned = new Todo(  "Passport"   ,"Apply "    ,LocalDate.parse("2021-09-01"),false);
                              //this(0,todoItemTitle, description,todoitemDeadline              ,isDoneStatus,1);
        Todo itemValue = toodDAO.create(itemAssigned);    // THe problem was here as "toodDAO.create(itemAssigned);" was not being assigned to item object i.e. itemValue
        System.out.println(itemAssigned);
  */

/*
*  Todo item5 = new Todo("Car","Service due", LocalDate.parse("2020-07-25"),true);
        Todo item6 = new Todo("Tour","Stockholm", LocalDate.parse("2020-07-27"),true);
        Todo item7 = new Todo("House","June Bills payments", LocalDate.parse("2020-06-28"),true);
        Todo item8 = new Todo("House","HSB query address", LocalDate.parse("2020-07-01"),true);
        Todo item9 = new Todo("Work","Do certification plan", LocalDate.parse("2020-07-17"),true);
        Todo item10 = new Todo("Work","Job applications start", LocalDate.parse("2020-09-01"),true);
        Todo item11 = new Todo("Shopping","Cocos Oil from Lidl", LocalDate.parse("2020-07-03"),true);
*
* */