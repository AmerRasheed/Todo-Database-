package org.example.data;

import org.example.model.Person;
import org.example.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public class TodoItemsInterfaceRepositoty implements TodoItemsInterface{
    private static final String FIND_BY_ID = "SELECT * FROM todo_item where todo_id = ?";
    public static final String FIND_BY_NAME_LIKE = "SELECT * FROM todo_item where title LIKE ?";
    public static final String FIND_ALL="SELECT * FROM todo_item";
    private static final String DELETE_PERSON = "DELETE FROM todo_item WHERE todo_id=?" ;
    @Override
    public Todo create(Todo todo) {
//  public Person create(Person person)

        ToodDAO toodDAO = new ToodDAO();
        todo = toodDAO.create(todo);
        System.out.println(todo);
        return todo;
        /* PersonDAO personDAO = new PersonDAO();
        person = personDAO.create(person);
        System.out.println(person);

        return person;

        */
    }

    @Override
    public Collection<Todo> findAll() {
        return null;
    }

    @Override
    public Optional<Todo> findById(int id) {

        Optional<Todo> optionalTodo = Optional.empty();

        try(
                Connection connection= dbConnection.getConnection();
                PreparedStatement statement = createFindById(connection,FIND_BY_ID ,id);
                ResultSet resultSet= statement.executeQuery();
        ) {

            while(resultSet.next())
            {
                Todo todoitem= createTodo(resultSet);
                optionalTodo = Optional.ofNullable(todoitem);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return optionalTodo;
    }

    private Todo createTodo(ResultSet resultSet) throws SQLException {
        return new Todo(
                resultSet.getInt("todo_id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getObject("deadline", LocalDate.class),
                resultSet.getBoolean("done"),
                resultSet.getInt("assignee_id")
        );
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
    public Collection<Todo> findByDoneStatus(boolean isDone) {
        return null;
    }

    @Override
    public Collection<Todo> findByAssignee(int AssigneeId) {
        return null;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        return null;
    }

    @Override
    public Collection<Todo> findByUnassigneeTodoItems() {
        return null;
    }

    @Override
    public boolean deleteById(int todoItemsId) {
        return false;
    }
}
