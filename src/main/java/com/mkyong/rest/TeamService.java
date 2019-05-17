package com.mkyong.rest;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.o7planning.restfulcrud.dao.TeamDao;
import org.o7planning.restfulcrud.model.Team;

/**
 *
 * @author mateo
 */
@Path("/teams")
public class TeamService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Team> getTeamsJSON() {
        List<Team> listOfTeams = TeamDao.getAllTeams();
        return listOfTeams;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Team addTeam(Team team) {
        return TeamDao.addTeam(team);
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Team updateTeam(Team team) {
        return TeamDao.updateTeam(team);
    }

    @DELETE
    @Path("/{teamId}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteTeam(@PathParam("teamId") String empNo) {
        if(TeamDao.deleteTeam(empNo)){
            return "Success deleted";
        }else{
            return "Not deleted";
        }            
    }

}
