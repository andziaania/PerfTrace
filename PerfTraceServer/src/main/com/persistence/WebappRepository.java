package com.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author ania.pawelczyk
 * @since 14.11.2018.
 */
interface WebappRepository extends CrudRepository<Webapp, Long> {
  List<Webapp> findByUrl(String url);
}
