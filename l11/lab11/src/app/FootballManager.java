/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.PlayerController;
import controller.TeamController;
import entity.Player;
import entity.Team;
import static entity.Team_.player;
import java.sql.SQLException;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ada
 */
public class FootballManager {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("FootballPU");

    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command:");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            String[] params = command.trim().split("\\s+");
            switch (params[0]) {
                case "create-team":
                    createTeam(params[1]); //the team name
                    break;
                case "create-player":
                    createPlayer(params[1], params[2]); //the player name and the team name
                    break;
                case "list-players":
                    listPlayers(params[1]); //the team name
                    break;
            }
        }
    }

    private void createTeam(String teamName) throws SQLException {
        
        TeamController teamController = new TeamController((EntityManagerFactory) emf);
        Team team=new Team();
        team=teamController.findByName(teamName);
    }

    private void createPlayer(String playerName, String teamName) throws SQLException {
       
        Team team=new Team();
        PlayerController playerController = new PlayerController((EntityManagerFactory) emf);
        TeamController teamController = new TeamController(emf);
        Player player2;
        player2 = new Player();
        player2=playerController.findByName(playerName);
        team=teamController.findByName(teamName);
        playerController.create(player2);
        teamController.create(team);
    }

    private void listPlayers(String teamName) {

    }

    public static void main(String args[]) throws SQLException {
        new FootballManager().run();
    }
}
