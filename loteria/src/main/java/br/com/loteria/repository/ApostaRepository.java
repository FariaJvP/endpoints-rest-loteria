package br.com.loteria.repository;

import br.com.loteria.modelo.Aposta;
import br.com.loteria.modelo.Apostador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {

    Page<Aposta> findApostaByApostadorId(Apostador apostador, Pageable pageable);

    @Query("SELECT a FROM Aposta a WHERE a.id=:id AND a.apostadorId=:apostador")
    Optional<Aposta> FindApostaIdByApostador(Long id, Apostador apostador);
}
