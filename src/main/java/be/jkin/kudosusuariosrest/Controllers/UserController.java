package be.jkin.kudosusuariosrest.Controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.jkin.kudosusuariosrest.Exception.ResourceNotFoundException;
import be.jkin.kudosusuariosrest.model.User;
import be.jkin.kudosusuariosrest.repository.UserRepository;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable(value = "id") ObjectId userId) throws ResourceNotFoundException
    {
        User user = userRepository.findBy_id(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user)
    {
        /*
        User existingUser = userRepository.findByNickname(user.getNickname());
        if(existingUser != null)
        {
        }
        */

        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") ObjectId userId,
            @Valid @RequestBody User userDetails) throws ResourceNotFoundException
    {
        User user = userRepository.findBy_id(userId);

        user.setNickname(userDetails.getNickname());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());

        //TotalKudos will be increased only if the incoming value is greater than zero, otherwise no change
        if(userDetails.getTotalKudos()>0)
            user.increaseKudos();

        user.setUpdatedAt(new Date());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") ObjectId userId) throws Exception
    {
        User user = userRepository.findBy_id(userId);

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
