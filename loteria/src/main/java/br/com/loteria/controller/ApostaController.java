package br.com.loteria.controller;

import br.com.loteria.controller.DTO.saida.ApostaDTO;
import br.com.loteria.modelo.Aposta;
import br.com.loteria.modelo.Apostador;
import br.com.loteria.modelo.servico.ApostaService;
import br.com.loteria.repository.ApostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    @Autowired
    private ApostaService apostaService;

    @Autowired
    private ApostaRepository apostaRepository;

    @PostMapping
    public ResponseEntity<ApostaDTO> cadastraAposta(Authentication authentication, UriComponentsBuilder uriComponentsBuilder){
        Apostador apostador = (Apostador) authentication.getPrincipal();
        Aposta aposta = apostaService.realizaAposta(apostador);
        URI uri = uriComponentsBuilder.path("/aposta/{id}").buildAndExpand(aposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApostaDTO(aposta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApostaDTO> detalhar(@PathVariable Long id, Authentication authentication){
        Apostador apostador = (Apostador) authentication.getPrincipal();

        Optional<Aposta> aposta = apostaRepository.FindApostaIdByApostador(id, apostador);
        return aposta.map(valor -> ResponseEntity.ok(new ApostaDTO(valor))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
