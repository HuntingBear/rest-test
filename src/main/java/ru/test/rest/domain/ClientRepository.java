package ru.test.rest.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    @RestResource(exported = false)
    @Override
    <S extends Client> S save(S s);

}
