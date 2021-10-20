package com.praisesystem.backend.common.mappers;

import com.praisesystem.backend.common.mappers.support.CollectionSize;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    @CollectionSize
    static <T> Long collectionSize(Collection<T> collection) {
        return (long) collection.size();
    }
}
