package TENNIS.TENNISINFO.Index.Service;

import TENNIS.TENNISINFO.Index.DTO.PlayerDTO;

import java.util.List;

public interface IndexService {
    List<PlayerDTO> getTopRank() throws Exception;
}
