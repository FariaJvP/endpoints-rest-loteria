package br.com.loteria.controller;

import br.com.loteria.controller.DTO.entrada.LoginForm;
import br.com.loteria.controller.DTO.saida.TokenDTO;
import br.com.loteria.seguranca.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try{
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        }catch (AuthenticationException exception){
            return ResponseEntity.badRequest().build();
        }
    }
}
