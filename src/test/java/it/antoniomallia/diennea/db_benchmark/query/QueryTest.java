package it.antoniomallia.diennea.db_benchmark.query;
import static org.junit.Assert.assertEquals;
import it.antoniomallia.diennea.db_benchmark.db.Select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class QueryTest {

	@Test
	public void getStatementTest() throws SQLException {
	    Connection mockDbConnection = Mockito.mock(Connection.class);
	    PreparedStatement expected = mockDbConnection.prepareStatement("SELECT col FROM table WHERE a=1, b=2");
		assertEquals(expected, new Select("table").getStatement(mockDbConnection));
	}

}
