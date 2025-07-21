package com.accepted.assignment.service;

import com.accepted.assignment.exception.NotFoundException;
import com.accepted.assignment.exception.SameTeamMatchException;
import com.accepted.assignment.model.Match;
import com.accepted.assignment.model.domain.MatchRequest;
import com.accepted.assignment.repository.MatchRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MatchServiceImpl implements MatchService{

  private final MatchRepository matchRepository;

  public MatchServiceImpl(MatchRepository matchRepository) {
    this.matchRepository = matchRepository;
  }

  @Override
  public Match createMatch(MatchRequest request) {
    if (request.getTeamA().equalsIgnoreCase(request.getTeamB())) {
      throw new SameTeamMatchException("Invalid match, team %s cannot play against itself. Please try again.".formatted(request.getTeamA()));
    }

    Match match = Match.builder()
        .description(request.getDescription())
        .matchDate(request.getMatchDate())
        .matchTime(request.getMatchTime())
        .teamA(request.getTeamA())
        .teamB(request.getTeamB())
        .sport(request.getSport())
    .build();
    return matchRepository.save(match);
  }

  @Override
  public List<Match> getAllMatches() {
    List<Match> matches = matchRepository.findAll();

    log.info("Total matches found: {}", matches.size());
    return matches;
  }

  @Override
  public Match getMatchById(Integer id) {
    return matchRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("No match found with id %s".formatted(id)));
  }

  @Override
  public Match updateMatch(Integer id, MatchRequest request) {
    if (!matchRepository.existsById(id))
      throw new NotFoundException("Match with id %s not found".formatted(id));

    if (request.getTeamA().equalsIgnoreCase(request.getTeamB())) {
      throw new SameTeamMatchException("Invalid match, team %s cannot play against itself. Please try again.".formatted(request.getTeamA()));
    }

    Match match = Match.builder()
        .id(id)
        .description(request.getDescription())
        .matchDate(request.getMatchDate())
        .matchTime(request.getMatchTime())
        .teamA(request.getTeamA())
        .teamB(request.getTeamB())
        .sport(request.getSport())
    .build();
    return matchRepository.save(match);
  }

  @Override
  public void deleteMatchById(Integer id) {
    if (!matchRepository.existsById(id))
      throw new NotFoundException("Match with id %s not found".formatted(id));

    matchRepository.deleteById(id);
    log.info("Match with id {} deleted successfully", id);
  }
}
