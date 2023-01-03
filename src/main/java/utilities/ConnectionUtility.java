package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {

	private static ConnectionUtility connectionUtil;

	private ConnectionUtility() {}

	public static ConnectionUtility getConnectionUtil() {
		if (connectionUtil == null) {
			return new ConnectionUtility();
		}
		return connectionUtil;
	}
	public Connection getConnection() {
		Connection conn = null;

		String url = System.getenv("URL");
		String username = System.getenv("USER");
		String password = System.getenv("PASSWORD");

		try
		{
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
