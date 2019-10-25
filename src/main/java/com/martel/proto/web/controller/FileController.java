package com.martel.proto.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/file")
class FileController {

//   @GetMapping("/{fileName}")
//   public Mono<Resource> getFile(@PathVariable String fileName) {
//       return fileRepository.findByName(fileName)
//                .map(name -> new FileSystemResource(name));
//   }

	@GetMapping(value = "/browse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Path> getAll() throws IOException {
		return Flux.fromStream(Files.walk(Paths.get("/home/dev/eclipse"), 1));
//		.interval(Duration.ofSeconds(1))
	}
}

//https://dzone.com/articles/event-streaming-using-spring-webflux