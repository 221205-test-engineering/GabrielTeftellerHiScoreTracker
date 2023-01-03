package app;

import models.Score;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiScoreApp
{
    public static int lastId = 0;

    public static void main(String[] args)
    {
        Javalin app = Javalin.create();

        Map<Integer, Score> scoreMap = new HashMap<>();

        // create a new score
        // TODO: redirect to DAO
        app.post("/scores", ctx ->
        {
            Score newScore = ctx.bodyAsClass(Score.class); // Unmarshalling
            newScore.setId(lastId++);

            scoreMap.put(newScore.getId(), newScore);

            ctx.status(HttpStatus.CREATED);

            ctx.json(newScore);
        });

        // return all scores; returns score by initial if query is included in request
        // TODO: redirect to DAO
        app.get("/scores", ctx ->
        {
            String searchInitials = ctx.queryParam("initials");
            if (searchInitials != null)
            {
                String initials;

                for (int id = 0; id < scoreMap.size(); ++id)
                {
                    initials = scoreMap.get(id).getInitials();
                    if (initials.equals(searchInitials))
                    {
                        ctx.json(scoreMap.get(id));
                    }
                }
            }
            else
            {
                List<Score> scores = new ArrayList<>();
                for (Score score : scoreMap.values()) {
                    scores.add(score);
                }
                ctx.json(scores);
            }


        });

        // returns score by specified id; Returns 404 if score with ID not found
        // TODO: redirect to DAO
        app.get("/scores/{id}", ctx ->
        {
            int scoreId = Integer.parseInt(ctx.pathParam("id"));
            Score score = scoreMap.get(scoreId);

            if (score == null)
            {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result("No score with ID " + scoreId + " found.");
            }
            else
            {
                ctx.json(score);
            }
        });

        // replaces score with specified id; Returns 404 if score with ID not found
        // TODO: redirect to DAO
        app.put("/scores/{id}", ctx ->
        {
            int scoreId = Integer.parseInt(ctx.pathParam("id"));
            Score score = scoreMap.get(scoreId);

            if (score == null)
            {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result("No score with ID " + scoreId + " found.");
            }
            else
            {
                Score newScore = ctx.bodyAsClass(Score.class); // Unmarshalling
                newScore.setId(score.getId());
                scoreMap.replace(scoreId, newScore);
                ctx.json(newScore);
            }
        });

        // deletes score with specified id; Returns 404 if score with ID not found
        // TODO: redirect to DAO
        app.delete("/scores/{id}", ctx ->
        {
            int scoreId = Integer.parseInt(ctx.pathParam("id"));
            Score score = scoreMap.get(scoreId);

            if (score == null)
            {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result("No score with ID " + scoreId + " found.");
            }
            else
            {
                scoreMap.remove(scoreId);
                ctx.result("Score with ID " + scoreId + " successfully deleted.");
            }
        });

        app.start(8080);
    }
}
