package it.antoniomallia.diennea.db_benchmark.benchmarks;

import it.antoniomallia.diennea.db_benchmark.db.Select;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.zaxxer.hikari.HikariDataSource;

@Slf4j
public class SelectBench extends Bench {

	public SelectBench(HikariDataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void run(int runs) {
		try {
			Connection connection = dataSource.getConnection();
			ResultSet rs = new Select("emails", ImmutableList.of("COUNT(*)"))
					.getStatement(connection).executeQuery();
			rs.next();
			int num = rs.getInt(1);
			final SecureRandom random = new SecureRandom();
			long min = Long.MAX_VALUE;
			Pair<Long, Long> total = Pair.of(0l, 0l);
			long max = Long.MIN_VALUE;
			long start, elapsed = 0;
			for (int i = 0; i < runs; i++) {
				int id = random.nextInt(num);
				PreparedStatement select = new Select("emails", Pair.of("id",
						String.valueOf(id))).getStatement(connection);
				start = System.nanoTime();
				select.execute();
				elapsed = (System.nanoTime() - start) / 1000;
				max = Math.max(max, elapsed);
				min = Math.min(max, elapsed);
				total = Pair
						.of(total.getLeft() + elapsed, total.getRight() + 1);
			}
			connection.close();
			log.info(
					"N. of Select executed: {} Total Excution Time: {} us, Mean per query: {} us, Max: {} us, Min: {} us",
					total.getRight(), total.getLeft(),
					total.getLeft() / total.getRight(), max, min);
		} catch (Exception e) {
			log.error("An error occured while executing Select Benchmark", e);
		}

	}

}
