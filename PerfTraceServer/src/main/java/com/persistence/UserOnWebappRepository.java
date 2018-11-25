package com.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
public interface UserOnWebappRepository extends CrudRepository<UserOnWebapp, Long>{
   Optional<UserOnWebapp> findBySessionId(String userSessionId);

   List<UserOnWebapp> findByWebapp(Webapp webapp);
}
