package br.com.loteria.seguranca;

import br.com.loteria.repository.ApostadorRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private ApostadorRepository apostadorRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, ApostadorRepository apostadorRepository){
        this.tokenService = tokenService;
        this.apostadorRepository = apostadorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(httpServletRequest);
        boolean valido = tokenService.isTokenValido(token);
        if (valido){
            autenticaCliente(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticaCliente(String token){
        Long idApostador = tokenService.getIdApostador(token);
        Apostador apostador = apostadorRepository.findbyId(idApostador).get();
        UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(apostador, null, apostador.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

    private  String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
