package TENNIS.TENNISINFO.Common.rapid;

import TENNIS.TENNISINFO.Common.Enum.ImageDir;
import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerApiClient  extends AbstractApiClient<PlayerRapidDTO>{

    public PlayerApiClient(ObjectMapper objectMapper){
        super(objectMapper);
    }

    @Override
    protected PlayerRapidDTO handleResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (PlayerRapidDTO) method.invoke(this, response);
    }

    @Override
    protected List<PlayerRapidDTO> handleListResponse(String response, String methodName) throws Exception {
        Method method = this.getClass().getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return (List<PlayerRapidDTO>) method.invoke(this, response);
    }

    public String imageApi(String param, String fileName) throws Exception{
        String outputPath = "";

        try{
            String uri = "https://tennisapi1.p.rapidapi.com/api/";
            // HttpClient 생성
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri + param))
                    .header("x-rapidapi-key", rapidApiKey)
                    .header("x-rapidapi-host", "tennisapi1.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            System.out.println(response.body());
            InputStream inputStream = response.body();
            byte[] imageData = inputStream.readAllBytes();

            ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
            BufferedImage originalImage = ImageIO.read(bais);

            if(originalImage == null){
                throw new IOException("이미지 데이터를 읽을 수 없습니다.");
            }
            outputPath = ImageDir.PLAYER.getDir() + fileName + ".png";
            boolean saveImage = ImageIO.write(originalImage, "png", new File(imageDir + outputPath));

            if(saveImage){
                System.out.println("이미지 저장 완료! 경로 : " + outputPath);
            }else{
                System.out.println("이미지 저장 실패!");
            }

            bais.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return outputPath;
    }

    public String imageApiCall(String url, String fileName){
        String apiResponse = "";
        try{
            apiResponse = imageApi(url, fileName);
            if("".equals(apiResponse)) return null;

        }catch(Exception e){
            e.printStackTrace();
        }

        return apiResponse;
    }


    public PlayerRapidDTO teamDetails(String response) throws Exception {
        PlayerRapidDTO team = new PlayerRapidDTO();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode teamNode = rootNode.path("team");
        JsonNode playerNode = teamNode.path("playerTeamInfo");
        team = objectMapper.treeToValue(playerNode, PlayerRapidDTO.class);
        team.setBirthDate(transTimeStamp(team.getBirthDate()));
        // 이름
        JsonNode name= teamNode.path("fullName");
        team.setPlayerName(name.asText());
        team.setPlayerRapidId(teamNode.path("id").toString());
        // 상금
        JsonNode prizeNode = playerNode.path("prizeTotalRaw");
        JsonNode curNode = prizeNode.path("currency");

        String cur = curNode.asText();

        if(cur.equals("EUR")){
            Long prizeCurrent = team.getPrizeCurrent() != null? team.getPrizeCurrent() : 0L;
            Long prizeTotal = team.getPrizeTotal() != null? team.getPrizeTotal() : 0L;

            // USD로 저장
            team.setPrizeCurrent(eurToUsd(prizeCurrent));
            team.setPrizeTotal(eurToUsd(prizeTotal));
        }

        return team;


    }

    //
    public static String getDesktopPath() {
        return Paths.get(System.getProperty("user.home"), "Desktop").toString();
    }

}