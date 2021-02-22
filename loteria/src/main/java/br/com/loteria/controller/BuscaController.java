package br.com.loteria.controller;

import br.com.loteria.controller.DTO.saida.ApostaDTO;
import br.com.loteria.modelo.Aposta;
import br.com.loteria.modelo.Apostador;
import br.com.loteria.repository.ApostaRepository;
import br.com.loteria.repository.ApostadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/busca")
public class BuscaController {

    @Autowired
    private ApostaRepository apostaRepository;

    @Autowired
    private ApostadorRepository apostadorRepository;

    @GetMapping
    public ResponseEntity<Page<ApostaDTO>> buscaApostas(Authentication authentication, @PageableDefault() Pageable pageable){
        Apostador apostador = (Apostador) authentication.getPrincipal();
        Page<Aposta> apostas = apostaRepository.findApostaByApostadorId(apostador, pageable);

        return ResponseEntity.ok().body(ApostaDTO.toAposta(apostas));
    }
}
