package it.antoniomallia.diennea.db_benchmark.benchmarks;

import it.antoniomallia.diennea.db_benchmark.db.ConnectionPool;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.zaxxer.hikari.HikariDataSource;

@Slf4j
public class TestExecutor {
	private static final List<Class<? extends Bench>> benchTypes = Arrays
			.asList(InsertBench.class, InsertManualCommitBench.class, SelectBench.class);

	public static void main(String[] args) {
		log.info("============= Benchmarks Started ===============");
		HikariDataSource ds = new ConnectionPool().getDataSource();
		try {
			Bench bench;
			for (Class<? extends Bench> benchClass : benchTypes) {
				bench = benchClass.getDeclaredConstructor(
						HikariDataSource.class).newInstance(ds);
				bench.run(1000);
			}
			ds.close();
			log.info("============= Benchmarks Completed ===============");
		} catch (Exception e) {
			log.info("Impossible to execute the benhmark", e);
		}
	}

}
