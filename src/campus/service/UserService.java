package campus.service;

import campus.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully.");
    }

    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {
            user.displayUser();
            System.out.println("-------------------------");
        }
    }
}
