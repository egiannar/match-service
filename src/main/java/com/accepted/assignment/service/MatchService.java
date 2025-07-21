package com.accepted.assignment.service;

import com.accepted.assignment.model.Match;
import com.accepted.assignment.model.domain.MatchRequest;
import java.util.List;

public interface MatchService {
  Match createMatch(MatchRequest request);
  List<Match> getAllMatches();
  Match getMatchById(Integer id);
  Match updateMatch(Integer id, MatchRequest request);
  void deleteMatchById(Integer id);
}
