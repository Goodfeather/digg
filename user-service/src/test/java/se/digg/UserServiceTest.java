package se.digg;

import se.digg.model.User;
import se.digg.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    private UserService userService;
    private User pellePirat;

    @BeforeEach
    public void each() {
        userService = new UserService();
        pellePirat = new User("Pelle Pirat", "Tyskland", "09-123412");
    }


    @Test
    public void createNullUserTest() {
        assertEquals(Long.MAX_VALUE, userService.updateUser(null));
    }

    @Test
    public void createUserTest() {


        int numberOfUsers = userService.getUsers().size();



        Long id = userService.createUser(pellePirat);

        assertEquals(numberOfUsers+1, id);

        assertEquals(numberOfUsers+1, userService.getUsers().size());
        Optional<User> addedUser = userService.getUser(id);
        assertTrue(addedUser.isPresent());
        assertEquals(pellePirat.getName(), addedUser.get().getName());
        assertEquals(pellePirat.getAddress(), addedUser.get().getAddress());
        assertEquals(pellePirat.getTelephone(), addedUser.get().getTelephone());
    }



    @Test
    public void updateNullUserTest() {
        assertEquals(Long.MAX_VALUE, userService.updateUser(null));
    }
    @Test
    public void updateNullUserIdTest() {
        User user = new User();
        user.setId(null);
        assertEquals(Long.MAX_VALUE, userService.updateUser(user));
    }
    @Test
    public void updateUserTest() {

        pellePirat.setId(1L);


        Optional<User> currentUser = userService.getUser(1L);
        assertTrue(currentUser.isPresent());
        assertNotEquals(pellePirat.getName(), currentUser.get().getName());
        assertNotEquals(pellePirat.getAddress(), currentUser.get().getAddress());
        assertNotEquals(pellePirat.getTelephone(), currentUser.get().getTelephone());



        Long id = userService.updateUser(pellePirat);
        assertEquals(1L, id);

        currentUser = userService.getUser(1L);
        assertTrue(currentUser.isPresent());
        assertEquals(pellePirat.getName(), currentUser.get().getName());
        assertEquals(pellePirat.getAddress(), currentUser.get().getAddress());
        assertEquals(pellePirat.getTelephone(), currentUser.get().getTelephone());

    }

    @Test
    public void deleteUserTest() {

        List<User> users = userService.getUsers();
        User first = users.get(0);

        userService.deleteUser(first.getId());

        List<User> usersAfterRemove = userService.getUsers();

        assertEquals(users.size() - 1, usersAfterRemove.size());
        assertFalse(userService.getUser(first.getId()).isPresent());

    }
}