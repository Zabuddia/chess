package webSocketMessages.serverMessages;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ServerMessageDeserializer implements JsonDeserializer<ServerMessage> {
    @Override
    public ServerMessage deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String typeString = jsonObject.get("serverMessageType").getAsString();
        ServerMessage.ServerMessageType messageType = ServerMessage.ServerMessageType.valueOf(typeString);
        return switch (messageType) {
            case NOTIFICATION -> context.deserialize(json, NotificationMessage.class);
            case ERROR -> context.deserialize(json, ErrorMessage.class);
            case LOAD_GAME -> context.deserialize(json, LoadGameMessage.class);
        };
    }
}
