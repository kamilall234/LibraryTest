package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    public static Library library;
    private Integer generatedIdByLibrary; //Even deleted books didn't return own Id to possible Ids

    private Map<Integer,Book> bookList;

    public Library() {
        this.bookList = new HashMap<>();
        this.generatedIdByLibrary = new Integer(0);
    }
    //Singleton
    public static Library getLibrary() {
        return library==null ? library=new Library() : library;
    }

    public void addNewBook(String name, Integer year, String author){
        Book book = new Book(generatedIdByLibrary, name, year, author);
        this.bookList.put(book.getId(),book);
        ++generatedIdByLibrary; //Increase Id, only unique ID can be add. Deleted book doesn't return Id to possible next generated Id
    }

    public void removeBookById(Integer id){
        this.bookList.remove(id);
    }

    public Integer getNextId(){
        return generatedIdByLibrary;
    }

    public boolean isLibraryContainBook(Integer id){
        return this.bookList.containsKey(id) ? true : false;
    }

    public Book getBookById(Integer id){
        return this.bookList.get(id);
    }

    public List<Book> getBookList(){
        return new ArrayList<>(bookList.values());
    }

}
