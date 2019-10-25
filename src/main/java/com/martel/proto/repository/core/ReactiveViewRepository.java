package com.martel.proto.repository.core;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface ReactiveViewRepository<T, ID> extends Repository<T, ID> {

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id must not be {@literal null}.
	 * @return {@link Mono} emitting the entity with the given id or {@link Mono#empty()} if none found.
	 * @throws IllegalArgumentException in case the given {@code id} is {@literal null}.
	 */
	Mono<T> findById(ID id);

	/**
	 * Returns all instances of the type.
	 *
	 * @return {@link Flux} emitting all entities.
	 */
	Flux<T> findAll();
}