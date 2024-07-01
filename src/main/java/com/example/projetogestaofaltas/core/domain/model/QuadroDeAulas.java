package com.example.projetogestaofaltas.core.domain.model;

import com.example.projetogestaofaltas.core.domain.model.enumareted.DiaDaSemana;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.util.GregorianCalendar;

@Data
@Entity
@Builder
public class QuadroDeAulas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;
    @Column(nullable = false)
    private Time horarioInicio;
    @Column(nullable = false)
    private Time horarioFim;

    @CreationTimestamp
    @Column(nullable = false)
    private GregorianCalendar dataAdicionado;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;



    public QuadroDeAulas() {

    }

    public QuadroDeAulas(Long id, DiaDaSemana diaDaSemana, Time horarioInicio, Time horarioFim, GregorianCalendar dataAdicionado, Professor professor, Materia materia, Escola escola) {
        this.id = id;
        this.diaDaSemana = diaDaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.dataAdicionado = dataAdicionado;
        this.professor = professor;
        this.materia = materia;
        this.escola = escola;
    }
}
