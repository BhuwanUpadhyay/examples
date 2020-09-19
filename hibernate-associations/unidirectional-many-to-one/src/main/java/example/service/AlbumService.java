package example.service;

import example.entity.Singer;
import example.entity.Song;
import example.repository.SingerRepository;
import example.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor public class AlbumService {

	public static final String NAME = "Bandana Poudyal";
	private final SingerRepository singerRepository;
	private final SongRepository songRepository;

	private void createSinger() {

		Singer singer = new Singer();

		singer.setName(NAME);
		singer.setAge(26);
		singer.setCountry("Nepal");

		singerRepository.save(singer);
	}

	public void addSongsAndItsSinger() {
		this.createSinger();

		Singer singer = singerRepository.findByName(NAME);

		Song song = new Song();
		song.setGenre("Romantic");
		song.setTitle("Lal Isqh");
		song.setSinger(singer);
		songRepository.save(song);
	}

	@Transactional public void deleteFirstSongOfSinger() {
		Singer singer = singerRepository.findByName(NAME);
		Song song = songRepository.findAllBySinger(singer).get(0);
		songRepository.delete(song);
	}

	@Transactional public void deleteAllSongsOfSinger() {
		Singer singer = singerRepository.findByName(NAME);
		songRepository.deleteAllBySinger(singer);
	}
}
