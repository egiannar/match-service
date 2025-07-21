package com.accepted.assignment.model.domain;

import com.accepted.assignment.model.enums.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class MatchRequest {
  private String description;
  @Schema(type = "string", format = "time", description = "Date of the match in YYYY-MM-DD format", example = "2026-01-05")
  private LocalDate matchDate;
  @Schema(type = "string", format = "time", description = "Time of the match in HH:mm:ss format", example = "18:30:00")
  private LocalTime matchTime;
  private String teamA;
  private String teamB;
  @Schema(type = "integer", description = "Sport type: 1 for FOOTBALL, 2 for BASKETBALL", example = "1")
  private Sport sport;
}
