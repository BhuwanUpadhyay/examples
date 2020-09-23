package example.repository;

import example.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SingerRepository extends JpaRepository<Singer, Long> {
  Singer findByName(String name);
}
