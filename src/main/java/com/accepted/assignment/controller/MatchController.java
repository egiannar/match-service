package com.accepted.assignment.controller;

import com.accepted.assignment.exception.model.ErrorResponse;
import com.accepted.assignment.model.Match;
import com.accepted.assignment.model.MatchOdd;
import com.accepted.assignment.model.domain.MatchOddRequest;
import com.accepted.assignment.model.domain.MatchOddResponse;
import com.accepted.assignment.model.domain.MatchRequest;
import com.accepted.assignment.model.domain.UpdateMatchOddRequest;
import com.accepted.assignment.service.MatchOddService;
import com.accepted.assignment.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/matches")
public class MatchController {

  private final MatchService matchService;
  private final MatchOddService matchOddService;

  public MatchController(MatchService matchManagementService,
      MatchOddService matchOddService) {
    this.matchService = matchManagementService;
    this.matchOddService = matchOddService;
  }

  @Operation(summary = "Create match", description = "Create match")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Match created",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = Match.class),
              examples = @ExampleObject(name = "MatchExample", value = "{\"id\":1, \"description\":\"Championship Final\",\"matchDate\":\"2025-08-15\",\"matchTime\":\"18:30:00\",\"teamA\":\"Team Alpha\",\"teamB\":\"Team Beta\",\"sport\":\"1\"}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }"))),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "BadRequest", value = "{\"error\":{\"code\":\"BAD_REQUEST\",\"message\":\"Invalid match, team PAO cannot play against itself. Please try again.\",\"innerError\":{\"type\":\"BadRequestException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "BadRequest", value = "{\"error\":{\"code\":\"BAD_REQUEST\",\"message\":\"Unsupported sport id:100.\",\"innerError\":{\"type\":\"BadRequestException\",\"date\":\"2025-07-21+03:00\"}}}")))

  })
  @PostMapping("")
  public ResponseEntity<Match> createMatch(@RequestBody MatchRequest request) {
    Match response = matchService.createMatch(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get match", description = "Get match")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Get match",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = Match.class),
              examples = @ExampleObject(name = "MatchExample", value = "{\"id\":1,\"description\":\"Championship Final\",\"matchDate\":\"2025-08-15\",\"matchTime\":\"18:30:00\",\"teamA\":\"Team Alpha\",\"teamB\":\"Team Beta\",\"sport\":\"1\"}"))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Found", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"No match found with id 325.\",\"innerError\":{\"type\":\"NotFoundException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @GetMapping("/{id}")
  public ResponseEntity<Match> getMatch(@PathVariable Integer id) {
    Match response = matchService.getMatchById(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get all matches", description = "Get all matches")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Get all matches",
          content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Match.class)),
              examples = @ExampleObject(name = "MatchExample", value = "[{\"id\":1,\"description\":\"Championship Final\",\"matchDate\":\"2025-08-15\",\"matchTime\":\"18:30:00\",\"teamA\":\"Team Alpha\",\"teamB\":\"Team Beta\",\"sport\":\"1\"}, \"id\":2,\"description\":\"Championship Semi Final\",\"matchDate\":\"2025-08-15\",\"matchTime\":\"13:30:00\",\"teamA\":\"Team C\",\"teamB\":\"Team D\",\"sport\":\"1\"}]"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @GetMapping("")
  public ResponseEntity<List<Match>> getAllMatches() {
    List<Match> response = matchService.getAllMatches();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @Operation(summary = "Update match", description = "Update match")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Update match",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = Match.class),
              examples = @ExampleObject(name = "MatchExample", value = "{\"id\":1, \"description\":\"Championship Final\",\"matchDate\":\"2025-09-16\",\"matchTime\":\"20:30:00\",\"teamA\":\"Team Alpha\",\"teamB\":\"Team Beta\",\"sport\":\"1\"}"))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Found", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"No match found with id 6.\",\"innerError\":{\"type\":\"NotFoundException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Bad Request", value = "{\"error\":{\"code\":\"BAD_REQUEST\",\"message\":\"Invalid match, team PAO cannot play against itself. Please try again.\",\"innerError\":{\"type\":\"BadRequestException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @PutMapping("/{id}")
  public ResponseEntity<Match> updateMatch(@PathVariable Integer id, @RequestBody MatchRequest request) {
    Match response = matchService.updateMatch(id, request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Delete match", description = "Delete match")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Delete match",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class),
              examples = @ExampleObject(value = ""))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "BadRequest", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"No match found with id 6.\",\"innerError\":{\"type\":\"NotFoundException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMatch(@PathVariable Integer id) {
    matchService.deleteMatchById(id);
    return ResponseEntity.ok().build();
  }

  // match odds endpoints

  @Operation(summary = "Create match odd", description = "Create match odd")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Match odd created",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchOddResponse.class),
              examples = @ExampleObject(name = "CrateMatchOddExample", value = "{\"id\":1, \"matchId\":\"1\", \"specifier\":\"X\",\"odd\":2.75}"))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Found", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"No match found with id 6.\",\"innerError\":{\"type\":\"NotFoundException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "409", description = "Conflict",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Conflict", value = "{\"error\":{\"code\":\"CONFLICT\",\"message\":\"Odd X for match id 2 already exists\",\"innerError\":{\"type\":\"ConflictException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "406", description = "Not Acceptable",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Acceptable", value = "{\"error\":{\"code\":\"NOT_ACCEPTABLE\",\"message\":\"Odd 0.95 is not valid, as it must be over 1.01. Please try again\",\"innerError\":{\"type\":\"ConflictException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @PostMapping("/{id}/odds")
  public ResponseEntity<MatchOddResponse> createMatchOdd(@PathVariable Integer id, @RequestBody MatchOddRequest matchOddRequest) {
    MatchOddResponse response = matchOddService.createMatchOdd(id, matchOddRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get match odds", description = "Get match odds")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Get match odds",
          content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MatchOddResponse.class)),
              examples = @ExampleObject(name = "MatchExample", value = "{[\"id\":1,\"matchId\":10,\"specifier\":\"1\",\"odd\":2.75}, \"id\":2,\"matchId\":10,\"specifier\":\"X\",\"odd\":1.75}]}"))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Bad Request", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"No match found with id 6.\",\"innerError\":{\"type\":\"NotFoundException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @GetMapping("/{id}/odds")
  public ResponseEntity<List<MatchOddResponse>> getMatchOdds(@PathVariable Integer id) {
    List<MatchOddResponse> response = matchOddService.getMatchOddsResponseByMatchId(id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get all matches odds", description = "Get all matches odds")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Get all matches odds",
          content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MatchOddResponse.class)),
              examples = @ExampleObject(name = "MatchExample", value = "{[\"id\":1,\"matchId\":10,\"specifier\":\"1\",\"odd\":2.75}, \"id\":2,\"matchId\":10,\"specifier\":\"X\",\"odd\":1.75}]"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @GetMapping("/odds")
  public ResponseEntity<List<MatchOddResponse>> getAllMatchesOdds() {
    List<MatchOddResponse> response = matchOddService.getAllMatchesOdds();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Update match odd", description = "Update match odd")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Update match odd",
          content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MatchOdd.class)),
              examples = @ExampleObject(name = "UpdateMatchOddExample", value = "{\"id\":1,\"matchId\":10,\"specifier\":\"1\",\"odd\":3.05}"))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Found", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"No match found with id 325.\",\"innerError\":{\"type\":\"NotFoundException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Found", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"MatchOdd not found for matchId 1 and specifier 2\",\"innerError\":{\"type\":\"BadRequestException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Bad Request", value = "{\"error\":{\"code\":\"BAD_REQUEST\",\"message\":\"TODO:.\",\"innerError\":{\"type\":\"BadRequestException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "406", description = "Not Acceptable",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Acceptable", value = "{\"error\":{\"code\":\"NOT_ACCEPTABLE\",\"message\":\"Odd 0.95 is not valid, as it must be over 1.01. Please try again\",\"innerError\":{\"type\":\"ConflictException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @PutMapping("/{id}/odds")
  public ResponseEntity<MatchOddResponse> updateMatchOdds(@PathVariable Integer id,@RequestBody UpdateMatchOddRequest updateMatchOddRequest) {
    MatchOddResponse response = matchOddService.updateMatchOdd(id, updateMatchOddRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Delete match odds", description = "Update match odds")
  @ApiResponses( value = {
      @ApiResponse( responseCode ="200", description ="Update match odd",
          content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Void.class)),
              examples = @ExampleObject(name = "DeleteMatchOddExample", value = ""))),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Not Found", value = "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"No match found with id 325.\",\"innerError\":{\"type\":\"NotFoundException\",\"date\":\"2025-07-21+03:00\"}}}"))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(name = "Internal Server Error", value = "{ \"error\": { \"code\": \"generalException\", \"message\": \"There was an internal server error while processing the request.\", \"innerError\": { \"type\": \"application\", \"date\": \"2025-07-21T10:42:02.484+00:00\" } } }")))
  })
  @DeleteMapping("/{id}/odds")
  public ResponseEntity<Void> deleteMatchOdds(@PathVariable Integer id) {
    matchOddService.deleteMatchOddsByMatchId(id);
    return ResponseEntity.ok().build();
  }

}
