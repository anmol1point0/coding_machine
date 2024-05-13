import dao.GameDao;
import dao.PlayerDao;
import javafx.util.Pair;
import model.Player;
import service.GameService;
import service.PlayerService;
import utils.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DriverClass {

    public static void main(String[] args){
        GameDao gameDao = new GameDao();
        PlayerDao playerDao = new PlayerDao();
        GameUtils gameUtils = new GameUtils();
        GameService gameService = new GameService(gameDao, playerDao, gameUtils);
        PlayerService playerService = new PlayerService(playerDao);
        boolean boardLive = true;
        while(true) {
            System.out.println("Select Options from the Menu: \n" +
                    "1. REGISTER_BOARD \n" +
                    "2. REGISTER_ELEMENTS \n" +
                    "3. REGISTER_USER \n" +
                    "4. START \n");

            Scanner scan = new Scanner(System.in);

            switch (scan.next()) {
                case "REGISTER_BOARD":
                    System.out.println("Input size of board!");
                    scan = new Scanner(System.in);
                    int boardSize = scan.nextInt();
                    gameService.registerBoard(boardSize);
                    break;
                case "REGISTER_ELEMENTS":
                    System.out.println("Snakes Or Ladders?");
                    scan = new Scanner(System.in);
                    String input = scan.nextLine();
                    switch (input) {
                        case "Snakes":
                            System.out.println("Register number of Snakes");
                            scan = new Scanner(System.in);
                            int numberOfSnakes = scan.nextInt();
                            List<Pair<Integer, Integer>> snakePositionRequests = new ArrayList<>();
                            while (numberOfSnakes > 0) {
                                System.out.println("Provide Head and Tail in the order");
                                int head = scan.nextInt();
                                int tail = scan.nextInt();
                                snakePositionRequests.add(new Pair<>(head, tail));
                                numberOfSnakes--;
                            }
                            Boolean isSnakeRegistered = gameService.registerSnakes(snakePositionRequests);
                            if (isSnakeRegistered.equals(Boolean.TRUE))
                                System.out.println("Number of Snakes Registered Successfully");
                            break;
                        case "Ladders":
                            System.out.println("Register number of Ladders");
                            scan = new Scanner(System.in);
                            int numberOfLadders = scan.nextInt();
                            List<Pair<Integer, Integer>> ladderPositionRequests = new ArrayList<>();
                            while (numberOfLadders > 0) {
                                System.out.println("Provide Head and Tail in the order");
                                scan = new Scanner(System.in);
                                int head = scan.nextInt();
                                int tail = scan.nextInt();
                                ladderPositionRequests.add(new Pair<>(head, tail));
                                numberOfLadders--;
                            }
                            Boolean isLadderRegistered = gameService.registerLadders(ladderPositionRequests);
                            if (isLadderRegistered.equals(Boolean.TRUE))
                                System.out.println("Number of Ladders Registered");
                            break;
                        case "Default":
                            break;
                    }
                    break;
                case "REGISTER_USER":
                    System.out.println("Number of Players?");
                    scan = new Scanner(System.in);
                    int numberOfPlayers = scan.nextInt();
                    while (numberOfPlayers > 0) {
                        System.out.println("Name of the player");
                        scan = new Scanner(System.in);
                        String playerName = scan.nextLine();
                        Player registeredPlayer = playerService.registerPlayer(playerName);
                        if (Objects.isNull(registeredPlayer)) {
                            numberOfPlayers++;
                        }
                        numberOfPlayers--;
                    }
                    break;
                case "START":
                    List<String> playerNamesInOrder = gameService.startGame();
                    if (Objects.nonNull(playerNamesInOrder)) {
                        System.out.println("Game started, All Players at 0, Best Of Luck!");
                        System.out.println("Game will go in order of");
                        for (int i = 0; i < playerNamesInOrder.size(); i++) {
                            System.out.println(playerNamesInOrder.get(i));
                        }
                        int playerOrder = 0;
                        while (true) {
                            System.out.println("type any key to Roll the Dice for: " + playerNamesInOrder.get(playerOrder));

                            scan = new Scanner(System.in);
                            scan.next();

                            gameService.diceRolled(playerNamesInOrder.get(playerOrder));
                            if (!gameService.isGameLive()) {
                                boardLive = false;
                                break;
                            }
                            playerOrder++;
                            playerOrder %= playerNamesInOrder.size();
                        }
                    }
                    break;
                case "Default":
                    break;
            }
            if(!boardLive){
                break;
            }
        }
    }
}
