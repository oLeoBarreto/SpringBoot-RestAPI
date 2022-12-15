package br.com.LeoBarreto.repository;

import br.com.LeoBarreto.domain.Serie;
import br.com.LeoBarreto.mapper.repository.SerieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for the Serie Repository")
class SerieRepositoryTest {

    @Autowired
    private SerieRepository serieRepository;

    @Test
    @DisplayName("Save persists series when succesful")
    public void Save_PersistsSerie_WhenSuccesful() {
        Serie serieToBeSaved = CreateSerie();
        Serie serieSaved = this.serieRepository.save(serieToBeSaved);

        Assertions.assertThat(serieSaved).isNotNull();
        Assertions.assertThat(serieSaved.getId()).isNotNull();
        Assertions.assertThat(serieSaved.getName()).isEqualTo(serieToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates series when succesful")
    public void Save_UpdatesSerie_WhenSuccesful() {
        Serie serieToBeSaved = CreateSerie();
        Serie serieSaved = this.serieRepository.save(serieToBeSaved);

        serieSaved.setName("Dark");

        Serie serieUpdated = this.serieRepository.save(serieSaved);

        Assertions.assertThat(serieUpdated).isNotNull();
        Assertions.assertThat(serieUpdated.getId()).isNotNull();
        Assertions.assertThat(serieUpdated.getName()).isEqualTo(serieSaved.getName());
    }

    @Test
    @DisplayName("Save delete series when succesful")
    public void Save_RemoveSerie_WhenSuccesful() {
        Serie serieToBeSaved = CreateSerie();
        Serie serieSaved = this.serieRepository.save(serieToBeSaved);

       this.serieRepository.delete(serieSaved);
        Optional<Serie> serieOptional = this.serieRepository.findById(serieSaved.getId());

        Assertions.assertThat(serieOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name return a list of serie when succesful")
    public void FindByName_ReturnListOfSerie_WhenSuccesful() {
        Serie serieToBeSaved = CreateSerie();
        Serie serieSaved = this.serieRepository.save(serieToBeSaved);

        List<Serie> listOfSeries = this.serieRepository.findByName(serieSaved.getName());

        Assertions.assertThat(listOfSeries).isNotEmpty().contains(serieSaved);
    }

    @Test
    @DisplayName("Find by name return empty list of serie when no serie is found")
    public void FindByName_ReturnEmptyList_WhenNoSeieIsFound() {
        List<Serie> listOfSeries = this.serieRepository.findByName("Not Exists");

        Assertions.assertThat(listOfSeries).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is Empty")
    public void Save_ThrowConstraintViolationException_WhenNameIsEmpty() {
        Serie serie = new Serie();
        Assertions.assertThatThrownBy(() -> this.serieRepository.save(serie))
                .isInstanceOf(ConstraintViolationException.class);
    }

    private Serie CreateSerie() {
        return Serie.builder()
                .name("The Walking Dead")
                .build();
    }
}