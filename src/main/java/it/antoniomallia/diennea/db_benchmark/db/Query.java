package it.antoniomallia.diennea.db_benchmark.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Query {

	String table;

	protected abstract String build();

	public PreparedStatement getStatement(Connection connection)
			throws SQLException {
		return connection.prepareStatement(build());
	}
}
