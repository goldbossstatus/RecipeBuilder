package com.recipe.userservices;

import com.recipe.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);

    // @EntityGraph(attributePaths = "authorities")
    //Optional<User> findUserByEmail(String email);

}