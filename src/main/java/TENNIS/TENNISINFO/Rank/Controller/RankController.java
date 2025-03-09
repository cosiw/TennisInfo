package TENNIS.TENNISINFO.Rank.Controller;

import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.rapid.PlayerApiClient;
import TENNIS.TENNISINFO.Common.rapid.RankingApiClient;
import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.DTO.TopRankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import TENNIS.TENNISINFO.Rank.Service.RankService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/rank")
public class RankController {

    private final RankService rankService;
    private final RankingApiClient rankingApi;
    private final PlayerApiClient playerApi;

    @Autowired
    public RankController(RankService rankService, RankingApiClient rankingApi, PlayerApiClient playerApi){
        this.rankService = rankService;
        this.rankingApi = rankingApi;
        this.playerApi = playerApi;
    }

    @GetMapping("/player/{rapidPlayerId}/image")
    public void getTopRanking(@PathVariable String rapidPlayerId){
//        try{
//            playerApi.teamImage(rapidPlayerId);
//        }catch(Exception e){
//            System.out.println(e.toString());
//        }

    }

    @GetMapping("/player/{rapidPlayerId}")
    public ResponseEntity test1(@PathVariable String rapidPlayerId){
        try{
            playerApi.teamDetails(rapidPlayerId);
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return new ResponseEntity("",HttpStatus.OK);
    }


    @GetMapping("/rankList")
    public ResponseEntity getRankingList(){

        try{
            var rankList = rankService.getRankingList();

            return new ResponseEntity(rankList,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.toString());
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
