package be.jkin.kudosusuariosrest.Controllers;

import be.jkin.kudosusuariosrest.model.KudosMessage;
import be.jkin.kudosusuariosrest.model.User;
import be.jkin.kudosusuariosrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


public class KudosMessageController {

    protected Logger logger = Logger.getLogger(KudosMessageController.class.getName());

    @Autowired
    private UserRepository userRepository;

    public KudosMessageController(){}

    public void HandleKudosMessage(KudosMessage kudosMessage)
    {
        logger.info("Kudos Received: " + kudosMessage.toString());

    }

}
