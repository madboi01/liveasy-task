package tech.springboot.liveasytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.springboot.liveasytask.model.Payload;

import java.util.List;

@Repository
public interface PayloadRepo extends JpaRepository<Payload,Long> {

    List<Payload> findByShipperId(String id);
}
