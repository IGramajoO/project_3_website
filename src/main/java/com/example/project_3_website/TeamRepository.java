package com.example.project_3_website;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    Team findTeamById(@Param("teamId") Integer teamId);
}
