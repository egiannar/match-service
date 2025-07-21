package com.accepted.assignment.serializers;

import com.accepted.assignment.exception.UnsupportedSportException;
import com.accepted.assignment.model.enums.Sport;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class SportDeserializer extends JsonDeserializer<Sport> {
  @Override
  public Sport deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    int id = p.getIntValue();
    try {
      return Sport.fromId(id);
    } catch (UnsupportedSportException ex) {
      throw new UnsupportedSportException("Unsupported match id: " + id);
    }
  }
}
