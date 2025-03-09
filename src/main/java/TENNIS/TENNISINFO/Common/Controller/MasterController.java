package TENNIS.TENNISINFO.Common.Controller;

import TENNIS.TENNISINFO.Common.Service.MasterService;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.rapid.PlayerApiClient;
import TENNIS.TENNISINFO.Common.rapid.RankingApiClient;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/master")
public class MasterController {

    private final MasterService masterService;


    public MasterController(MasterService masterService){
        this.masterService = masterService;
    }

    @PostMapping("/ranking")
    public ResponseEntity savePlayerAndRanking(){

        try{
            masterService.savePlayerAndRanking();

        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity saveCategory(){
        try{
            masterService.saveCategory();
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }
        return new ResponseEntity("", HttpStatus.OK);
    }

    @PostMapping("/tournament")
    public ResponseEntity saveTournament(){
        try{
            masterService.saveTournament();
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @PostMapping("/season")
    public ResponseEntity saveSeason(){
        try{
            masterService.saveSeason();
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @PostMapping("/round")
    public ResponseEntity saveRound(){
        try{
            masterService.saveRound();
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }

        return new ResponseEntity("", HttpStatus.OK);
    }
    @PostMapping("/match")
    public ResponseEntity saveMatch(){
        try{
            masterService.saveMatch();
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }

        return new ResponseEntity("", HttpStatus.OK);
    }

}
