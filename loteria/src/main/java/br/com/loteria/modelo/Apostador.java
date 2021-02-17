package br.com.loteria.modelo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_apostador")
public class Apostador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apostador_id")
    private Long id;

    private String email;

    private String senha;

    private List<Aposta> apostas;


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public List<Aposta> getApostas() {
        return apostas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apostador)) return false;
        Apostador apostador = (Apostador) o;
        return Objects.equals(id, apostador.id) && Objects.equals(email, apostador.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }


}
