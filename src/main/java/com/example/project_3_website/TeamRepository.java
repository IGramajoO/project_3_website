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
}

//SELECT * FROM heroes INNER JOIN team ON heroes.team_id = team.team_id WHERE user_id = 2
