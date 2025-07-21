package com.accepted.assignment.model.domain;

import com.accepted.assignment.model.enums.Sport;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class UpdateMatchRequest {
  private Integer id;
  private String description;
  private LocalDate matchDate;
  private LocalTime matchTime;
  private String teamA;
  private String teamB;
  private Sport sport;
}
