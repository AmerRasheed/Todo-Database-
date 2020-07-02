package org.example.data;

import org.example.model.Person;

import java.sql.*;

public class PersonDAO {

    public Person create(Person human) {
        if(human.getPersonId()!=0)
            throw new IllegalArgumentException("try update instead");
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet personSet=null;

        try{
            connection=dbConnection.getConnection();
            statement=connection.prepareStatement("INSERT INTO person (first_name,last_name) VALUES (?,?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,human.getFirstName());
            statement.setString(2,human.getLastName());
            statement.execute();
            personSet=statement.getGeneratedKeys();

            while(personSet.next())
            {
                human=  new Person(
                personSet.getInt(1),
                human.getFirstName(),
                human.getLastName()
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try{
                if(personSet!=null)
                    personSet.close();
                if(statement!=null)
                    statement.close();
                if(connection!=null)
                    connection.close();
            }catch (SQLException ex)
            {ex.printStackTrace();
            }
        }
        return human;
    }
    /*
    *

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement("INSERT INTO person (first_name,last_name) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            //  statement.setInt(1,person.getPersonId());
            statement.setString(1,person1.getFirstName());
            statement.setString(2,person1.getLastName());

            statement.execute();

         //   personSet=null;
            personSet=statement.getGeneratedKeys();

            while (personSet.next())
            {
                person1= new Person(
                        personSet.getInt(1),
                        //people.getPersonId(),
                        person1.getFirstName(),
                        person1.getLastName()
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try{
                if(personSet!=null)
                    personSet.close();
                if(statement!=null)
                    statement.close();
                if(connection!=null)
                    connection.close();
            }catch (SQLException ex)
            {ex.printStackTrace();
            }
        }
        return person1;
    }*/
}
