package com.carus.entities;

import com.carus.enums.EState;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cnh")
@Data
public class CnhEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cnh_user"))
    private UserEntity user;

    @Column(nullable = false, length = 8)
    private String rg;

    @Column(nullable = false, length = 11)
    private String registerNumber;

    @Column(nullable = false, length = 10, unique = true)
    private String cnhNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate expirationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EState state;
}
