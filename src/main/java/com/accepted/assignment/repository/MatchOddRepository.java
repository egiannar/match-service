package com.accepted.assignment.repository;

import com.accepted.assignment.model.MatchOdd;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchOddRepository extends JpaRepository<MatchOdd, Integer> {

  List<MatchOdd> findByMatchId(Integer matchId);

  Optional<MatchOdd> findByMatchIdAndSpecifier(Integer matchId, String specifier);

}
