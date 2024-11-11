package TENNIS.TENNISINFO.Rank.Controller;

import TENNIS.TENNISINFO.Rank.Domain.DTO.RankingResponseDTO;
import TENNIS.TENNISINFO.Rank.Service.RankService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/rank")
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService){

        this.rankService = rankService;
    }
    @PostMapping("")
    public ResponseEntity saveRanking(){
        String response = "";
        try{
            String resJson = rankService.getRankingApiData();

            rankService.saveRankingData(resJson);

        }catch(Exception ex){
            System.out.println("ex : " + ex);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getRankingList(){
        List<RankingResponseDTO> rankingList = new ArrayList<>();
        try{
            rankingList = rankService.getRankingList();
        }catch(Exception e){
            System.out.println(e.toString());
        }

        return new ResponseEntity(rankingList,HttpStatus.OK);
    }

    @GetMapping("/test1")
    public String test1(){
        System.out.println("test");
        return "test";
    }
}
