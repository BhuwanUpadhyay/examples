package example.repository;

import example.entity.Singer;
import example.entity.Song;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

  List<Song> findAllBySinger(Singer singer);

  void deleteAllBySinger(Singer singer);
}
