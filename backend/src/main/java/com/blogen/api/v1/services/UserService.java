package com.blogen.api.v1.services;

import com.blogen.api.v1.controllers.UserController;
import com.blogen.api.v1.model.UserDTO;
import com.blogen.api.v1.model.UserListDTO;
import com.blogen.domain.User;

import java.util.Optional;

/**
 * REST operations on {@link com.blogen.domain.User}
 *
 * @author Cliff
 */
public interface UserService {

    UserListDTO getAllUsers();

    UserDTO getUser( Long id );

    UserDTO updateUser( Long id, UserDTO userDTO );

    Optional<User> findByUserName( String name );

    User saveUser( User user );

    //delete user not supported in this version

    /**
     * build the userUrl that gets returned with every response DTO
     * @param user
     * @return
     */
    default String buildUserUrl( User user ) {
        return UserController.BASE_URL + "/" + user.getId();
    }

}
