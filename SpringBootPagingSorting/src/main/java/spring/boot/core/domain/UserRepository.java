package spring.boot.core.domain;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
