package br.com.LeoBarreto.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "This is the serie ID", example = "1")
    private Long id;

    @NotEmpty(message = "The field name cannot be empty")
    @Schema(description = "This is the serie title", example = "Stranger Things")
    private String name;
}
