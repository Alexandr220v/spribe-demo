package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.stream.Stream;

public class GetPlayerRequestDTO {
    @JsonProperty("playerId")
    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPlayerRequestDTO that = (GetPlayerRequestDTO) o;
        return playerId == that.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

    @Override
    public String toString() {
        return "GetPlayerRequestDTO{" +
                "playerId=" + playerId +
                '}';
    }
}
