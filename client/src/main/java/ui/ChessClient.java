package ui;

import java.util.Scanner;

public class ChessClient {
    private static final ServerFacade serverFacade = new ServerFacade("http://localhost:8080");
    private static String authToken = null;
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
                System.out.println("Invalid choice. Please try again.");
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
                System.out.println("Invalid choice. Please try again.");
                postloginUI();
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
        authToken = serverFacade.login(username, password);
        postloginUI();
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
        authToken = serverFacade.register(username, password, email);
        postloginUI();
    }
    private static void quit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
    private static void preloginHelp() {
        System.out.println("Welcome to Chess! Here is what you can do:");
        System.out.println("Login: Log in to your account");
        System.out.println("Register: Create a new account");
        System.out.println("Quit: Exit the game");
        System.out.println("Help: Display this message");
        preloginUI();
    }
    private static void postloginHelp() {
        System.out.println("Here is what you can do:");
        System.out.println("Create Game: Create a new game");
        System.out.println("List Games: List all available games");
        System.out.println("Join Game: Join an existing game");
        System.out.println("Join Observer: Join an existing game as an observer");
        System.out.println("Logout: Log out of your account");
        System.out.println("Quit: Exit the game");
        System.out.println("Help: Display this message");
        postloginUI();
    }
    private static void logout() {
        System.out.println("You have been logged out.");
        authToken = null;
        preloginUI();
    }
    private static void createGame() {
        System.out.println("Enter the name of the game:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String gameName = scanner.nextLine();
        serverFacade.createGame(gameName);
        postloginUI();
    }
    private static void listGames() {
        System.out.println("Here are the available games:");
        System.out.println(serverFacade.listGames());
        postloginUI();
    }
    private static void joinGame() {
        System.out.println("Enter the game ID:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String gameID = scanner.nextLine();
        System.out.println("Enter White or Black:");
        System.out.print("> ");
        String color = scanner.nextLine();
        postloginUI();
    }
    private static void joinObserver() {
        System.out.println("Enter the game ID:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String gameID = scanner.nextLine();
        postloginUI();
    }
}
