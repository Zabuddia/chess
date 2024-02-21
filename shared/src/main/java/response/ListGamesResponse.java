package response;

import model.GameData;

import java.util.ArrayList;
import java.util.Collection;

public record ListGamesResponse(String message, ArrayList<GameData> games) {}