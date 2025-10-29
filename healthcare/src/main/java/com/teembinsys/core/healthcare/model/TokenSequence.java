package com.teembinsys.core.healthcare.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "token_sequence")
@Getter
@Setter
public class TokenSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_value", nullable = false)
    private Long lastValue;

    @Column(name = "last_reset_time")
    private LocalDateTime lastResetTime;
}
