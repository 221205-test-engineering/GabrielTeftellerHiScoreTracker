package app;

import daos.ScoreDAO;
import models.Score;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiScoreApp
{
    public static void main(String[] args)
    {
        Javalin app = Javalin.create();

        Map<Integer, Score> scoreMap = new HashMap<>();

        // create a new score
        // TODO: implement http responses
        app.post("/scores", ctx ->
        {
            ScoreDAO dao = new ScoreDAO();
            Score newScore = ctx.bodyAsClass(Score.class);
            dao.postNewScore(newScore);
        });

        // return all scores; returns score by initial if query is included in request
        app.get("/scores", ctx ->
        {
            ScoreDAO dao = new ScoreDAO();
            String searchInitials = ctx.queryParam("initials");
            List<Score> scores;
            if (searchInitials != null)
            {
                scores = dao.getAllScores(searchInitials);
            }
            else
            {
                scores = dao.getAllScores();
            }

            ctx.status(200);
            ctx.json(scores);
        });

        // returns score by specified id; Returns 404 if score with ID not found
        app.get("/scores/{id}", ctx ->
        {
            ScoreDAO dao = new ScoreDAO();
            int scoreId = Integer.parseInt(ctx.pathParam("id"));
            Score score = dao.getScoreById(scoreId);

            if (score == null)
            {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result("No score with ID " + scoreId + " found.");
            }
            else
            {
                ctx.status(200);
                ctx.json(score);
            }
        });

        // replaces score with specified id; Returns 404 if score with ID not found
        // TODO: implement http responses
        app.put("/scores/{id}", ctx ->
        {
            int scoreId = Integer.parseInt(ctx.pathParam("id"));
            ScoreDAO dao = new ScoreDAO();
            Score newScore = ctx.bodyAsClass(Score.class);

            dao.replaceScoreById(scoreId, newScore);
            ctx.result("Score with ID " + scoreId + " successfully replaced.");
        });

        // deletes score with specified id; Returns 404 if score with ID not found
        // TODO: implement http responses
        app.delete("/scores/{id}", ctx ->
        {
            ScoreDAO dao = new ScoreDAO();
            int scoreId = Integer.parseInt(ctx.pathParam("id"));

            dao.deleteById(scoreId);
            ctx.result("Score with ID " + scoreId + " successfully deleted.");
        });

        app.start(8080);
    }
}
