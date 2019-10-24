package com.martel.proto;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebFlux
@SpringBootApplication
public class ReactiveApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("ReactiveApplication.run()");
//		log.info("Seeding data!");
//		Flux<String>   names     = Flux  .just("raj", "david", "pam")
//													.delayElements(Duration.ofSeconds(1));
//		Flux<Integer>  ages      = Flux  .just(25, 27, 30)
//													.delayElements(Duration.ofSeconds(1));
//		Flux<Customer> customers = Flux  .zip(names, ages)
//													.map(tupple -> {
//														return new Customer(null, tupple.getT1(), tupple.getT2());
//													});
//		repo  .deleteAll()
//				.thenMany(customers  .flatMap(c -> repo.save(c))
//											.thenMany(repo.findAll()))
//				.subscribe(System.out::println);
	}
}