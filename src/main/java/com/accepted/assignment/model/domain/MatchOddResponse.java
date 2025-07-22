package com.accepted.assignment.model.domain;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MatchOddResponse{
    private Integer id;
    private Integer matchId;
    private String specifier;
    private double odd;
}
