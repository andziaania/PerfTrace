package com.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
public interface UserOnWebappRepository extends CrudRepository<UserOnWebapp, Long>{
   List<UserOnWebapp> findByWebapp(Webapp webapp);

}
