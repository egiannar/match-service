package com.accepted.assignment.model.enums;

import com.accepted.assignment.exception.UnsupportedSportException;
import com.accepted.assignment.serializers.SportDeserializer;
import com.accepted.assignment.serializers.SportSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize(using = SportSerializer.class)
@JsonDeserialize(using = SportDeserializer.class)
public enum Sport {
  FOOTBALL(1),
  BASKETBALL(2);

  private int id;

  Sport(int id) {
    this.id = id;
  }

  public static Sport fromId(int id) {
    for (Sport sport : values()) {
      if (sport.id == id)
        return sport;
    }
    throw new UnsupportedSportException("Unsupported sport id: %d".formatted(id));
  }

}
