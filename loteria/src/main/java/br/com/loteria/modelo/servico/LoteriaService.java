package br.com.loteria.modelo.servico;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoteriaService {

    //configuracao atual dos valores
    Integer valor = 100; //gera um numero de 1 a 100
    Integer quantidadeDeNumerosParaSortear = 3; //gera 3 aleatórios

    private Integer geraNumero(){
        return (1+(int)(Math.random() * valor));
    }

    public List<Integer> geraNumerosAletatorios(){
        List<Integer> listaDeNumeros = new ArrayList<>();

        while (listaDeNumeros.size() < quantidadeDeNumerosParaSortear){
            Integer numeroSorteado = geraNumero();
            if(!listaDeNumeros.contains(numeroSorteado)){
                listaDeNumeros.add(numeroSorteado);
            }
        }
        return listaDeNumeros;
    }
}
