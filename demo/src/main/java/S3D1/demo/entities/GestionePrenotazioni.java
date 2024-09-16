package S3D1.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "gestione_prenotazioni")
public class GestionePrenotazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_viaggio")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

    private LocalDate dataPrenotazione;

    private String noteEPreferenze;

    public GestionePrenotazioni() {
    }

    public GestionePrenotazioni(int id, Viaggio viaggio, Dipendente dipendente, LocalDate dataPrenotazione, String noteEPreferenze) {
        this.id = id;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataPrenotazione = dataPrenotazione;
        this.noteEPreferenze = noteEPreferenze;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public String getNoteEPreferenze() {
        return noteEPreferenze;
    }

    public void setNoteEPreferenze(String noteEPreferenze) {
        this.noteEPreferenze = noteEPreferenze;
    }

    @Override
    public String toString() {
        return "GestionePrenotazioni{" +
                "id=" + id +
                ", viaggio=" + viaggio +
                ", dipendente=" + dipendente +
                ", dataPrenotazione=" + dataPrenotazione +
                ", noteEPreferenze='" + noteEPreferenze + '\'' +
                '}';
    }
}
