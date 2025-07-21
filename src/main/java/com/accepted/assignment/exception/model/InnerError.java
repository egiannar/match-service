package com.accepted.assignment.exception.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InnerError {

  private String type;
  private String date;

}
