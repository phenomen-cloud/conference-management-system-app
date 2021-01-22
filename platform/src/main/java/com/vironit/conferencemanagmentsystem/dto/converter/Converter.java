package com.vironit.conferencemanagmentsystem.dto.converter;

import java.util.List;

public interface Converter <Dto, Entity> {
    Dto entityToDto(Entity entity);
    List<Dto> entityToDto(List<Entity> entities);
    Entity dtoToEntity(Dto dto);
    List<Entity> dtoToEntity(List<Dto> dtos);
}
