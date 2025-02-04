package TENNIS.TENNISINFO.Common.Enum;


public enum RapidApi {

    ATPRANKINGS("atpRankings", "tennis/rankings/atp"),
    TEAMDETAILS("teamDetails", "tennis/team/%s"),
    CATEGORY("category","tennis/tournament/categories"),
    CATEGORYTOURNAMENTS("categoryTournaments","tennis/tournament/all/category/%s"),
    LEAGUEDETAILS("leagueDetails", "tennis/tournament/%s"),
    TOURNAMENTINFO("tournamentInfo","tennis/tournament/%s/info"),
    LEAGUESEASONS("leagueSeasons", "tennis/tournament/%s/seasons"),
    LEAGUESEASONINFO("leagueSeasonInfo", "tennis/tournament/%s/season/%s/info"),
    LEAGUEROUNDS("leagueRounds", "tennis/tournament/%s/season/%s/rounds");

    private final String methodName;
    private final String url;

    RapidApi(String methodName, String url){
        this.methodName = methodName;
        this.url = url;
    }

    public String getMethodName(){
        return this.methodName;
    }

    public String getUrl(){
        return this.url;
    }
    public String getUrl(Object... params){
        return String.format(url, params);
    }
}
