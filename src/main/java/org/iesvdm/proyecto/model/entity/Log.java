package org.iesvdm.proyecto.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"name", "material_id"})
})
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDate logDate; // timestamp of the log
    // Enum for actions
    public enum Action {
        ADD, REMOVE
    }

    @Enumerated(EnumType.STRING)
    private Action action;

    private int amount; // renamed from stock for clarity

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color; // reference to Color entity
}