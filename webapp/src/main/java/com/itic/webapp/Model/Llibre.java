package com.itic.webapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "Llibre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_llibre")
    private Integer idLlibre;

    @NotNull(message = "El título no puede ser nulo")
    @Column(nullable = false)
    private String titol;

    private String autor;
    private String editorial;

    @Column(name = "datapublicacio")
    private LocalDate dataPublicacio;

    private String tematica;

    @Column(unique = true, nullable = false)
    private String isbn;

}