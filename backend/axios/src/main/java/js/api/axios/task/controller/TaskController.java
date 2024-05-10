package js.api.axios.task.controller;

import js.api.axios.task.domain.Task;
import js.api.axios.task.domain.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://127.0.0.1:5500")
public class TaskController {
    @Autowired
    private TaskService taskService;

    // Publish new task
    @PostMapping("/{userId}")
    public ResponseEntity<String> createTask(@PathVariable Long userId, @RequestBody Task newTask) {
        String res = taskService.createTask(userId, newTask);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    // Get all tasks by user id
    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasks(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Update a task by id
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        String res = taskService.updateTask(id, updatedTask);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // Delete a task by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        String res = taskService.deleteTask(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
