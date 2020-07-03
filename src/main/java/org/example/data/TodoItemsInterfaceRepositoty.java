package org.example.data;

import org.example.model.Person;
import org.example.model.Todo;

import java.util.Collection;

public class TodoItemsInterfaceRepositoty implements TodoItemsInterface{
    @Override
    public Todo create(Todo todo) {
        return null;
    }

    @Override
    public Collection<Todo> findAll() {
        return null;
    }

    @Override
    public Todo findById(int id) {
        return null;
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
