package it.antoniomallia.diennea.db_benchmark.benchmarks;

import it.antoniomallia.diennea.db_benchmark.db.Insert;
import it.antoniomallia.diennea.db_benchmark.utils.RandomEmail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariDataSource;

@Slf4j
public class InsertBench extends Bench {

	public InsertBench(HikariDataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void run(int runs) {
		try {
			Connection connection = dataSource.getConnection();
			long min = Long.MAX_VALUE;
			Pair<Long, Long> total = Pair.of(0l, 0l);
			long max = Long.MIN_VALUE;
			long start, elapsed = 0;
			for (int i = 0; i < runs; i++) {
				Map<String, String> values = ImmutableMap.of("email",
						RandomEmail.generateEmailAddress());

				PreparedStatement insert = new Insert("emails", values)
						.getStatement(connection);
				start = System.nanoTime();
				insert.execute();
				elapsed = (System.nanoTime() - start)/1000;
				min = Math.min(elapsed, min);
				max = Math.max(elapsed, max);
				total = Pair
						.of(total.getLeft() + elapsed, total.getRight() + 1);
			}
			connection.close();
			log.info(
					"N. of Insert executed: {} Total Excution Time: {} us, Mean per query: {} us, Max: {} us, Min: {} us",
					total.getRight(), total.getLeft(),
					total.getLeft() / total.getRight(), max, min);
		} catch (Exception e) {
			log.error("An error occured while executing Insert Benchmark", e);
		}

	}
}
