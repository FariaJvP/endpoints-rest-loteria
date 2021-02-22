package br.com.loteria.seguranca;

import br.com.loteria.modelo.Apostador;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${loteria.jwt.expiration}")
    private String expiration;

    @Value("${loteria.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        Apostador logado = (Apostador) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime()+Long.parseLong(expiration));

        return Jwts.builder().setIssuer("API do sistema de loteria")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValido(String token){
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public Long getIdApostador(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
