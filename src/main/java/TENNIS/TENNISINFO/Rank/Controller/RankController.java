package TENNIS.TENNISINFO.Rank.Controller;

import TENNIS.TENNISINFO.Rank.Service.RankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/rank")
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService){

        this.rankService = rankService;
    }
    @GetMapping("")
    public ResponseEntity getRanking(){
        String response = "";
        try{
            String resJson = rankService.getRankingApiData();

            rankService.saveRankingData(resJson);

        }catch(Exception ex){
            System.out.println("ex : " + ex);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("test");
        return "main";
    }

    @GetMapping("/test1")
    public String test1(){
        System.out.println("test");
        return "test";
    }
}
