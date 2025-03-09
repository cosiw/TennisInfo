package TENNIS.TENNISINFO.Common.Service;

import TENNIS.TENNISINFO.Category.Domain.Category;
import TENNIS.TENNISINFO.Category.Repository.CategoryRepository;
import TENNIS.TENNISINFO.Common.Enum.RapidApi;
import TENNIS.TENNISINFO.Common.config.RapidApiConfig;
import TENNIS.TENNISINFO.Common.domain.*;
import TENNIS.TENNISINFO.Common.rapid.*;
import TENNIS.TENNISINFO.Match.Domain.Match;
import TENNIS.TENNISINFO.Match.Repository.MatchRepository;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Repository.PlayerRepository;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import TENNIS.TENNISINFO.Rank.Repository.RankingRepository;
import TENNIS.TENNISINFO.Round.Domain.Round;
import TENNIS.TENNISINFO.Round.Repository.RoundRepository;
import TENNIS.TENNISINFO.Season.Domain.Season;
import TENNIS.TENNISINFO.Season.Repository.SeasonRepository;
import TENNIS.TENNISINFO.Tournament.Domain.Tournament;
import TENNIS.TENNISINFO.Tournament.Repository.TournamentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MasterServiceImpl implements MasterService{


    private final PlayerRepository playerRepository;
    private final RankingRepository rankingRepository;
    private final CategoryRepository categoryRepository;
    private final TournamentRepository tournamentRepository;
    private final SeasonRepository seasonRepository;
    private final RoundRepository roundRepository;
    private final MatchRepository matchRepository;
    private final ObjectMapper objectMapper;
    private AbstractApiClient apiClient;

    private final Map<String, AbstractApiClient> apiClientMap;

    public MasterServiceImpl(PlayerRepository playerRepository, RankingRepository rankingRepository,
                             CategoryRepository categoryRepository,
                             TournamentRepository tournamentRepository,
                             SeasonRepository seasonRepository,
                             RoundRepository roundRepository,
                             MatchRepository matchRepository,
                             ObjectMapper objectMapper, Map<String, AbstractApiClient> apiClientMap){
        this.playerRepository = playerRepository;
        this.rankingRepository = rankingRepository;
        this.categoryRepository = categoryRepository;
        this.tournamentRepository = tournamentRepository;
        this.seasonRepository = seasonRepository;
        this.roundRepository = roundRepository;
        this.matchRepository = matchRepository;
        this.objectMapper = objectMapper;
        this.apiClientMap = apiClientMap;

    }
    @Override
    @Transactional
    public void savePlayerAndRanking() throws Exception {

        apiClient = apiClientMap.get("rankingApiClientTest");
        // 랭킹 API 조회
        List<RankingRapidDTO> rankingApiList = apiClient.executeListApiCall(RapidApi.ATPRANKINGS.getUrl(),RapidApi.ATPRANKINGS.getMethodName());

        // playerRapidId 추출
        List<String> ids = rankingApiList.stream()
                .map(RankingRapidDTO::getTeam)
                .map(PlayerRapidDTO::getPlayerRapidId)
                .collect(Collectors.toList());

        apiClient = apiClientMap.get("playerApiClient");

        // 선수 API 조회
        List<PlayerRapidDTO> list = ids.stream()
                .map(p -> {
                    try{
                        Thread.sleep(1000);
                        return (PlayerRapidDTO)apiClient.executeApiCall(RapidApi.TEAMDETAILS.getUrl(p), RapidApi.TEAMDETAILS.getMethodName() );
                    }catch(Exception e){
                        e.printStackTrace();
                        return null;
                    }

                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 선수 Player 형태 변환
        List<Player> playerList = list.stream()
                .map(rapidDTO -> new Player(rapidDTO))
                .map(player -> {
                    Optional<Player> searchPlayer = playerRepository.findByRapidPlayerId(player.getRapidPlayerId());
                    PlayerApiClient playerApiClient = (PlayerApiClient) apiClient;
                    String rapidId = player.getRapidPlayerId();
                    String image = playerApiClient.imageApiCall(RapidApi.TEAMIMAGE.getUrl(rapidId), rapidId);
                    player.setImage(image);
                    if(searchPlayer.isPresent()) player.setPlayerId(searchPlayer.get().getPlayerId());
                    return player;
                })
                .collect(Collectors.toList());

        // 선수 Insert
        List<Player> savePlayer = playerRepository.saveAll(playerList);
        List<String> noData = new ArrayList<>();
        // 랭킹 Insert
        List<Ranking> rankList = rankingApiList.stream()
                .map(rankDTO -> {
                    Optional<Player> player = savePlayer
                            .stream()
                            .filter(pp -> rankDTO.getTeam().getPlayerRapidId().equals(pp.getRapidPlayerId()))
                            .findFirst();

                    if(player.isPresent()){
                        Optional<Ranking> rank = rankingRepository.findByPlayer(player.get());
                        if(rank.isEmpty()){
                            return new Ranking(rankDTO, player.get());
                        }
                    }else{
                        noData.add(rankDTO.getTeam().getPlayerRapidId());
                        return null;
                    }
                    return null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        if(rankList.isEmpty()) {
            System.out.println("데이터가 없습니다.");
            return;
        }

        List<Ranking> saveRank = rankingRepository.saveAll(rankList);
        System.out.println("저장되지 않은 데이터(playerRapidId)");
        noData.forEach(p -> System.out.println(p));

    }

    @Override
    public void saveCategory() throws Exception {
        apiClient = apiClientMap.get("categoryApiClientTest");

        List<CategoryRapidDTO> categoryApiList = apiClient.executeListApiCall(RapidApi.CATEGORY.getUrl(), RapidApi.CATEGORY.getMethodName());

        List<Category> categoryList = categoryApiList
                .stream()
                .map(dto -> {
                    Category category = new Category(dto);
                    Optional<Category> findCategory = categoryRepository.findByRapidCategoryId(dto.getCategoryId());
                    if(findCategory.isPresent()) category.setCategoryId(findCategory.get().getCategoryId());

                    return category;
                })
                .toList();

        categoryRepository.saveAll(categoryList);
    }

    @Override
    @Transactional
    public void saveTournament() throws Exception {
        apiClient = apiClientMap.get("tournamentApiClientTest");
        List<TournamentRapidDTO> tournamentInfos = new ArrayList<>();

        List<Tournament> tournamentList = new ArrayList<>();

        // CategoryTournaments 조회(일단 ATP만 조회)
        List<TournamentRapidDTO> getTournamentRapidDTO = apiClient.executeListApiCall(RapidApi.CATEGORYTOURNAMENTS.getUrl("3"),RapidApi.CATEGORYTOURNAMENTS.getMethodName());

        getTournamentRapidDTO.stream().forEach(tournament -> {

                try{
                    // legueDetail 조회
                    TournamentRapidDTO leagueDetail = (TournamentRapidDTO) apiClient.executeApiCall(RapidApi.LEAGUEDETAILS.getUrl(tournament.getTournamentRapidId()), RapidApi.LEAGUEDETAILS.getMethodName());
                    // tournamentInfo 조회
                    TournamentRapidDTO tournamentInfo = (TournamentRapidDTO) apiClient.executeApiCall(RapidApi.TOURNAMENTINFO.getUrl(tournament.getTournamentRapidId()), RapidApi.TOURNAMENTINFO.getMethodName());

                    //objectMapper.readerForUpdating(leagueDetail).readValue(objectMapper.writeValueAsString(tournamentInfo));
                    leagueDetail = objectMapper.updateValue(leagueDetail,tournamentInfo);
                    tournamentInfos.add(leagueDetail);
                }catch(Exception e){
                    e.printStackTrace();
                }

        });

        tournamentInfos.stream().forEach(tournamentDTO -> {

            Category category = categoryRepository.findByRapidCategoryId(tournamentDTO.getCategory().getCategoryId()).get();
            Player mostTitleHolder = new Player();
            Player titleHolder = new Player();
            if(!tournamentDTO.getMostTitlePlayer().isEmpty()){
                mostTitleHolder = findOrSavePlayer(tournamentDTO.getMostTitlePlayer().get(0).getPlayerRapidId());
            }
            if(!tournamentDTO.getMostTitlePlayer().isEmpty()){
                titleHolder = findOrSavePlayer(tournamentDTO.getTitleHolder().getPlayerRapidId());
            }


            Tournament tournament = new Tournament(tournamentDTO, category, mostTitleHolder, titleHolder);
            Optional<Tournament> findTournament = tournamentRepository.findByRapidTournamentId(tournament.getRapidTournamentId());
            if(findTournament.isEmpty()){
                tournamentList.add(tournament);
            }
        });

        tournamentRepository.saveAll(tournamentList);

    }



    public void saveSeason() throws Exception{
        try{
            apiClient = apiClientMap.get("seasonApiClient");
            List<Tournament> findTournament = tournamentRepository.findAll();
            findTournament.stream().forEach(t -> {
                List<SeasonRapidDTO> seasonRapidList = apiClient.executeListApiCall(RapidApi.LEAGUESEASONS.getUrl(t.getRapidTournamentId()),RapidApi.LEAGUESEASONS.getMethodName());

                List<Season> seasonList = seasonRapidList.stream()
                        .map(season -> {
                            SeasonRapidDTO seasonInfo = (SeasonRapidDTO) apiClient.executeApiCall(RapidApi.LEAGUESEASONINFO.getUrl(t.getRapidTournamentId(),season.getSeasonRapidId()), RapidApi.LEAGUESEASONINFO.getMethodName());
                            if(seasonInfo == null) return null;
                            seasonInfo.setSeasonRapidId(season.getSeasonRapidId());
                            seasonInfo.setYear(season.getYear());
                            return new Season(seasonInfo,t);
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                seasonRepository.saveAll(seasonList);
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveRound() throws Exception{
        apiClient = apiClientMap.get("roundApiClient");
        List<Season> findSeason = seasonRepository.findAll();
        findSeason.stream().forEach(s -> {
            Optional<Tournament> findTournament = tournamentRepository.findById(s.getTournament().getTournamentId());
            if(findTournament.isPresent()){
                Tournament tournament = findTournament.get();
                String tournamentRapidId = tournament.getRapidTournamentId();
                String seasonRapidId = s.getRapidSeasonId();

                List<RoundRapidDTO> roundRapidList = apiClient.executeListApiCall(RapidApi.LEAGUEROUNDS.getUrl(tournamentRapidId, seasonRapidId),
                        RapidApi.LEAGUEROUNDS.getMethodName());

                if(roundRapidList != null){
                    List<Round> roundList = roundRapidList.stream()
                                                          .map(roundDTO -> {
                                                              Round round = new Round(roundDTO, s);
                                                              Optional<Round> existingRound = roundRepository.findByRapidRoundIdAndSlugAndSeason(round.getRapidRoundId(), round.getSlug(), s);

                                                              if(existingRound.isPresent()) round.setRoundId(existingRound.get().getRoundId());

                                                              return round;
                                                          }).toList();

                    roundRepository.saveAll(roundList);
                }
            }
        });


    }

    @Override
    public void saveMatch() throws Exception {
        apiClient = apiClientMap.get("matchApiClient");
        List<Round> findRound = roundRepository.findRoundAndSeasonAndTournament();
        findRound.stream().forEach(r -> {
            String rapidTournamentId = r.getSeason().getTournament().getRapidTournamentId();
            String rapidSeasonId = r.getSeason().getRapidSeasonId();
            String rapidRoundId = r.getRapidRoundId();
            String rapidSlug = r.getSlug();
            if(!(apiClient instanceof MatchApiClient)) apiClient = apiClientMap.get("matchApiClient");
            List<MatchRapidDTO> matchRapidList = apiClient.executeListApiCall(RapidApi.LEAGUEEVENTSBYROUND.getUrl(rapidTournamentId, rapidSeasonId, rapidRoundId, rapidSlug), RapidApi.LEAGUEEVENTSBYROUND.getMethodName());

            if(matchRapidList != null){
                matchRapidList.forEach(matchRapidDTO -> {
                    Player homePlayer = findOrSavePlayer(matchRapidDTO.getHomePlayer().getPlayerRapidId());
                    Player awayPlayer = findOrSavePlayer(matchRapidDTO.getAwayPlayer().getPlayerRapidId());
                    Match match = new Match(matchRapidDTO, r,  homePlayer, awayPlayer);
                    if(matchRepository.findByMatchRapidId(match.getMatchRapidId()).isEmpty()){
                        matchRepository.save(match);
                    }
                });

            }

        });
    }

    public Player findOrSavePlayer(String rapidPlayerId){
        Optional<Player> findPlayer = playerRepository.findByRapidPlayerId(rapidPlayerId);
        if(findPlayer.isPresent()){
            return findPlayer.get();
        }
        Player savePlayer = new Player();
        try{
            apiClient = apiClientMap.get("playerApiClientTest");
            PlayerRapidDTO searchPlayerDTO = (PlayerRapidDTO)apiClient.executeApiCall(RapidApi.TEAMDETAILS.getUrl(rapidPlayerId), RapidApi.TEAMDETAILS.getMethodName());
            savePlayer = new Player(searchPlayerDTO);
            savePlayer = playerRepository.save(savePlayer);

        }catch(Exception e){
            e.printStackTrace();
        }

        return savePlayer;
    }

}
