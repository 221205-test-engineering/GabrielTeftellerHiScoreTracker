package daos;

import models.Score;
import utilities.ConnectionUtility;

import java.util.List;

public class ScoreDAO
{
	private static ConnectionUtility cu = ConnectionUtility.getConnectionUtil();

	// Create
	public Score postNewScore()
	{
		return null;
	}

	// Read
	public List<Score> getAllScores()	// must also handle queries for initials
	{
		return null;
	}

	public Score getScoreById(int id)
	{
		return null;
	}

	// Update
	public Score replaceScoreById(int id)
	{
		return null;
	}

	// Delete
	public Score deleteById(int id)
	{
		return null;
	}
}
