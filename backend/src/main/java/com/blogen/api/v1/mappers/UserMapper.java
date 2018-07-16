package com.blogen.api.v1.mappers;

import com.blogen.api.v1.model.UserDTO;
import com.blogen.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct mappers for mapping between {@link com.blogen.domain.User} and {@link com.blogen.api.v1.model.UserDTO}
 *
 * @author Cliff
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface UserMapper {
    //NullValueCheckStrategy.ALWAYS ensures that source properties that are NULL, don't get mapped
    // onto target properties

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping( target = "avatarImage", source = "userPrefs.avatarImage")
    UserDTO userToUserDto( User user );

    @Mapping( target = "userPrefs.avatarImage", source = "avatarImage")
    User userDtoToUser( UserDTO userDTO );
    
    @Mapping( source = "avatarImage", target = "userPrefs.avatarImage")
    void updateUserFromDTO( UserDTO userDTO, @MappingTarget User user );
}
