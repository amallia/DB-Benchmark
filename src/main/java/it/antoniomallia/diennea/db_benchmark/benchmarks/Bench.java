package it.antoniomallia.diennea.db_benchmark.benchmarks;

import lombok.AllArgsConstructor;

import com.zaxxer.hikari.HikariDataSource;

@AllArgsConstructor
public abstract class Bench  {

	protected HikariDataSource dataSource;
	public abstract void run(int runs);

}
