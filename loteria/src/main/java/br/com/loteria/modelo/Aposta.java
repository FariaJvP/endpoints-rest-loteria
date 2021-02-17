package br.com.loteria.modelo;

import javax.persistence.*;

@Entity
@Table(name = "tb_aposta")
public class Aposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aposta_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apostador_id", foreignKey = @ForeignKey(name = "fk_apostador_aposta"))
    private Apostador apostadorId;

    private String aposta;

    @Deprecated//construtor padrao
    public Aposta(){}

    public Aposta(Apostador apostadorId, String apostaGerada){
        this.apostadorId = apostadorId;
        this.aposta = apostaGerada;
    }

    public Long getId() {
        return id;
    }

    public Apostador getApostadorId() {
        return apostadorId;
    }

    public String getAposta() {
        return aposta;
    }
}
