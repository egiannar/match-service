package com.accepted.assignment.serializers;

import com.accepted.assignment.model.enums.Sport;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class SportSerializer extends JsonSerializer<Sport> {
  @Override
  public void serialize(Sport value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeNumber(value.getId());
  }
}
