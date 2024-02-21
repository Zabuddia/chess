package response;

import model.GameData;

import java.util.Collection;

public record ListGamesResponse(String message, Collection<GameData> listOfGames) {}