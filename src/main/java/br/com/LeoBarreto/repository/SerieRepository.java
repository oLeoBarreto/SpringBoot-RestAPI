package br.com.LeoBarreto.repository;

import br.com.LeoBarreto.domain.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    List<Serie> findByName(String name);
}
