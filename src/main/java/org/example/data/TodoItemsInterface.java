package org.example.data;

import org.example.model.Person;
import org.example.model.Todo;

import java.util.Collection;
import java.util.Optional;

public interface TodoItemsInterface {

    Todo create(Todo todo);
    Collection<Todo> findAll();
    Todo findById(int id);
    Collection<Todo> findByDoneStatus(boolean isDone);
    Collection<Todo> findByAssignee(int AssigneeId);
    Collection<Todo> findByAssignee(Person person);
    Collection<Todo> findByUnassigneeTodoItems();
    boolean deleteById(int todoItemsId);
}
