package org.example.data;

import org.example.model.Todo;

import java.sql.*;

public class ToodDAO {
    public Todo create(Todo item) {
        if (item.getTodoId() != 0)
            throw new IllegalArgumentException("Not valid entry"); // try update instead
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet itemSet = null;

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement("INSERT INTO todo_item (title,description,deadline,done,assignee_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //  statement.setInt(1,people.getPersonId());
            statement.setString(1,item.getTodoItemTitle());
            statement.setString(2,item.getDescription());
            statement.setObject(3,item.getTodoitemDeadline());
            statement.setBoolean(4,item.getDoneStatus());
            statement.setInt(5,item.getAssigneeId());


            statement.execute();

            //peopleSet=null;
            itemSet = statement.getGeneratedKeys();

            while (itemSet.next())
            {
                item= new Todo(
                        itemSet.getInt(1),
                        item.getTodoItemTitle(),
                        item.getDescription(),
                        item.getTodoitemDeadline(),
                        item.getDoneStatus(),
                        item.getAssigneeId()
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try{
                if(itemSet!=null)
                    itemSet.close();
                if(statement!=null)
                    statement.close();
                if(connection!=null)
                    connection.close();
            }catch (SQLException ex)
            {ex.printStackTrace();
            }
        }
        return item;
    }
}
