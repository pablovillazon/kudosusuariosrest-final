package be.jkin.kudosusuariosrest.Controllers;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;

import be.jkin.kudosusuariosrest.queue.Publisher;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import be.jkin.kudosusuariosrest.Exception.ResourceNotFoundException;
import be.jkin.kudosusuariosrest.model.User;
import be.jkin.kudosusuariosrest.repository.UserRepository;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    protected Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    Publisher publisher;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        logger.info(String.format("Users.findAll()"));
        return userRepository.findAll();
    }

/*
    @GetMapping("/users/bypages")
    public List<User> getAllUsers(@PageableDefault(value=10, page=0) Pageable pageable)
    {
        logger.info(String.format("Users.findAll()"));

        Page<User> page = userRepository.findAll(pageable);
        //Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nickname"));
        return page.getContent();
        //return userRepository.findAll();
    }
*/
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable(value = "id") ObjectId userId) throws ResourceNotFoundException
    {
        logger.info(String.format("Users.findBy(%s)", userId));
        User user = userRepository.findBy_id(userId);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/ByNickname/{nickname}")
    public ResponseEntity<User> getUserByNickname(
            @PathVariable(value = "nickname") String nickname) throws ResourceNotFoundException
    {
        logger.info(String.format("Users.findByNickname(%s)", nickname));
        User user = userRepository.findByNickname(nickname);
        if(user!=null)
            return ResponseEntity.ok().body(user);
        else
            return ResponseEntity.notFound().build();
    }



    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user)
    {
        logger.info(String.format("User.save(%s)", user));
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
        logger.info(String.format("User.update(%s)", userId));

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
        logger.info(String.format("User.delete(%s)", userId));

        User user = userRepository.findBy_id(userId);

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
