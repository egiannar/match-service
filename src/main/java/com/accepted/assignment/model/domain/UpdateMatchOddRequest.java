package com.accepted.assignment.model.domain;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateMatchOddRequest {
  private String specifier;
  private double odd;

}
