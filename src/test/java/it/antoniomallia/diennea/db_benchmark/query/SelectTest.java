package it.antoniomallia.diennea.db_benchmark.query;

import static org.junit.Assert.assertEquals;
import it.antoniomallia.diennea.db_benchmark.db.Select;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class SelectTest {

	@Test
	public void selectAllTest() {
		String expected = "SELECT * FROM test;";
		String actual = new Select("test").toString();
		assertEquals(expected, actual);
	}

	@Test
	public void selectWhereSingleTest() {
		String expected = "SELECT * FROM test WHERE a=1;";
		Pair<String, String> condition = Pair.of("a", "1");
		String actual = new Select("test", condition).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void selectColumnSingleTest() {
		String expected = "SELECT column1 FROM test;";
		List<String> column = ImmutableList.of("column1");
		String actual = new Select("test", column).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void selectColumnMultipleTest() {
		String expected = "SELECT column1, column2 FROM test;";
		List<String> columns = ImmutableList.of("column1", "column2");
		String actual = new Select("test", columns).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void selectColumnConditionTest() {
		String expected = "SELECT column1, column2 FROM test WHERE a=1;";
		List<String> columns = ImmutableList.of("column1", "column2");
		Pair<String, String> condition = Pair.of("a", "1");
		String actual = new Select("test", columns, condition).toString();
		assertEquals(expected, actual);
	}
}
