package ui;

import chess.ChessGame;
import chess.ChessPosition;
import model.GameData;
import webSocketMessages.serverMessages.*;

import static ui.EscapeSequences.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessClient implements ServerMessageObserver {
    private static final ServerFacade serverFacade = new ServerFacade(8080);
    private static String authToken = null;
    private static int gameID = -1;
    private static ChessGame.TeamColor teamColor = null;
    private static ArrayList<GameData> games = new ArrayList<>();
    @Override
    public void notify(ServerMessageInterface message) {
        switch (message.getServerMessageType()) {
            case NOTIFICATION -> displayNotification(((NotificationMessage) message).getMessage());
            case ERROR -> displayError(((ErrorMessage) message).getErrorMessage());
            case LOAD_GAME -> loadGame(((LoadGameMessage) message).getGame(), ((LoadGameMessage) message).position);
        }
    }

    public static void main(String[] args) {
        preloginUI();
    }
    private static void preloginUI() {
        System.out.println("Welcome to Chess! Here is what you can do:");
        System.out.println("Login");
        System.out.println("Register");
        System.out.println("Quit");
        System.out.println("Help");
        System.out.println();
        System.out.print("Enter your choice > ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice.toLowerCase()) {
            case "login":
                login();
                break;
            case "register":
                register();
                break;
            case "quit":
                quit();
                break;
            case "help":
                preloginHelp();
                break;
            default:
                System.out.println();
                displayError("Invalid choice. Please try again.");
                System.out.println();
                preloginUI();
        }
    }
    private static void postloginUI() {
        System.out.println("Welcome to Chess! Here is what you can do:");
        System.out.println("Create Game");
        System.out.println("List Games");
        System.out.println("Join Game");
        System.out.println("Join Observer");
        System.out.println("Logout");
        System.out.println("Quit");
        System.out.println("Help");
        System.out.println();
        System.out.print("Enter your choice > ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice.toLowerCase()) {
            case "create game":
                createGame();
                break;
            case "list games":
                listGames();
                break;
            case "join game":
                joinGame();
                break;
            case "join observer":
                joinObserver();
                break;
            case "logout":
                logout();
                break;
            case "quit":
                quit();
                break;
            case "help":
                postloginHelp();
                break;
            default:
                System.out.println();
                displayError("Invalid choice. Please try again.");
                System.out.println();
                postloginUI();
        }
    }
    private static void gameplayUI() {
        System.out.println("Here is what you can do:");
        System.out.println("Make Move");
        System.out.println("Highlight Legal Moves");
        System.out.println("Redraw Chess Board");
        System.out.println("Leave");
        System.out.println("Resign");
        System.out.println("Help");
        System.out.println();
        System.out.print("Enter your choice > ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice.toLowerCase()) {
            case "make move":
                makeMove();
                break;
            case "highlight legal moves":
                highlightLegalMoves();
                break;
            case "redraw chess board":
                redrawChessBoard();
                break;
            case "leave":
                leave();
                break;
            case "resign":
                resign();
                break;
            case "help":
                gameplayHelp();
                break;
            default:
                System.out.println();
                displayError("Invalid choice. Please try again.");
                System.out.println();
                gameplayUI();
        }
    }
    private static void login() {
        System.out.println("Enter your username:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        System.out.print("> ");
        String password = scanner.nextLine();
        String response = serverFacade.login(username, password);
        if (response.equals("Error: unauthorized")) {
            displayError("Invalid username or password. Please try again.");
            preloginUI();
        } else {
            authToken = response;
            postloginUI();
        }
    }
    private static void register() {
        System.out.println("Enter your username:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        System.out.print("> ");
        String password = scanner.nextLine();
        System.out.println("Enter your email:");
        System.out.print("> ");
        String email = scanner.nextLine();
        String response = serverFacade.register(username, password, email);
        if (response.equals("Error: already taken")) {
            displayError("Username already taken. Please try again.");
            preloginUI();
        } else {
            authToken = response;
            postloginUI();
        }
    }
    private static void quit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
    private static void preloginHelp() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.println("Here is what you can do:");
        System.out.println("Login: Log in to your account");
        System.out.println("Register: Create a new account");
        System.out.println("Quit: Exit the game");
        System.out.println("Help: Display this message");
        System.out.println();
        System.out.print(RESET_TEXT_COLOR);
        System.out.print(RESET_BG_COLOR);
        preloginUI();
    }
    private static void postloginHelp() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.println("Here is what you can do:");
        System.out.println("Create Game: Create a new game");
        System.out.println("List Games: List all available games");
        System.out.println("Join Game: Join an existing game");
        System.out.println("Join Observer: Join an existing game as an observer");
        System.out.println("Logout: Log out of your account");
        System.out.println("Quit: Exit the game");
        System.out.println("Help: Display this message");
        System.out.println();
        System.out.print(RESET_TEXT_COLOR);
        System.out.print(RESET_BG_COLOR);
        postloginUI();
    }
    private static void gameplayHelp() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.println("Here is what you can do:");
        System.out.println("Make Move: Make a move in the game");
        System.out.println("Highlight Legal Moves: Highlight all legal moves for a piece");
        System.out.println("Redraw Chess Board: Redraw the chess board");
        System.out.println("Leave: Leave the game");
        System.out.println("Resign: Resign from the game");
        System.out.println("Help: Display this message");
        System.out.println();
        System.out.print(RESET_TEXT_COLOR);
        System.out.print(RESET_BG_COLOR);
        gameplayUI();
    }
    private static void logout() {
        System.out.println("You have been logged out.");
        serverFacade.logout(authToken);
        authToken = null;
        preloginUI();
    }
    private static void createGame() {
        System.out.println("Enter the name of the game:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String gameName = scanner.nextLine();
        serverFacade.createGame(gameName, authToken);
        postloginUI();
    }
    private static void listGames() {
        System.out.println("Here are the available games:");
        games = serverFacade.listGames(authToken);
        int i = 1;
        for (GameData game : games) {
            System.out.print(i + ". ");
            System.out.print("Name: " + game.gameName() + " ");
            System.out.print("WhitePlayer: " + game.whiteUsername() + " ");
            System.out.println("BlackPlayer: " + game.blackUsername());
            i++;
        }
        System.out.println();
        postloginUI();
    }
    private static void joinGame() {
        if (games.isEmpty()) {
            System.out.println("Either create a game or list games first.");
            postloginUI();
        }
        System.out.println("Enter the game number:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int gameNumber = Integer.parseInt(scanner.nextLine());
        gameID = games.get(gameNumber - 1).gameID();
        System.out.println("Enter White or Black:");
        System.out.print("> ");
        String color = scanner.nextLine();
        if (color.equalsIgnoreCase("white")) {
            teamColor = ChessGame.TeamColor.WHITE;
        } else if (color.equalsIgnoreCase("black")) {
            teamColor = ChessGame.TeamColor.BLACK;
        } else {
            teamColor = null;
        }
        serverFacade.joinGame(ChessGame.TeamColor.valueOf(color.toUpperCase()), gameID, authToken);;
        gameplayUI();
    }
    private static void joinObserver() {
        if (games.isEmpty()) {
            System.out.println("Either create a game or list games first.");
            postloginUI();
        }
        System.out.println("Enter the game ID:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int gameNumber = Integer.parseInt(scanner.nextLine());
        gameID = games.get(gameNumber - 1).gameID();
        serverFacade.joinObserver(gameID, authToken);
        gameplayUI();
    }
    private static void makeMove() {
        System.out.println("Enter the move:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine();
        serverFacade.makeMove(gameID, authToken, teamColor, move);
        gameplayUI();
    }
    private static void highlightLegalMoves() {
        System.out.println("Enter the position of the piece:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String position = scanner.nextLine();
        serverFacade.highlightLegalMoves(gameID, authToken, position);
        gameplayUI();
    }
    private static void redrawChessBoard() {
        serverFacade.redrawChessBoard(gameID, authToken);
        gameplayUI();
    }
    private static void leave() {
        System.out.println("You have left the game.");
        serverFacade.leave(gameID, authToken);
        postloginUI();
    }
    private static void resign() {
        System.out.println("You have resigned from the game.");
        serverFacade.resign(gameID, authToken);
        postloginUI();
    }
    private static void displayNotification(String message) {
        System.out.println();
        System.out.println(message);
        System.out.print("Enter your choice > ");
    }
    private static void displayError(String errorMessage) {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_RED);
        System.out.println(errorMessage);
        System.out.print(RESET_TEXT_COLOR);
        System.out.print(RESET_BG_COLOR);
        if (errorMessage.equals("Error: A player has already joined the game with that color")) {
            postloginUI();
        }
    }
    private static void loadGame(ChessGame game, ChessPosition position) {
        System.out.println();
        if (teamColor == null) {
            ChessBoardUI.printBoard(game, ChessGame.TeamColor.WHITE, position);
        } else if (teamColor == ChessGame.TeamColor.WHITE) {
            ChessBoardUI.printBoard(game, ChessGame.TeamColor.WHITE, position);
        } else {
            ChessBoardUI.printBoard(game, ChessGame.TeamColor.BLACK, position);
        }
    }
}