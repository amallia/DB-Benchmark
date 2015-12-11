package it.antoniomallia.diennea.db_benchmark.db;

import java.util.Properties;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import com.google.common.io.Resources;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Slf4j
public class ConnectionPool {
	@Getter
	private HikariDataSource dataSource = null;

	public ConnectionPool() {
		log.info("Initializing the datasource...");
		try {
			Properties properties = new Properties();
			properties.load(Resources.getResource("db.properties").openStream());
			HikariConfig config = new HikariConfig(properties);
			dataSource = new HikariDataSource(config);
			log.info("Datasource initialized correctly.");
		} catch (Exception e) {
			log.error("Error initializing the datasource", e);
		}
	}
}
