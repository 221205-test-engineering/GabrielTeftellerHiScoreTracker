package daos;

import models.Score;
import utilities.ConnectionUtility;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO
{
	private static ConnectionUtility conUtil = ConnectionUtility.getConnectionUtil();

	// Create
	public void postNewScore(Score score)
	{
		String sql = "insert into scores values (default, ?, ?);";

		try (Connection con = conUtil.getConnection())
		{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, score.getInitials());
			statement.setInt(2, score.getPoints());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Read
	public List<Score> getAllScores()
	{
		String sql = "select * from scores order by points desc;";
		List<Score> scores = new ArrayList<>();

		try (Connection con = conUtil.getConnection())
		{
			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet results = statement.executeQuery();

			while (results.next())
			{
				scores.add
					(
						new Score
							(
								results.getInt("id"),
								results.getString("initials"),
								results.getInt("points")
							)
					);
			}
			return scores;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Score> getAllScores(String initials) // handles queries for initials
	{
		String sql = "select * from scores where initials = ? order by points desc;";
		List<Score> scores = new ArrayList<>();

		try (Connection con = conUtil.getConnection())
		{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, initials);

			ResultSet results = statement.executeQuery();

			while (results.next())
			{
				scores.add
					(
						new Score
							(
								results.getInt("id"),
								results.getString("initials"),
								results.getInt("points")
							)
					);
			}
			return scores;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Score getScoreById(int id)
	{
		String sql = "select * from scores where id = ? order by points desc;";
		Score score = null;

		try (Connection con = conUtil.getConnection())
		{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet results = statement.executeQuery();

			if (results.next())
			{
				score = new Score
					(
							results.getInt("id"),
							results.getString("initials"),
							results.getInt("points")
					);
			}
			return score;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// Update
	public void replaceScoreById(int id, Score score)
	{
		String sql = "UPDATE scores SET initials = ?, points = ? WHERE id = ?;";

		try (Connection con = conUtil.getConnection())
		{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, score.getInitials());
			statement.setInt(2, score.getPoints());
			statement.setInt(3, id);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Delete
	public void deleteById(int id)
	{
		String sql = "delete from scores where id = ?;";

		try (Connection con = conUtil.getConnection())
		{
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
