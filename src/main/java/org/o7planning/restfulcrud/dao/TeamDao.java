package org.o7planning.restfulcrud.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.o7planning.restfulcrud.model.Employee;
import org.o7planning.restfulcrud.model.Team;

/**
 *
 * @author mateo
 */
public class TeamDao {

    private static final Map<String, Team> teamMap = new HashMap<String, Team>();

    static {
        initTeam();
    }

    private static void initTeam() {
        Team team1 = new Team("00", "Avengers");

        teamMap.put(team1.getId(), team1);

    }

    public static Team addTeam(Team team) {
        teamMap.put(team.getId(), team);
        return team;
    }

    public static List<Team> getAllTeams() {
        Collection<Team> c = teamMap.values();
        List<Team> list = new ArrayList<Team>();
        list.addAll(c);
        return list;
    }

    public static Team updateTeam(Team myTeam) {
        teamMap.put(myTeam.getId(), myTeam);
        return myTeam;
    }

    public static Boolean deleteTeam(String teamId) {
        teamMap.remove(teamId);
        return true;
    }

}
