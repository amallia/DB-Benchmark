package it.antoniomallia.diennea.db_benchmark.db;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Joiner;

public class Select extends Query {

	private List<String> columns;
	private Pair<String, String> condition;

	public Select(String table) {
		this.table = table;
	}

	public Select(String table, List<String> columns,
			Pair<String, String> condition) {
		this(table);
		this.columns = columns;
		this.condition = condition;
	}

	public Select(String table, Pair<String, String> condition) {
		this(table, null, condition);
	}

	public Select(String table, List<String> columns) {
		this(table, columns, null);
	}

	@Override
	protected String build() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		if (Optional.ofNullable(columns).isPresent()) {
			sb.append(Joiner.on(", ").join(columns));
		} else {
			sb.append("*");
		}
		sb.append(" FROM ").append(table);
		if (Optional.ofNullable(condition).isPresent()) {
			sb.append(" WHERE ");
			sb.append(condition.getLeft()).append("=").append(condition.getRight());
		}
		sb.append(";");
		return sb.toString();
	}

	@Override
	public String toString() {
		return build();
	}
}
