package Models;

public class Book {

    private final Integer id;

    private String title;

    private Integer year;

    private String authorOfBook;

    private boolean isAvalible;

    private String lendersName;

    public Book(Integer id, String title, Integer year, String author) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.authorOfBook = author;
        this.isAvalible=true;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getAuthorOfBook() {
        return authorOfBook;
    }


    public boolean isAvalible() {
        return isAvalible;
    }

    public void setAvalible(boolean avalible) {
        isAvalible = avalible;
    }

    public String getLendersName() {
        return lendersName;
    }

    public void setLendersName(String lendersName) {
        this.lendersName = lendersName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", authorOfBook='" + authorOfBook + '\'' +
                ", isAvalible=" + isAvalible +
                ", lendersName='" + lendersName + '\'' +
                '}';
    }
}
