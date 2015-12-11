package it.antoniomallia.diennea.db_benchmark.db;

import java.util.Map;

import com.google.common.base.Joiner;

public class Insert extends Query {

	private Map<String, String> columnValues;

	public Insert(String table, Map<String, String> columnValues) {
		this.table = table;
		this.columnValues = columnValues;
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(table).append(" (")
				.append(Joiner.on(", ").join(columnValues.keySet()))
				.append(") VALUES ('")
				.append(Joiner.on("', '").join(columnValues.values()))
				.append("');");
		return sb.toString();
	}

	@Override
	public String toString() {
		return build();
	}

}
