package com.accepted.assignment.service;

import com.accepted.assignment.model.domain.MatchOddRequest;
import com.accepted.assignment.model.domain.MatchOddResponse;
import com.accepted.assignment.model.domain.UpdateMatchOddRequest;
import java.util.List;

public interface MatchOddService {
    MatchOddResponse createMatchOdd(Integer id, MatchOddRequest createMatchOddRequest);
    List<MatchOddResponse> getAllMatchesOdds();
    List<MatchOddResponse> getMatchOddsResponseByMatchId(Integer matchId);
    MatchOddResponse updateMatchOdd(Integer matchId, UpdateMatchOddRequest updateMatchOdd);
    void deleteMatchOddsByMatchId(Integer id);
}
