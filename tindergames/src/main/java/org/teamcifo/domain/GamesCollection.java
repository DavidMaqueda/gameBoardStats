package org.teamcifo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.teamcifo.utils.Helpers;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
public class GamesCollection {
    private String collectionId;
    private Set<String> gamesCollection;

    public GamesCollection() {
        // The collection ID is generated on creation time
        this.collectionId = Helpers.generateUUID();
        this.gamesCollection = new HashSet<>();
    }

    // Public methods
    public void addGame(BoardGame boardGame) {
        // Use the boardGame ID as the hashmap key
        this.gamesCollection.add(boardGame.getGameID());
    }

    public void deleteGame(BoardGame boardGame) {
        // Only try to remove the BoardGame if its gameId exists
        if (this.gamesCollection.contains(boardGame.getGameID())) {
            this.gamesCollection.remove(boardGame.getGameID());
        } else {
            System.out.println("Game " + boardGame.getGameTitle() + " is not included in the collection, can't remove it!");
        }
    }

    public boolean hasGame(BoardGame boardGame) {
        // Check that the collection has an entry with the same game ID
        return this.gamesCollection.contains(boardGame.getGameID());
    }

    public void copyFrom(Set<String> gamesCollection) {
        this.gamesCollection.addAll(gamesCollection);
    }

    public int size() {
        return this.gamesCollection.size();
    }

    // Methods override
    @Override
    public int hashCode() {
        // As collectionID should already be unique for each collection, we can use it to compute the hashcode
        return this.collectionId.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamesCollection that = (GamesCollection) o;
        return getGamesCollection().equals(that.getGamesCollection());
    }
}
