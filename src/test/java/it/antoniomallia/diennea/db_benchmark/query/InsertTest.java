package it.antoniomallia.diennea.db_benchmark.query;

import static org.junit.Assert.assertEquals;
import it.antoniomallia.diennea.db_benchmark.db.Insert;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class InsertTest {

	
	@Test
	public void insertSingleTest() {
		String expected = "INSERT INTO table (column1) VALUES ('value1');";
		String actual = new Insert("table", ImmutableMap.of("column1", "value1")).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void insertMultipleTest() {
		String expected = "INSERT INTO table (column1, column2) VALUES ('value1', 'value2');";
		String actual = new Insert("table", ImmutableMap.of("column1", "value1", "column2", "value2")).toString();
		assertEquals(expected, actual);
	}
}
