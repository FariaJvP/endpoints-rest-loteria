package br.com.loteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long> {

    Optional<Apostador> findByEmail(String username);

}
