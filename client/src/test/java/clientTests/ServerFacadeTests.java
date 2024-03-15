package clientTests;

import chess.ChessGame;
import dataAccess.SQLDAO;
import dataAccess.SQLGameDAO;
import org.junit.jupiter.api.*;
import server.Server;
import ui.ServerFacade;


public class ServerFacadeTests {
    private static Server server;
    static ServerFacade facade;
    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        facade = new ServerFacade(port);
    }
    @BeforeEach
    public void clear() {
        facade.clear();
    }
    @AfterAll
    static void stopServer() {
        server.stop();
    }
    @Test
    public void registerPositive() {
        String authToken = facade.register("testUser", "testPassword", "testEmail");
        Assertions.assertTrue(authToken.length() > 10);
    }
    @Test
    public void registerNegative() {
        facade.register("testUser", "testPassword", "testEmail");
        String authToken = facade.register("testUser", "testPassword", "testEmail");
        Assertions.assertNull(authToken);
    }
    @Test
    public void loginPositive() {
        facade.register("testUser", "testPassword", "testEmail");
        String authToken = facade.login("testUser", "testPassword");
        Assertions.assertTrue(authToken.length() > 10);
    }
    @Test
    public void loginNegative() {
        String authToken = facade.login("testUser", "testPassword");
        Assertions.assertNull(authToken);
    }
    @Test
    public void createGamePositive() {
        facade.register("testUser", "testPassword", "testEmail");
        String authToken = facade.login("testUser", "testPassword");
        facade.createGame("testGame", authToken);
        Assertions.assertFalse(SQLDAO.isEmpty("game"));
    }
    @Test
    public void createGameNegative() {
        facade.createGame("testGame", "Fake auth token");
        Assertions.assertTrue(SQLDAO.isEmpty("game"));
    }
    @Test
    public void joinGamePositive() {
        SQLGameDAO gameDAO = new SQLGameDAO();
        facade.register("testUser", "testPassword", "testEmail");
        String authToken = facade.login("testUser", "testPassword");
        int gameID = facade.createGame("testGame", authToken);
        facade.joinGame(ChessGame.TeamColor.WHITE, gameID, authToken);
        Assertions.assertEquals("testUser", gameDAO.getGameData(gameID).whiteUsername());
    }
    @Test
    public void joinGameNegative() {
        SQLGameDAO gameDAO = new SQLGameDAO();
        String authToken = facade.register("testUser", "testPassword", "testEmail");
        int gameID = facade.createGame("testGame", authToken);
        facade.joinGame(ChessGame.TeamColor.WHITE, gameID, "Fake auth token");
        Assertions.assertNotEquals("testUser", gameDAO.getGameData(gameID).whiteUsername());
    }
    @Test
    public void logoutPositive() {
        String authToken = facade.register("testUser", "testPassword", "testEmail");
        facade.logout(authToken);
        Assertions.assertTrue(SQLDAO.isEmpty("auth"));
    }
    @Test
    public void logoutNegative() {
        facade.register("testUser", "testPassword", "testEmail");
        facade.logout("Fake auth token");
        Assertions.assertFalse(SQLDAO.isEmpty("auth"));
    }
    @Test
    public void listGamesPositive() {
        String authToken = facade.register("testUser", "testPassword", "testEmail");
        facade.createGame("testGame", authToken);
        Assertions.assertFalse(facade.listGames(authToken).isEmpty());
    }
    @Test
    public void listGamesNegative() {
        String authToken = facade.register("testUser", "testPassword", "testEmail");
        facade.createGame("testGame", authToken);
        Assertions.assertNull(facade.listGames("Fake auth token"));
    }
}