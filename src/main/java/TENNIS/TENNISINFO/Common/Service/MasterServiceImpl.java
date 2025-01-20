package TENNIS.TENNISINFO.Common.Service;

import TENNIS.TENNISINFO.Category.Domain.Category;
import TENNIS.TENNISINFO.Category.Repository.CategoryRepository;
import TENNIS.TENNISINFO.Common.domain.CategoryRapidDTO;
import TENNIS.TENNISINFO.Common.domain.PlayerRapidDTO;
import TENNIS.TENNISINFO.Common.domain.RankingRapidDTO;
import TENNIS.TENNISINFO.Common.domain.TournamentRapidDTO;
import TENNIS.TENNISINFO.Common.rapid.CategoryApiClient;
import TENNIS.TENNISINFO.Common.rapid.PlayerApiClient;
import TENNIS.TENNISINFO.Common.rapid.RankingApiClient;
import TENNIS.TENNISINFO.Common.rapid.TournamentApiClient;
import TENNIS.TENNISINFO.Player.Domain.Player;
import TENNIS.TENNISINFO.Player.Repository.PlayerRepository;
import TENNIS.TENNISINFO.Rank.Domain.Ranking;
import TENNIS.TENNISINFO.Rank.Repository.RankingRepository;
import TENNIS.TENNISINFO.Tournament.Domain.Tournament;
import TENNIS.TENNISINFO.Tournament.Repository.TournamentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterServiceImpl implements MasterService{

    private final RankingApiClient rankingApi;
    private final PlayerApiClient playerApi;
    private final CategoryApiClient categoryApi;
    private final TournamentApiClient tournamentApiClient;
    private final PlayerRepository playerRepository;
    private final RankingRepository rankingRepository;
    private final CategoryRepository categoryRepository;
    private final TournamentRepository tournamentRepository;

    public MasterServiceImpl(RankingApiClient rankingApi, PlayerApiClient playerApi,
                             PlayerRepository playerRepository, RankingRepository rankingRepository,
                             CategoryApiClient categoryApi, CategoryRepository categoryRepository,
                             TournamentApiClient tournamentApiClient, TournamentRepository tournamentRepository){
        this.rankingApi = rankingApi;
        this.playerApi = playerApi;
        this.categoryApi = categoryApi;
        this.tournamentApiClient = tournamentApiClient;
        this.playerRepository = playerRepository;
        this.rankingRepository = rankingRepository;
        this.categoryRepository = categoryRepository;
        this.tournamentRepository = tournamentRepository;

    }
    @Override
    @Transactional
    public void savePlayerAndRanking() throws Exception {
        // 랭킹 API 조회
        List<RankingRapidDTO> rankingApiList = rankingApi.atpRankings();

        // playerRapidId 추출
        List<String> ids = rankingApiList.stream()
                .map(RankingRapidDTO::getTeam)
                .map(PlayerRapidDTO::getPlayerRapidId)
                .skip(9)
                .limit(30)
                .collect(Collectors.toList());

        // 선수 API 조회
        List<PlayerRapidDTO> list = ids.stream()
                .map(p -> {
                    try{
                        return playerApi.teamDetails(p);
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
                        return new Ranking(rankDTO, player.get());
                    }else{
                        noData.add(rankDTO.getTeam().getPlayerRapidId());
                        return null;
                    }
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
    public void saveTournament() throws Exception {

        List<Tournament> tournamentList = new ArrayList<>();
        // CategoryTournaments 조회(일단 ATP만 조회)
        List<TournamentRapidDTO> getTournamentRapidDTO = categoryApi.categoryTournaments("3");
        // 임시
        List<TournamentRapidDTO> list  = getTournamentRapidDTO.stream().limit(3).toList();
        // LeagueDetails 조회
        List<TournamentRapidDTO> getDetails = tournamentApiClient.LeagueDetails(list);

        // TournamentInfo 조회
        List<TournamentRapidDTO> getTournamentInfos = tournamentApiClient.TournamentInfo(getDetails);

        getTournamentInfos.stream().forEach(tournamentDTO -> {
            Category category = categoryRepository.findByRapidCategoryId(tournamentDTO.getCategory().getCategoryId()).get();

            Player mostTitleHolder = playerRepository.findByRapidPlayerId(tournamentDTO.getMostTitlePlayer().get(0).getPlayerRapidId()).get();

            Player titleHolder = playerRepository.findByRapidPlayerId(tournamentDTO.getTitleHolder().getPlayerRapidId()).get();

            Tournament tournament = new Tournament(tournamentDTO, category, mostTitleHolder, titleHolder);

            tournamentList.add(tournament);
        });

        tournamentRepository.saveAll(tournamentList);

    }

    @Override
    public void saveCategory() throws Exception {
        List<CategoryRapidDTO> categoryApiList = categoryApi.category();
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

}
