package TENNIS.TENNISINFO.Player.Service;

public interface PlayerService {

    String getPlayerByApi(String rapidPlayerId) throws Exception;
    void savePlayer(String jsonString, String rapidPlayerId) throws Exception;

    void saveCareer(String jsonString, String rapidPlayerId) throws Exception;
}
