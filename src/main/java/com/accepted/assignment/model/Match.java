package com.accepted.assignment.model;

import com.accepted.assignment.model.enums.Sport;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;

  @Column(name = "match_date")
  private LocalDate matchDate;

  @Column(name = "match_time")
  private LocalTime matchTime;

  @Column(name = "team_a")
  private String teamA;

  @Column(name = "team_b")
  private String teamB;

  @Enumerated(EnumType.STRING)
  private Sport sport;

}