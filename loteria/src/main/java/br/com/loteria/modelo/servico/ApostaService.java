package br.com.loteria.modelo.servico;

import br.com.loteria.modelo.Aposta;
import br.com.loteria.modelo.Apostador;
import br.com.loteria.repository.ApostaRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApostaService {

    @Autowired
    private LoteriaService loteriaService;

    @Autowired
    private ApostaRepository apostaRepository;

    public Aposta realizaAposta(Apostador apostador){
        List<Integer> apostaGerada = loteriaService.geraNumerosAletatorios();
        String apostaToString = serializaApostaJson(apostaGerada);

        Aposta aposta = new Aposta(apostador, apostaToString);
        apostaRepository.save(aposta);
        return aposta;
    }

    private String serializaApostaJson(List<Integer> apostaGerada){
        Gson gson = new Gson();
        return gson.toJson(apostaGerada);
    }
}
