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

		Song song = new Song();
		song.setGenre("Romantic");
		song.setTitle("Lal Isqh");

		singer.addSong(song);

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
