package models;

public class Score
{
    private int id;
    private String initials;
    private int points;

    public Score() {}

    public Score(int id, String initials, int points)
    {
        this.id = id;
        this.initials = initials;
        this.points = points;
    }

    public Score(String initials, int points)
    {
        this.initials = initials;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", initials='" + initials + '\'' +
                ", points=" + points +
                '}';
    }
}
