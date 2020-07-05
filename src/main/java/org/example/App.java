package org.example;

import org.example.data.*;
import org.example.model.Person;
import org.example.model.Todo;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    /*
    person TABLE DB sktech

    person_id   first_name       last_name
      1	            Amer             Rasheed
     23         	Habib       	Jaalib
     33         	Hamid       	Pia
     47     	    Tobias      	Carlsson
     95         	Shakeel     	Ahmed
     100	        Shakeel     	Ahmed
    *
    todo_item TABLE DB sketch
todo_id     title        description           deadline     done        assignee_id
* '25', 'Handla', 'Från Willys', '2020-02-05', '1', '1'
'27', 'Shopping', 'From Willys', '2020-07-04', '1', '47'
'28', 'Work', 'Doing TodoJDBC assignment By Erik', '2020-07-03', '1', '47'
'29', 'Shopping', 'From Willys', '2020-07-04', '1', '1'
'30', 'Work', 'Doing TodoJDBC assignment By Erik', '2020-07-03', '1', '1'
'111', 'Passport', 'Apply ', '2021-09-01', '0', '1'
'127', 'House', 'Fixation during vacations', '2020-07-17', '1', '1'
'128', 'Car', 'Besiktning', '2020-11-01', '1', '1'
'129', 'Shopping', 'From Willys', '2020-07-04', '1', '1'
'150', 'A', 'B', '2020-08-17', '1', '95'
*/
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        dbConnection.getConnection();

        PersonDAO personDAO = new PersonDAO();
        Person man = new Person("Tobias", "Carlsson");
        Person man1 = new Person("Shakeel", "Ahmed");
        //  System.out.println(man);
        //91  Person value = personDAO.create(man);
        //92    System.out.println(value);

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        PIClass personDAO1 = new PIClassRep();
        personDAO1.findByName("Amer").forEach(System.out::println);
        //88 personDAO1.create(man1);
        // 90 System.out.println(personDAO1.findAll());
        // 91 System.out.println(personDAO1.findById(23));
        // 92 System.out.println(personDAO1.findByName("Hamid"));

        //87        personDAO1.deleteById(98);


        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
// putting items
        ToodDAO itemInDB = new ToodDAO();
        Todo item = new Todo("Shopping", "From Willys", LocalDate.parse("2020-07-04"), true);
        Todo item1 = new Todo("Work", "Doing TodoJDBC assignment By Erik", LocalDate.parse("2020-07-03"), true);
        Todo item2 = new Todo("Work", "Covering previous assigments", LocalDate.parse("2020-06-26"), true);
        Todo item3 = new Todo("House", "Fixation during vacations", LocalDate.parse("2020-07-17"), true);
        Todo item4 = new Todo("Car", "Besiktning", LocalDate.parse("2020-11-01"), true);
        Todo item5 = new Todo("A", "B", LocalDate.parse("2020-08-17"), true);

        //PIClass personDAO1= new PIClassRep();        personDAO1.findByName("Amer").forEach(System.out::println);

        TodoItemsInterface todoItems = new TodoItemsInterfaceRepositoty();
        System.out.println("findbyId implemented");
        System.out.println(" ");
        System.out.println(todoItems.findById(30));
        // System.out.println(todoItems.findAll());
        // System.out.println(todoItems.findByAssignee(47));
        // System.out.println(todoItems.findByAssigneeName(man));  // For FindbyAssignee Name

        System.out.println("findbyDoneStatus implemented");
        System.out.println(" ");
        System.out.println(todoItems.findByDoneStatus(true));
        //86    todoItems.deleteById(144);


// Assigning item (task) to Assignee
        //93     item = itemInDB.create(item);
        //94     item1 = itemInDB.create(item1);
        //95    item2 = itemInDB.create(item2);
        //96     item3 = itemInDB.create(item3);
        //97    item4 = itemInDB.create(item4);

        //98    System.out.println(item);
        //99   System.out.println(item1);
        // 100 System.out.println(item2);

        //89 item4 = itemInDB.create(item4);  // Adding another item to DB
        //  item5=itemInDB.create(item5);
    }
}
