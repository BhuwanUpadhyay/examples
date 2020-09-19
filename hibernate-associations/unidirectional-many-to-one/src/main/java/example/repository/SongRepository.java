package example.repository;

import example.entity.Singer;
import example.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllBySinger(Singer singer);

    void deleteAllBySinger(Singer singer);
}
