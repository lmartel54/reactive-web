package com.martel.proto.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories("com.martel.proto.repository")
public class DatabaseConfig extends AbstractR2dbcConfiguration {

	@Value("${spring.data.r2dbc.applicationName}")
	private String applicationName;

	@Value("${spring.data.r2dbc.connectTimeout}")
	private int connectTimeout;

	@Value("${spring.data.r2dbc.database}")
	private String database;

	@Value("${spring.data.r2dbc.host}")
	private String host;

	@Value("${spring.data.r2dbc.port}")
	private int port;

	@Value("${spring.data.r2dbc.username}")
	private String username;

	@Value("${spring.data.r2dbc.password}")
	private String password;

	@Bean
	public ConnectionFactory connectionFactory() {

//		Map<String, String> options = new HashMap<>();
//		options.put("lock_timeout", "10s");
//		options.put("statement_timeout", "60000"); // [ms]

		return new PostgresqlConnectionFactory(
		PostgresqlConnectionConfiguration.builder()
													.applicationName(applicationName)
													.connectTimeout(Duration.ofMillis(connectTimeout))
													.database(database)
													.host(host)
//											      .options(options)
													.port(port)
													.username(username)
													.password(password)
//											      .schema("atrium").
//											      .sslMode(SSLMode.ALLOW)
													.build());

//		PostgresqlConnectionConfiguration.builder()
//		.applicationName("atrium-r2dbc")
//		.connectTimeout(Duration.ofMillis(1000))
//		.database("atrium-test")
//		.host("localhost")
////      .options(options)
//		.port(5432)
//		.username("admin")
//		.password("password")
////      .schema("atrium").
////      .sslMode(SSLMode.ALLOW)
//		.build());
	}

//	@Bean
//	@Override
//	public R2dbcCustomConversions r2dbcCustomConversions() {
//
////		List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
////		converters.add(new UUIDReadConverter());
////		converters.add(new UUIDWriteConverter());
////
//		Dialect dialect = getDialect(connectionFactory());
//		StoreConversions storeConversions = StoreConversions.of(dialect.getSimpleTypeHolder());
//
//		return new R2dbcCustomConversions(storeConversions, List.of(new UUIDReadConverter()/* , new UUIDWriteConverter() */));
//	}
//
//	@ReadingConverter
//	public class UUIDReadConverter implements Converter<Row, ExpenseGasView> {
//
//		@Override
//		public ExpenseGasView convert(Row source) {
//			System.out.println("######################################");
//			System.out.println("UUIDReadConverter.convert()");
//			System.out.println("######################################");
//			// TODO Auto-generated method stub
//			return null;
//		}

//		@Override
//		public UUID convert(String source) {
//			final UUID result = UUID.fromString(source);
//			System.out.println("UUIDReadConverter.convert() => " + result.toString());
//			return result;
//		}
//	}

//	@WritingConverter
//	public class UUIDWriteConverter implements Converter<UUID, Integer> {
//
//		@Override
//		public Integer convert(UUID source) {
//			
//			System.out.println("######################################");
//			System.out.println("UUIDWriteConverter.convert()");
//			System.out.println("######################################");
//			return null;
//		}
//
////		@Override
////		public String convert(UUID source) {
////			System.out.println("UUIDWriteConverter.convert() => " + source.toString());
////			return source.toString();
////		}
//	}
}