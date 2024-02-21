package response;

import model.GameData;

import java.util.Collection;

public record ListGamesResponse(String message, String error, Collection<GameData> listOfGames) {}