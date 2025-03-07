package TENNIS.TENNISINFO.Common.Enum;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ImageDir {

    PLAYER("player", "player/thumbnail/");

    private final String name;
    private final String dir;

    ImageDir(String name, String dir){
        this.name = name;
        this.dir = dir;
    }

    public String getName(){
        return this.name;
    }

    public String getDir(){
        return this.dir;
    }


}
