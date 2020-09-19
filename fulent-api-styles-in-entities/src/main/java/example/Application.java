package example;

import example.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication @RequiredArgsConstructor @Slf4j public class Application {

	private final AlbumService albumService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener public void onReady(ApplicationReadyEvent event) {
		mark();
		log.info("Create singer with songs");
		mark();
		albumService.addSongsAndItsSinger();
		mark();

		log.info("Delete a song of a singer");
		mark();
		albumService.deleteFirstSongOfSinger();
		mark();

		log.info("Delete all songs of a singer");
		mark();
		albumService.deleteAllSongsOfSinger();
		mark();

	}

	private void mark() {
		log.info("------------------------");
	}
}
