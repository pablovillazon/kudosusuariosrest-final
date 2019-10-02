package be.jkin.kudosusuariosrest.queue;

import be.jkin.kudosusuariosrest.Controllers.KudosMessageController;
import be.jkin.kudosusuariosrest.Controllers.UserController;
import be.jkin.kudosusuariosrest.model.KudosMessage;
import be.jkin.kudosusuariosrest.model.User;
import be.jkin.kudosusuariosrest.repository.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Subscriber {

    @Autowired
    private UserRepository userRepository;

    protected java.util.logging.Logger logger = Logger.getLogger(Subscriber.class.getName());


    @RabbitListener(queues = "kudos-queue-k2u")
    public void onMessageFromRabbitMQ(final String messageFromRabbitMQ)
    {
        logger.info(String.format("Message from Queue: {%s}", messageFromRabbitMQ));

        //Handle the kudos message received from the queue
        JsonObject jsonObject = new JsonParser().parse(messageFromRabbitMQ).getAsJsonObject();
        KudosMessage message = new KudosMessage(jsonObject);


        KudosMessageController kudosController = new KudosMessageController();
        kudosController.HandleKudosMessage(message);

        User user = userRepository.findByNickname(message.getDestino());

        if(user != null)
        {
            Long kudosQty = user.getTotalKudos();
            logger.info("#Kudos Usuario previous: " + user.getTotalKudos() + " User:" + user);

            if(message.getAccion().equalsIgnoreCase("ADD_KUDOS"))
            {
                user.setTotalKudos(kudosQty + 1);
            }
            else //DELETE_KUDOS
            {
                if(kudosQty > 0)
                    user.setTotalKudos(kudosQty - 1);
            }

            userRepository.save(user);

            logger.info("#Kudos Usuario Updated to: " + user.getTotalKudos());

        }

    }
}
