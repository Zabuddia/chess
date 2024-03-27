package webSocketMessages.serverMessages;

public interface ServerMessageInterface {
    public ServerMessage.ServerMessageType getServerMessageType();
    public String getMessage();
}
