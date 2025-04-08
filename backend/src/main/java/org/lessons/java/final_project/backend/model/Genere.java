package org.lessons.java.final_project.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "generi")
public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "generi")
    @JsonBackReference
    private List<Videogioco> videogiochi;


    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return List<Videogioco> return the videogiochi
     */
    public List<Videogioco> getVideogiochi() {
        return videogiochi;
    }

    /**
     * @param videogiochi the videogiochi to set
     */
    public void setVideogiochi(List<Videogioco> videogiochi) {
        this.videogiochi = videogiochi;
    }

    @Override
    public String toString(){
        return String.format("%s", nome);
    }

}
