package com.accepted.assignment.service;

import com.accepted.assignment.exception.NotFoundException;
import com.accepted.assignment.mapper.MatchOddMapper;
import com.accepted.assignment.model.Match;
import com.accepted.assignment.model.MatchOdd;
import com.accepted.assignment.model.domain.MatchOddRequest;
import com.accepted.assignment.model.domain.MatchOddResponse;
import com.accepted.assignment.model.domain.UpdateMatchOddRequest;
import com.accepted.assignment.repository.MatchOddRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MatchOddServiceImpl implements MatchOddService{

  private static final String MATCH_NOT_FOUND_MESSAGE = "Match with id %s not found";

  private final MatchService matchService;
  private final MatchOddMapper matchOddMapper;
  private final MatchOddRepository matchOddRepository;

  public MatchOddServiceImpl(MatchService matchService, MatchOddMapper matchOddMapper, MatchOddRepository matchOddRepository) {
    this.matchService = matchService;
    this.matchOddMapper = matchOddMapper;
    this.matchOddRepository = matchOddRepository;
  }

  @Override
  public MatchOddResponse createMatchOdd(Integer id, MatchOddRequest createMatchOddRequest) {
    Match match = matchService.getMatchById(id);
    if (match == null)
      throw new NotFoundException(MATCH_NOT_FOUND_MESSAGE.formatted(id));

    MatchOdd matchOdd = MatchOdd.builder()
        .match(match)
        .specifier(createMatchOddRequest.getSpecifier())
        .odd(createMatchOddRequest.getOdd())
    .build();
    MatchOdd saved = matchOddRepository.save(matchOdd);
    return matchOddMapper.toResponse(saved);
  }

  @Override
  public List<MatchOddResponse> getAllMatchesOdds() {
    List<MatchOddResponse> responseList = new ArrayList<>();
    List<MatchOdd> matchesOdds = matchOddRepository.findAll();

    for (MatchOdd matchOdd : matchesOdds) {
      MatchOddResponse response = matchOddMapper.toResponse(matchOdd);
      responseList.add(response);
    }

    log.info("Total matches found: {}", matchesOdds.size());
    return responseList;
  }

  @Override
  public List<MatchOddResponse> getMatchOddsResponseByMatchId(Integer matchId) {
    List<MatchOddResponse> responseList = new ArrayList<>();
    List<MatchOdd> matchesOdds = matchOddRepository.findByMatchId(matchId);

    for (MatchOdd matchOdd : matchesOdds) {
      MatchOddResponse response = matchOddMapper.toResponse(matchOdd);
      responseList.add(response);
    }

    return responseList;
  }

  @Override
  public MatchOddResponse updateMatchOdd(Integer matchId, UpdateMatchOddRequest updateMatchOdd) {
    Match matchById = matchService.getMatchById(matchId);
    if (matchById == null)
      throw new NotFoundException(MATCH_NOT_FOUND_MESSAGE.formatted(matchId));

    MatchOdd matchOdd = matchOddRepository.findByMatchIdAndSpecifier(matchId,
            updateMatchOdd.getSpecifier())
        .orElseThrow(
            () -> new RuntimeException(
                "MatchOdd not found for matchId %s and specifier %s"
                    .formatted(matchId, updateMatchOdd.getSpecifier())
            )
        );

    matchOdd.setOdd(updateMatchOdd.getOdd());
    MatchOdd saved = matchOddRepository.save(matchOdd);
    return matchOddMapper.toResponse(saved);
  }

  @Override
  public void deleteMatchOddsByMatchId(Integer id) {
    Match matchById = matchService.getMatchById(id);
    if (matchById == null)
      throw new NotFoundException(MATCH_NOT_FOUND_MESSAGE.formatted(id));

    List<MatchOdd> matchOddByMatchId = getMatchOddsByMatchId(id);
    log.info("{} odds found for match with id {}", matchOddByMatchId.size(), id);
    
    for (MatchOdd matchOdd : matchOddByMatchId) {
      Integer matchId = matchOdd.getId();
      matchOddRepository.deleteById(matchId);
      log.info("Match odd with id {} deleted successfully", matchId);
    }

    log.info("Match with id {} deleted successfully", id);
  }

  public List<MatchOdd> getMatchOddsByMatchId(Integer matchId) {
    return matchOddRepository.findByMatchId(matchId);
  }

}
