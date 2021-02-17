package br.com.loteria.repository;

import br.com.loteria.modelo.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {
}
