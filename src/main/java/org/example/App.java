package org.example;

import org.example.data.PersonDAO;
import org.example.data.PersonInterfaceClass;
import org.example.data.PersonInterfaceClassRepository;
import org.example.data.dbConnection;
import org.example.model.Person;

import java.sql.SQLException;

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
        System.out.println(value);

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

PersonInterfaceClass personDAO1= new PersonInterfaceClassRepository();
        System.out.println(personDAO1.findById(50));




    }
}
