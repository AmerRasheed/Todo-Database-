package org.example.data;

import org.example.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class PersonInterfaceClassRepository implements PersonInterfaceClass {
 private static final String FIND_BY_ID = "SELECT * FROM person where person_id = ?";
    @Override
    public Person create(Person person) {
        PersonDAO personDAO = new PersonDAO();
        person = personDAO.create(person);
        System.out.println(person);

        return person;
    }
/*
*    PersonDAO PersonDAO = new PersonDAO();
        person = PersonDAO.create(person);
        System.out.println(person);
        return null;*/
    @Override
    public Collection<Person> findAll() {
        return null;
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
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
