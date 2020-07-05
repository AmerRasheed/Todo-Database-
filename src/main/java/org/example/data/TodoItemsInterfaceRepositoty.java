package org.example.data;

import org.example.model.Person;
import org.example.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TodoItemsInterfaceRepositoty implements TodoItemsInterface {
    private static final String FIND_BY_ID = "SELECT * FROM todo_item where todo_id = ?";
    public static final String FIND_BY_NAME_LIKE = "SELECT * FROM todo_item where title LIKE ?";
    public static final String FIND_ALL = "SELECT * FROM todo_item";

    // public static final String FIND_ALL_ASSIGNEE ="SELECT * FROM todo_item\n" +  " INNER JOIN person ON todo_item.assignee_id=person.person_id where assignee_id= ?";
    public static final String FIND_ALL_ASSIGNEE = "SELECT * FROM todo_item where assignee_id= ?";
    // public static final String FIND_ALL_ASSIGNEE_NAME ="SELECT * FROM todo_item where assignee_id=person_id";
    public static final String FIND_ALL_ASSIGNEE_NAME = "SELECT * FROM todo_item\n" +
            "    INNER JOIN person ON todo_item.assignee_id=person.person_id;";
    public static final String FIND_ALL_BY_DONE_STATUS = "SELECT * FROM todo_item where done =?";
    private static final String DELETE_PERSON = "DELETE FROM todo_item WHERE todo_id=?";
    public static final String SELECT_FROM_TODO_ITEM_WHERE_DONE = "SELECT * FROM todo_item where done =?";

    //SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
    //FROM Orders
    //INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;
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
        List<Todo> todoList = new ArrayList<>();
        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                todoList.add(createTodo(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Optional<Todo> findById(int id) {

        Optional<Todo> optionalTodo = Optional.empty();

        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = createFindById(connection, FIND_BY_ID, id);
                ResultSet resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {
                Todo todoitem = createTodo(resultSet);
                optionalTodo = Optional.ofNullable(todoitem); // It will return everytype of person including NULL
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

        PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
        statement.setInt(1, id);
        return statement;
    }

    private PreparedStatement createFindByDoneStatus(Connection connection, String SELECT_FROM_TODO_ITEM_WHERE_DONE, boolean id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_TODO_ITEM_WHERE_DONE);
        statement.setBoolean(1, id);
        return statement;
    }

    private PreparedStatement createFindByAssignee(Connection connection, String FIND_ALL_ASSIGNEE, int id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(FIND_ALL_ASSIGNEE);
        statement.setInt(1, id);
        return statement;
    }

    private PreparedStatement createFindByAssigneePerson(Connection connection, String FIND_ALL_ASSIGNEE, Person person) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(FIND_ALL_ASSIGNEE);
        statement.setString(1, person.getFirstName());
        // statement.setString(2,person.getLastName());
        statement.execute();
        return statement;
    }

    @Override
    public List<Todo> findByDoneStatus(boolean isDone) {
        List<Todo> todoList = new ArrayList<>();
        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = createFindByDoneStatus(connection, SELECT_FROM_TODO_ITEM_WHERE_DONE, isDone);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                todoList.add(createTodo(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return todoList;

    }

    @Override
    public Collection<Todo> findByAssignee(int AssigneeId) {
        List<Todo> todoList = new ArrayList<>();
        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = createFindByAssignee(connection, FIND_ALL_ASSIGNEE, AssigneeId);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                todoList.add(createTodo(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Collection<Todo> findByAssigneeName(Person person) {
        List<Todo> todoList = new ArrayList<>();
        try (
                Connection connection = dbConnection.getConnection();
                //   PreparedStatement statement = createFindByAssigneePerson(connection,FIND_ALL_ASSIGNEE_NAME,person);
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_ASSIGNEE_NAME);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                todoList.add(createTodo(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Collection<Todo> findByUnassigneeTodoItems() {
        return null;
    }

    @Override
    public int deleteById(int todoItemsId) {
        int rowsAffected = 0;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERSON)) {
            statement.setInt(1, todoItemsId);
            rowsAffected = statement.executeUpdate();
            rowsAffected++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected;
    }
}

