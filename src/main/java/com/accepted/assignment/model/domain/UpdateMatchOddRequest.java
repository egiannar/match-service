package com.accepted.assignment.model.domain;

import lombok.Data;

@Data
public class UpdateMatchOddRequest {
  private String specifier;
  private double odd;

}
