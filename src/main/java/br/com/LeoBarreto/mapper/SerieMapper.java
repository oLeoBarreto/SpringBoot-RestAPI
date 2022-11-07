package br.com.LeoBarreto.mapper;

import br.com.LeoBarreto.domain.Serie;
import br.com.LeoBarreto.request.SeriePostRequestBody;
import br.com.LeoBarreto.request.SeriePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class SerieMapper {
    public static final SerieMapper INSTANCE = Mappers.getMapper(SerieMapper.class);
    public abstract Serie toSerie(SeriePostRequestBody seriePostRequestBody);
    public abstract Serie toSerie(SeriePutRequestBody seriePutRequestBody);
}
