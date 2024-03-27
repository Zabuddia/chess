package webSocketMessages.userCommands;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserGameCommandDeserializer implements JsonDeserializer<UserGameCommand> {
    @Override
    public UserGameCommand deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String typeString = jsonObject.get("commandType").getAsString();
        UserGameCommand.CommandType commandType = UserGameCommand.CommandType.valueOf(typeString);
        return switch (commandType) {
            case JOIN_PLAYER -> context.deserialize(json, JoinPlayerCommand.class);
            case MAKE_MOVE -> context.deserialize(json, MakeMoveCommand.class);
            case JOIN_OBSERVER -> context.deserialize(json, JoinObserverCommand.class);
            case RESIGN -> context.deserialize(json, ResignCommand.class);
            case LEAVE -> context.deserialize(json, LeaveCommand.class);
        };
    }
}
