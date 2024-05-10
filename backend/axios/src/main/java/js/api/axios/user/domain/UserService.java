package js.api.axios.user.domain;

import js.api.axios.task.domain.Task;
import js.api.axios.task.domain.TaskService;
import js.api.axios.task.infraestructure.TaskRepository;
import js.api.axios.user.infraestructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public String createUser(User newUser) {
        userRepository.save(newUser);
        return "User created";
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        List<Task> userTasks = taskRepository.findTasksByUserId(id);
        user.setTasks(userTasks);
        return user;
    }

    public String updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "User not found";
        }

        if (updatedUser.getName() != null)
            user.setName(updatedUser.getName());
        if (updatedUser.getEmail() != null)
            user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null)
            user.setPassword(updatedUser.getPassword());
        if (updatedUser.getUrlprofile() != null)
            user.setUrlprofile(updatedUser.getUrlprofile());

        userRepository.save(user);

        return "User updated with id: " + id;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted with id: " + id;
    }
}
