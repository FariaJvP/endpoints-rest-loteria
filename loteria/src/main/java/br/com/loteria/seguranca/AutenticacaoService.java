package br.com.loteria.seguranca;

import br.com.loteria.modelo.Apostador;
import br.com.loteria.repository.ApostadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private ApostadorRepository apostadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Apostador> apostador = apostadorRepository.findByEmail(username);
        if(apostador.isPresent()){
            return apostador.get();
        }
        throw new UsernameNotFoundException("dados invalidos");
    }
}
