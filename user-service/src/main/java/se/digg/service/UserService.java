package se.digg.service;

import io.quarkus.runtime.Startup;
import jakarta.inject.Singleton;
import se.digg.model.User;

import java.util.*;


@Singleton
@Startup
public class UserService {

    private Map<Long, User> users = new HashMap();



    public UserService() {
        generateUsers();
    }
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }


    public Long createUser(User user) {
        if (user == null) {
            return Long.MAX_VALUE;
        }
        user.setId(users.size()+1L);
        users.put(user.getId(), user);
        return user.getId();
    }
    public Long updateUser(User user) {
        if (user == null || user.getId() == null) {
            return Long.MAX_VALUE;
        }
        users.replace(user.getId(), user);

        return user.getId();
    }


    private void generateUsers() {
        for (int i=0; i<100; i++) {
            createUser(new User("Kalle Anka "+ i, "vägen "+ i, "08-12313"+i));
        }
    }

    public Optional<User> getUser(Long id) {
        if (users.containsKey(id)) {
            return Optional.of(users.get(id));
        }
        return Optional.empty();
    }

    public User deleteUser(Long userId) {
        return users.remove(userId);
    }

    public boolean userExists(Long userId) {
        return users.containsKey(userId);
    }
}
