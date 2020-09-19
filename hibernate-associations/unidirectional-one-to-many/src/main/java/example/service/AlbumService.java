package example.service;

import example.entity.Singer;
import example.entity.Song;
import example.repository.SingerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor public class AlbumService {

	public static final String NAME = "Bandana Poudyal";
	private final SingerRepository singerRepository;

	public void addSongsAndItsSinger() {

		Singer singer = new Singer();

		singer.setName(NAME);
		singer.setAge(26);
		singer.setCountry("Nepal");

		Song song1 = new Song();
		song1.setGenre("Romantic");
		song1.setTitle("Lal Isqh");

		Song song2 = new Song();
		song2.setGenre("Romantic");
		song2.setTitle("Lal Isqh 2");

		singer.addSong(song1);
		singer.addSong(song2);

		singerRepository.save(singer);
	}

	@Transactional public void deleteFirstSongOfSinger() {
		Singer singer = singerRepository.findByName(NAME);
		Song song = singer.getSongs().get(0);
		singer.removeSong(song);
	}

	@Transactional public void deleteAllSongsOfSinger() {
		Singer singer = singerRepository.findByName(NAME);
		singer.removeSongs();
	}
}
