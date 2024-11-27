package TENNIS.TENNISINFO.Rank.Controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService){

        this.rankService = rankService;
    }
    @PostMapping("/save")
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

    @GetMapping("/rankList")
    public ResponseEntity getRankingList(){
        List<RankingResponseDTO> rankingList = new ArrayList<>();
        try{
            rankingList = rankService.getRankingList();
        }catch(Exception e){
            System.out.println(e.toString());
        }

        return new ResponseEntity(rankingList,HttpStatus.OK);
    }

    @GetMapping("/")
    public String getTopRanking(Model model){
        List<TopRankingResponseDTO> topRankingList = new ArrayList<>();
        try{
            topRankingList = rankService.getTopRankingList();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        model.addAttribute("players", topRankingList);
        return "index";
    }

    @GetMapping("/player/{id}")
    public String test1(){
        System.out.println("test");
        return "player";
    }
}
