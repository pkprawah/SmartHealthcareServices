package com.teembinsys.core.healthcare.repositories;


import com.teembinsys.core.healthcare.model.TokenSequence;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TokenSequenceRepository extends JpaRepository<TokenSequence, Long> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO token_sequence (sequence_date, last_number)
        VALUES (:sequenceDate, 1)
        ON CONFLICT (sequence_date)
        DO UPDATE SET last_number = token_sequence.last_number + 1
        RETURNING last_number;
        """, nativeQuery = true)
    Long getNextTokenNumber(@Param("sequenceDate") LocalDate sequenceDate);

    // 🧹 Cleanup method: remove records older than 7 days
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM token_sequence WHERE sequence_date < :cutoffDate", nativeQuery = true)
    int deleteOldSequences(@Param("cutoffDate") LocalDate cutoffDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TokenSequence> findTopByOrderByIdAsc();
}
