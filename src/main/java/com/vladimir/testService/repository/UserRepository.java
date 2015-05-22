package com.vladimir.testService.repository;

import com.vladimir.testService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {



    /*@Query("SELECT u from user u where u.name = :name")
    User findByName(@Param("name") String name);

    @Query("SELECT u from user u where u.surname = :surname")
    User findBySurname(@Param("surname") String surname);*/

}
