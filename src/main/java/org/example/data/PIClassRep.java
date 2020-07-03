package org.example.data;

import org.example.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PIClassRep implements PIClass {
    private static final String FIND_BY_ID = "SELECT * FROM person where person_id = ?";
    public static final String FIND_BY_NAME_LIKE = "SELECT * FROM person where first_name LIKE ?";
    public static final String FIND_ALL="SELECT * FROM person";
    private static final String DELETE_PERSON = "DELETE FROM person WHERE person_id=?" ;

    @Override
    public Person create(Person person) {
        PersonDAO personDAO = new PersonDAO();
        person = personDAO.create(person);
        System.out.println(person);

        return person;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> personList=new ArrayList<>();

        try(
                Connection connection=dbConnection.getConnection();
                PreparedStatement statement=connection.prepareStatement(FIND_ALL);
                ResultSet resultSet=statement.executeQuery();
        ) {
            while(resultSet.next())
            {
                personList.add(createPerson(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return personList;
    }

    @Override
    public Optional<Person> findById(int id) {
        Optional<Person> optionalPerson = Optional.empty();

        try(
                Connection connection= dbConnection.getConnection();
                PreparedStatement statement = createFindById(connection,FIND_BY_ID ,id);
                ResultSet resultSet= statement.executeQuery();
        ) {

            while(resultSet.next())
            {
                optionalPerson= Optional.of(createPerson(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return optionalPerson;
    }
    private Person createPerson(ResultSet resultSet) throws SQLException {
        return new Person(
                resultSet.getInt("person_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    }

    private PreparedStatement createFindById(Connection connection, String FIND_BY_ID, int id) throws SQLException {

        PreparedStatement statement= connection.prepareStatement(FIND_BY_ID);
        statement.setInt(1,id);
        return statement;
    }
    @Override
    public Collection<Person> findByName(String name) {
        List<Person> result = new ArrayList<>();
        Connection connection= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;

        try{
//&&13
            connection=dbConnection.getConnection();
            statement=connection.prepareStatement(FIND_BY_NAME_LIKE);  // By Right click ->Refactor ->Introduce CONSTANT  ->// &&13  A UP
            //connection ->statement ->resultSet
            statement.setString(1,name.concat("%")); // Actually its replacement of whole createBY_CODE_Method
            // We probably do so since name QUERY is LIKE but in case of id and code there were all different values so we do like this
            resultSet=statement.executeQuery();

            while(resultSet.next())
            {
                result.add(createPerson(resultSet));   //&&14  -> //&&14 A
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally {                       // &&15
            try{

                if(resultSet!=null)
                    resultSet.close();
                if(statement!=null)
                    statement.close();
                if(connection!=null)
                    connection.close();
            }catch (SQLException ex)
            {ex.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public int deleteById(int id) {

        int rowsAffected = 0;
/*
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERSON)) {
            rowsAffected = statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
        return rowsAffected;


    }
}
