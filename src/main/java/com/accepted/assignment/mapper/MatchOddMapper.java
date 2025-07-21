package com.accepted.assignment.mapper;

import com.accepted.assignment.model.MatchOdd;
import com.accepted.assignment.model.domain.MatchOddResponse;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class MatchOddMapper {

  public MatchOddResponse toResponse(MatchOdd matchOdd) {
    return MatchOddResponse.builder()
        .id(matchOdd.getId())
        .matchId(matchOdd.getMatch().getId())
        .specifier(matchOdd.getSpecifier())
        .odd(matchOdd.getOdd())
        .build();
  }

}
