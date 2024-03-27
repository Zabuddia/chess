package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import webSocketMessages.serverMessages.ErrorMessage;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.serverMessages.ServerMessageDeserializer;
import webSocketMessages.serverMessages.ServerMessageInterface;
import webSocketMessages.userCommands.GameCommand;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketCommunicator extends Endpoint {
    private Session session;
    private ServerMessageObserver observer = new ChessClient();
    private final Gson gson = new Gson();
    public WebSocketCommunicator() throws DeploymentException, IOException, URISyntaxException {
        URI uri = new URI("ws://localhost:8080/connect");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        this.session = container.connectToServer(this, uri);

        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
                Gson gsonBuilder = builder.create();

                ServerMessageInterface serverMessage = gsonBuilder.fromJson(message, ServerMessage.class);

                System.out.println(serverMessage.getMessage());
            }
        });
    }
    public void send(GameCommand command) throws IOException {
        String msg = gson.toJson(command);
        this.session.getBasicRemote().sendText(msg);
    }
    @Override
    public void onOpen(Session session, EndpointConfig config) {
    }
}
