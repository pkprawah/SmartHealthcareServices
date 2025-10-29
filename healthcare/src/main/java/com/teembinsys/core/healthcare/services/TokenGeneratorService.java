package com.teembinsys.core.healthcare.services;

import com.teembinsys.core.healthcare.model.Patient;
import com.teembinsys.core.healthcare.model.TokenSequence;
import com.teembinsys.core.healthcare.repositories.TokenSequenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
//@RequiredArgsConstructor
public class TokenGeneratorService {
    private final TokenSequenceRepository tokenSequenceRepository;

    TokenGeneratorService(TokenSequenceRepository tokenSequenceRepository){
        this.tokenSequenceRepository=tokenSequenceRepository;
    }

    private static final int RESET_AFTER_DAYS = 7;

    @Transactional
    public String generateNextTokenNumber(String patientMobileNumber) {
        TokenSequence sequence = tokenSequenceRepository.findTopByOrderByIdAsc()
                .orElseGet(() -> {
                    TokenSequence newSeq = new TokenSequence();
                    newSeq.setLastValue(0L);
                    newSeq.setLastResetTime(LocalDateTime.now());
                    return tokenSequenceRepository.save(newSeq);
                });

        // Reset sequence if older than 7 days
        if (sequence.getLastResetTime() == null ||
                Duration.between(sequence.getLastResetTime(), LocalDateTime.now()).toDays() >= RESET_AFTER_DAYS) {
            sequence.setLastValue(0L);
            sequence.setLastResetTime(LocalDateTime.now());
        }

        // Increment sequence safely
        long newValue = sequence.getLastValue() + 1;
        sequence.setLastValue(newValue);
        tokenSequenceRepository.save(sequence);

        // Build token number: DAY + DATE + LAST4(phone) + SEQ
        String day = LocalDate.now().getDayOfWeek().name().substring(0, 3); // MON
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM"));
        String last4Digits = patientMobileNumber.length() >= 4
                ? patientMobileNumber.substring(patientMobileNumber.length() - 4)
                : "0000";

        String token = String.format("%s%s%s%04d", day, date, last4Digits, newValue);

        return token;
    }
}
