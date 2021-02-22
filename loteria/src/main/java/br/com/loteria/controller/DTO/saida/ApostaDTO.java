package br.com.loteria.controller.DTO.saida;

import br.com.loteria.modelo.Aposta;
import org.springframework.data.domain.Page;

public class ApostaDTO {

    private Long id;

    private String email;

    private String aposta;

    public ApostaDTO(Aposta novaAposta){
        this.id = novaAposta.getId();
        this.email = novaAposta.getApostadorId().getEmail();
        this.aposta = novaAposta.getAposta();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAposta() {
        return aposta;
    }

    public static Page<ApostaDTO> toAposta(Page<Aposta> apostas){
        return apostas.map(ApostaDTO::new);
    }
}
