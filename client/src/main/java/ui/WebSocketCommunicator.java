package ui;

import com.google.gson.Gson;
import webSocketMessages.serverMessages.ErrorMessage;
import webSocketMessages.serverMessages.ServerMessage;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

public class WebSocketCommunicator extends Endpoint {
    private Session session;
    private final Gson gson = new Gson();
    private final String webSocketUrl;
    private ServerMessageObserver observer;
    public WebSocketCommunicator(String url, ServerMessageObserver observer) throws DeploymentException, IOException {
        webSocketUrl = url;
        this.observer = observer;

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        this.session = container.connectToServer(this, URI.create(webSocketUrl));

        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                WebSocketCommunicator.this.onMessage(message);
            }
        });
    }
    public void onMessage(String message) {
        try {
            ServerMessage serverMessage = gson.fromJson(message, ServerMessage.class);
            observer.notify(serverMessage);
        } catch(Exception ex) {
            observer.notify(new ErrorMessage(ex.getMessage()));
        }
    }
    @Override
    public void onOpen(Session session, EndpointConfig config) {
    }
}
