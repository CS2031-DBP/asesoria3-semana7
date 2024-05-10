package js.api.axios.task.domain;

import js.api.axios.task.infraestructure.TaskRepository;
import js.api.axios.user.domain.User;
import js.api.axios.user.infraestructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public String createTask(Long userId, Task task) {
        LocalDateTime now = LocalDateTime.now();
        task.setCreatedAt(now);

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found";
        }

        task.setUser(user);
        taskRepository.save(task);
        return "Task created";
    }

    public List<Task> getTasks(Long userId) {
        return taskRepository.findTasksByUserId(userId);
    }

    public String updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "Task not found";
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDeadline(updatedTask.getDeadline());
        task.setUser(updatedTask.getUser());
        taskRepository.save(task);
        return "Task " + id + " updated";
    }

    public String deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "Task not found";
        }

        taskRepository.delete(task);
        return "Task " + id + " deleted";
    }
}
