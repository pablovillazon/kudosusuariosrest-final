package be.jkin.kudosusuariosrest.repository;


import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import be.jkin.kudosusuariosrest.model.User;

import java.awt.print.Pageable;
import java.util.List;


public interface UserRepository extends MongoRepository<User, Long>{
    User findBy_id(ObjectId _id);
    User findByNickname(String nickname);
    //Page<User> findAll(Pageable pageRequest);
}
