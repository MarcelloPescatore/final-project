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
    @Column(name = "copertina_url")
    private String copertinaUrl;

    @Max(value = 5, message = "Il voto non può superare 5.00")
    @Column(precision = 3, scale = 2, nullable = true)
    private BigDecimal voto;

    @Column(name = "data_uscita")
    @PastOrPresent(message = "L'anno di uscita non può essere successivo all'anno corrente")
    private LocalDate dataUscita;

    @CreationTimestamp
    @Column(nullable = false, name = "aggiunto_il")
    private LocalDateTime aggiuntoIl;

    @ManyToMany
    @JoinTable(name = "genere_videogioco", joinColumns = @JoinColumn(name = "videogioco_id"), inverseJoinColumns = @JoinColumn(name = "genere_id"))
    private List<Genere> generi;

    @ManyToMany
    @JoinTable(name = "console_videogioco", joinColumns = @JoinColumn(name = "videogioco_id"), inverseJoinColumns = @JoinColumn(name = "console_id"))
    private List<Console> console;

    @Override
    public String toString(){
        return String.format("Videogioco: %s - %s", titolo, dataUscita);
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
    public String getCopertinaUrl() {
        return copertinaUrl;
    }

    /**
     * @param copertinaUrl the copertinaUrl to set
     */
    public void setCopertinaUrl(String copertinaUrl) {
        this.copertinaUrl = copertinaUrl;
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
     * @return LocalDate return the dataUscita
     */
    public LocalDate getDataUscita() {
        return dataUscita;
    }

    /**
     * @param dataUscita the dataUscita to set
     */
    public void setDataUscita(LocalDate dataUscita) {
        this.dataUscita = dataUscita;
    }

    /**
     * @return LocalDateTime return the aggiunto_il
     */
    public LocalDateTime getAggiuntoIl() {
        return aggiuntoIl;
    }

    /**
     * @param aggiuntoIl the aggiuntoIl to set
     */
    public void setAggiuntoIl(LocalDateTime aggiuntoIl) {
        this.aggiuntoIl = aggiuntoIl;
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
