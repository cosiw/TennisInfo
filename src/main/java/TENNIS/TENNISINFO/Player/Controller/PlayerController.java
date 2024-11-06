package TENNIS.TENNISINFO.Player.Controller;

import TENNIS.TENNISINFO.Player.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/player")
public class PlayerController {

    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
    @GetMapping(value="{rapidPlayerId}")
    public ResponseEntity savePlayer(@PathVariable String rapidPlayerId){
        try{
            String jsonString = playerService.getPlayerByApi(rapidPlayerId);

            playerService.savePlayer(jsonString);

            playerService.saveCareer(jsonString);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity("", HttpStatus.OK);
    }
}
