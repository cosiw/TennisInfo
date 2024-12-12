package TENNIS.TENNISINFO.Player.Controller;

import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Service.PlayerService;
import TENNIS.TENNISINFO.Rank.Service.RankService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/player")
public class PlayerController {

    PlayerService playerService;

    RankService rankService;

    @Autowired
    public PlayerController(PlayerService playerService, RankService rankService){
        this.playerService = playerService;
        this.rankService = rankService;
    }
    @PostMapping(value="{rapidPlayerId}")
    public ResponseEntity savePlayer(@PathVariable String rapidPlayerId){
        try{
            String jsonString = playerService.getPlayerByApi(rapidPlayerId);

            Player player =  playerService.savePlayer(jsonString, rapidPlayerId);

            playerService.saveCareer(jsonString, rapidPlayerId, player);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity("", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity saveAllPlayer(){
        try{
            // 랭킹 정보 API 조회
//            String rankStr = rankService.getRankingApiData();
//            // rapidId List화
//            List<String> rapidIdList = playerService.getRapidIdList(rankStr);
//            // 랭킹은 모두 DELETE 후 INSERT
//            rankService.deleteRankingData();
//
//            rapidIdList.stream().forEach(id -> {
//                try{
//                    Thread.sleep(1000);
//                    // Player API 조회
//                    String jsonString = playerService.getPlayerByApi(id);
//
//                    // Player INSERT (DB에 없는 데이터만 INSERT)
//                    Player player = playerService.savePlayer(jsonString, id);
//
//                    // Career INSERT OR UPDATE
//                    playerService.saveCareer(jsonString, id, player);
//
//                }catch(Exception ex){
//                    System.out.println("forEach Catch " + ex.getMessage());
//                }
//            });
//            // RANKING INSERT
//            rankService.saveRankingData(rankStr);


        }catch(Exception ex){

        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
