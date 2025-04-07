package org.lessons.java.final_project.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "videogiochi")
public class Videogioco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Column(nullable = false)
    private String titolo;

    @Lob
    @Size(max = 500, message = "La descrizione non può essere più lunga di 500 caratteri.")
    private String descrizione;

    @NotBlank(message = "URL è obbligatorio")
    @URL(message = "Inserisci un URL valido")
    private String copertina_url;

    @Max(value = 5, message = "Il voto non può superare 5.00")
    @Column(precision = 3, scale = 2, nullable = true)
    private BigDecimal voto;

    @Column(name = "anno_uscita")
    @PastOrPresent(message = "L'anno di uscita non può essere successivo all'anno corrente")
    private LocalDate anno_uscita;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime aggiunto_il;

    @ManyToMany
    @JoinTable(name = "genere_videogioco", joinColumns = @JoinColumn(name = "videogioco_id"), inverseJoinColumns = @JoinColumn(name = "genere_id"))
    private List<Genere> generi;

    @ManyToMany
    @JoinTable(name = "console_videogioco", joinColumns = @JoinColumn(name = "videogioco_id"), inverseJoinColumns = @JoinColumn(name = "console_id"))
    private List<Console> console;

    @Override
    public String toString(){
        return String.format("Videogioco: %s - %s", titolo, anno_uscita);
    }

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
     * @return String return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo the titolo to set
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @return String return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @return String return the copertina_url
     */
    public String getCopertina_url() {
        return copertina_url;
    }

    /**
     * @param copertina_url the copertina_url to set
     */
    public void setCopertina_url(String copertina_url) {
        this.copertina_url = copertina_url;
    }

    /**
     * @return BigDecimal return the voto
     */
    public BigDecimal getVoto() {
        return voto;
    }

    /**
     * @param voto the voto to set
     */
    public void setVoto(BigDecimal voto) {
        this.voto = voto;
    }

    /**
     * @return LocalDate return the anno_uscita
     */
    public LocalDate getAnno_uscita() {
        return anno_uscita;
    }

    /**
     * @param anno_uscita the anno_uscita to set
     */
    public void setAnno_uscita(LocalDate anno_uscita) {
        this.anno_uscita = anno_uscita;
    }

    /**
     * @return LocalDateTime return the aggiunto_il
     */
    public LocalDateTime getAggiunto_il() {
        return aggiunto_il;
    }

    /**
     * @param aggiunto_il the aggiunto_il to set
     */
    public void setAggiunto_il(LocalDateTime aggiunto_il) {
        this.aggiunto_il = aggiunto_il;
    }

    /**
     * @return List<Genere> return the generi
     */
    public List<Genere> getGeneri() {
        return generi;
    }

    /**
     * @param generi the generi to set
     */
    public void setGeneri(List<Genere> generi) {
        this.generi = generi;
    }


    /**
     * @return List<Console> return the console
     */
    public List<Console> getConsole() {
        return console;
    }

    /**
     * @param console the console to set
     */
    public void setConsole(List<Console> console) {
        this.console = console;
    }

}
