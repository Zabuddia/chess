package clientTests;

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
//        facade.clear();
    }
    @AfterAll
    static void stopServer() {
        server.stop();
    }
    @Test
    public void register() {
        String authToken = facade.register("testUser", "testPassword", "testEmail");
        Assertions.assertTrue(authToken.length() > 10);
    }

}