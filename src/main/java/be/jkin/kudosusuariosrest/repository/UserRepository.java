package be.jkin.kudosusuariosrest.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import be.jkin.kudosusuariosrest.model.User;


public interface UserRepository extends MongoRepository<User, Long>{
    User findBy_id(ObjectId _id);
    User findByNickname(String nickname);
}
