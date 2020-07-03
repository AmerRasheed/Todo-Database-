package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {
        int TodoId;
        String todoItemTitle;
        String description;
        LocalDate TodoitemDeadline;
        Boolean isDoneStatus;
        int AssigneeId;

        public Todo(int todoId, String todoItemTitle, String description, LocalDate todoitemDeadline, Boolean isDoneStatus, int assigneeId) {
                TodoId = todoId;
                this.todoItemTitle = todoItemTitle;
                this.description = description;
                TodoitemDeadline = todoitemDeadline;
                this.isDoneStatus = isDoneStatus;
                AssigneeId = assigneeId;
        }

        public Todo(String todoItemTitle, String description, LocalDate todoitemDeadline, Boolean isDoneStatus) {
                this(0,todoItemTitle,description,todoitemDeadline,isDoneStatus,47);
        }

        public int getTodoId() {
                return TodoId;
        }

        public String getTodoItemTitle() {
                return todoItemTitle;
        }

        public void setTodoItemTitle(String todoItemTitle) {
                this.todoItemTitle = todoItemTitle;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDate getTodoitemDeadline() {
                return TodoitemDeadline;
        }

        public void setTodoitemDeadline(LocalDate todoitemDeadline) {
                TodoitemDeadline = todoitemDeadline;
        }

        public Boolean getDoneStatus() {
                return isDoneStatus;
        }

        public void setDoneStatus(Boolean doneStatus) {
                isDoneStatus = doneStatus;
        }

        public int getAssigneeId() {
                return AssigneeId;
        }

        public void setAssigneeId(int assigneeId) {
                AssigneeId = assigneeId;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Todo todo = (Todo) o;
                return TodoId == todo.TodoId &&
                        AssigneeId == todo.AssigneeId &&
                        Objects.equals(todoItemTitle, todo.todoItemTitle) &&
                        Objects.equals(description, todo.description) &&
                        Objects.equals(TodoitemDeadline, todo.TodoitemDeadline) &&
                        Objects.equals(isDoneStatus, todo.isDoneStatus);
        }

        @Override
        public int hashCode() {
                return Objects.hash(TodoId, todoItemTitle, description, TodoitemDeadline, isDoneStatus, AssigneeId);
        }

        @Override
        public String toString() {
                return "Todo{" +
                        "TodoId=" + TodoId +
                        ", todoItemTitle='" + todoItemTitle + '\'' +
                        ", description='" + description + '\'' +
                        ", TodoitemDeadline=" + TodoitemDeadline +
                        ", isDoneStatus=" + isDoneStatus +
                        ", AssigneeId=" + AssigneeId +
                        '}';
        }
}
