package se.digg;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import se.digg.model.User;
import se.digg.service.UserService;

import java.util.List;
import java.util.Optional;

@Path("/digg/user")

public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Long createUser(User user) {
        return userService.createUser(user);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> readUsers() {
        return userService.getUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}/")
    public Optional<User> readUser(@PathParam("userId") Long userId) {
        if (userId == null) {
            return Optional.empty();
        }
        return userService.getUser(userId);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Long updateUser(User user) {
        if (user == null || user.getId() == null || !userService.userExists(user.getId())) {
            return 0L;
        }
        return userService.updateUser(user);
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}/")
    public User deleteUser(@PathParam("userId") Long userId) {
        if (userId == null || !userService.userExists(userId)) {
            return null;
        }
        return userService.deleteUser(userId);
    }
}
