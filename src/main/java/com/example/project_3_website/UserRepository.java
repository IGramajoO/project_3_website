package com.example.project_3_website;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {



    @Query(value = "SELECT * FROM User u WHERE u.userId like %:userId%",
            countQuery = "Select count(*) from User", nativeQuery = true)
    User findUserById(@Param("userId") Integer userId);

    User findUserByUsername(String username);
}
