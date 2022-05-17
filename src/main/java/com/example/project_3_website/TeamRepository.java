package com.example.project_3_website;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
    Team findByTeamId(@NonNull int teamId);

    @Query(value = "SELECT * FROM Team t WHERE t.user_id like %:user_id%",
            countQuery = "Select count(*) from Team", nativeQuery = true)
    Iterable<Team> findTeamByUserId(@Param("user_id") Integer user_id);

    @Query(value = "SELECT * FROM team INNER JOIN user ON team.user_id = user.user_id WHERE user.username LIKE %:username%",
            countQuery = "Select count(*) from Team", nativeQuery = true)
    Iterable<Team> findTeamByUsername(@Param("username") String username);
}

//SELECT * FROM heroes INNER JOIN team ON heroes.team_id = team.team_id WHERE user_id = 2

//SELECT * FROM team INNER JOIN user ON team.user_id = user.user_id WHERE user.username = "nacho"