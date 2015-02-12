package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class EstablishConnection {
	String url = "jdbc:mysql://localhost:3306/airline_schema";
	String uname = "root";
	String pwd = "root";

	Queue<Connection> pool = new LinkedList<Connection>();
	private static final int MAXPOOLSIZE = 30;
	private static final int MINPOOLSIZE = 40;
	public static boolean onConnectionPool = false;
	public static boolean created = false;

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, uname, pwd);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return conn;

	}

	public void endConnection(Connection conn) {
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createPool() {
		if (onConnectionPool == true) {
			if (!created) {
				System.out.println("server creating pool");

				for (int i = 0; i < MINPOOLSIZE; i++) {
					pool.add(createPoolConnection());

				}

				created = true;
			}

		}
	}

	public Connection getConnection1() {
		if (onConnectionPool) {

			return getConnectionFromQueue();
		}
		return getConnectionToDatabase();
	}

	private Connection createPoolConnection() {
		Connection conn = null;

		while (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						url + "?cachePrepStmts=true", uname, pwd);

			} catch (Exception e) {

				// e.printStackTrace();

				if (conn == null) {
					System.out
							.println("Could not get connection...Trying again");
				}

			}
		}
		try {
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Connection getConnectionFromQueue() {
		Connection conn = null;
		synchronized (pool) {
			if (pool.isEmpty() == false)
				conn = pool.poll();
			else {
				if (pool.size() < MAXPOOLSIZE) {
					pool.add(getConnection());
					conn = pool.poll();
				} else {
					while (conn == null) {
						System.out.println("waiting for connection");

						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						conn = pool.poll();
					}
				}
			}
		}
		return conn;
	}

	public void endConnection1(Connection conn) {
		if (onConnectionPool)
			endConnectioninPool(conn);
		else {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void endConnectioninPool(Connection conn) {
		synchronized (pool) {
			pool.add(conn);
		}

	}

	public void releasePool() {
		for (int i = 0; i < pool.size(); i++) {
			Connection conn = pool.poll();
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnectionToDatabase() {
		Connection conn = null;
		while (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, uname, pwd);
			} catch (Exception e) {
				if (conn == null) {
					System.out
							.println("Could not get connection...Trying again");
				}
			}
		}
		try {
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
