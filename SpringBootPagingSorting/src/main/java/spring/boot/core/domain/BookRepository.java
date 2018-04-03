package spring.boot.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book,Long> 
        ,JpaSpecificationExecutor<Book> {
}
