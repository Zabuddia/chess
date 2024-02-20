package response;

import model.GameData;

import java.util.Collection;

public record ListGamesResponse(int responseNum, String message, String error, Collection<GameData> listOfGames) {}