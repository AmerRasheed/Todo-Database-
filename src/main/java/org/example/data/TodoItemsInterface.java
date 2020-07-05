package org.example.data;

import org.example.model.Person;
import org.example.model.Todo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TodoItemsInterface {

    Todo create(Todo todo);
    Collection<Todo> findAll();
    Optional<Todo> findById(int id);
    List<Todo> findByDoneStatus(boolean isDone);
    Collection<Todo> findByAssignee(int AssigneeId);
    Collection<Todo> findByAssigneeName(Person person);
    Collection<Todo> findByUnassigneeTodoItems();
    int deleteById(int todoItemsId);
}
