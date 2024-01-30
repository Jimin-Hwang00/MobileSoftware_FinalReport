package ddwu.mobile.finalreport_02_20201036;

import java.io.Serializable;

public class Movie implements Serializable{

    long _id;
    int poster;
    String title;
    String director;
    String date;
    String company;

    public Movie() {
        _id = -1;
        poster = -1;
        title = null;
        director = null;
        date = null;
        company = null;
    }

    public Movie(long _id, int poster, String title, String director, String date, String company) {
        this._id = _id;
        this.poster = poster;
        this.title = title;
        this.director = director;
        this.date = date;
        this.company = company;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_id);
        sb.append(poster);
        sb.append(title);
        sb.append(director);
        sb.append(date);
        sb.append(company);

        return sb.toString();
    }
}
