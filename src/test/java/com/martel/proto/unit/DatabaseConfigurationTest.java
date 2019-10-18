package com.martel.proto.unit;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.function.connectionfactory.ConnectionFactoryUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.martel.proto.category.UnitTests;

import io.r2dbc.spi.ConnectionFactory;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class DatabaseConfigurationTest {

	@Autowired
	private ConnectionFactory connectionFactory;

	@Test
	public void connect_database_Success() throws SQLException {

		ConnectionFactoryUtils  .getConnection(connectionFactory)
										.as(StepVerifier::create)
										.expectNextCount(1)
										.verifyComplete();
	}
}