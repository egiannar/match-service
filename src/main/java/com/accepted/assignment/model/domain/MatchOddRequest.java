package com.accepted.assignment.model.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchOddRequest {

  private String specifier;
  private double odd;

}
