package com.arfa.backend.repository;

import com.arfa.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

    Optional<Player> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT p FROM Player p ORDER BY p.highScore DESC")
    List<Player> findTopPlayersByHighScore();

    List<Player> findByHighScoreGreaterThan(Integer minScore);
    List<Player> findAllByOrderByTotalCoinsDesc();
    List<Player> findAllByOrderByTotalDistanceTravelledDesc();
}
