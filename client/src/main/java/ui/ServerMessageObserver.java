package ui;

import webSocketMessages.serverMessages.ServerMessageInterface;

public interface ServerMessageObserver {
    void notify(ServerMessageInterface message);
}
