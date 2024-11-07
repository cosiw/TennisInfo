package TENNIS.TENNISINFO.Player.Domain.Enum;

public enum BackHandType {
    ONE_HANDED,
    TWO_HANDED;
    public static BackHandType fromString(String value){
        return BackHandType.valueOf(value.replace("-","_").toUpperCase());
    }
}
