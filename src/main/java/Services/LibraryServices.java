package Services;

import Models.Book;
import Models.Library;

import java.io.BufferedReader;
import java.util.Comparator;
import java.util.Scanner;

public class LibraryServices {

    private final Scanner sc = new Scanner(System.in);

    public LibraryServices() {
        Integer i = -1;
        while (i!=0){
            System.out.println("Choose what you would like to do: ");
            System.out.println("0 - exit from applicaiton");
            System.out.println("1 - add new book to Library");
            System.out.println("2 - remove book by Id from library");
            System.out.println("3 - get List of Books in Library");
            System.out.println("4 - lent book");
            System.out.println("5 - search book");
            System.out.println("6 - details of book");
            i = sc.nextInt();

            switch (i){
                case 1:
                    addNewBookToLibrary();
                    break;
                case 2:
                    removeBookFromLibrary();
                    break;
                case 3:
                    getListAllofBooksInLibrary();
                    break;
                case 4:
                    lentBookById();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    getDetailsodBookById();
                    break;
                case 0:
                    System.out.println("End of Library Application");
                    break;
                default:
                    System.out.println("No Option for this choose");
            }
        }
    }

    private void addNewBookToLibrary(){
        System.out.println("Type Name, Year and Author of Book");
        System.out.println("Name : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Year: ");
        Integer year = sc.nextInt();
        System.out.println("Author: ");
        sc.nextLine();
        String author = sc.nextLine();
        Library.getLibrary().addNewBook(name, year, author);
    }

    private void removeBookFromLibrary(){
        Integer id = inputBookId();
        if(Library.getLibrary().isLibraryContainBook(id)){
            if(Library.getLibrary().getBookById(id).isAvalible()){
                Library.getLibrary().removeBookById(id);
                System.out.println("Book deleted from Library");
            }else {
                System.out.println("Is not possbile to remove Library with Id: "+id+" of reason the book is currently lent");
            }
        }else {
            System.out.println("Library doesn't contain book with Id "+id);
        }
    }

    private void getListAllofBooksInLibrary(){
        Library.getLibrary().getBookList().stream().sorted(Comparator.comparing(Book::getId)).forEach(System.out::println);
        System.out.println("Amount of availible to lent: "+ Library.getLibrary().getBookList().stream().filter(Book::isAvalible).count());
        System.out.println("Amount of lent: "+ Library.getLibrary().getBookList().stream().filter(book -> !book.isAvalible()).count());
    }

    private void lentBookById(){
        Integer id = inputBookId();
        if(Library.getLibrary().isLibraryContainBook(id)){
            if(Library.getLibrary().getBookById(id).isAvalible()){
                Library.getLibrary().getBookById(id).setAvalible(false);
                System.out.println("Type name of the Person who lent book:");
                sc.nextLine();
                String lendersName = sc.nextLine();
                Library.getLibrary().getBookById(id).setLendersName(lendersName);
                System.out.println("Book lent from Library");
            }else {
                System.out.println("Is not possbile to lent Book with Id: "+id+" of reason the book is currently lent");
            }
        }else {
            System.out.println("Library doesn't contain book with Id "+id);
        }
    }

    private void getDetailsodBookById(){
        Integer id = inputBookId();
        if(Library.getLibrary().isLibraryContainBook(id)){
            System.out.println(Library.getLibrary().getBookById(id));
        }else {
            System.out.println("Library doesn't contain book with Id "+id);
        }
    }

    private void searchBook(){
        /*Filter will be working in AND mode, if some of value is not empty then result will be like SQL querry
         * SELECT * FROM Book WHERE Name LIKE 'TypedName' and Year=TypedYear*/
        System.out.println("The filter is working in 'AND' mode of filtering");
        System.out.println("It means that if you type Name and Year the result will be the Book with typed Name and Year");

        System.out.println("Set value which you would like filter book list: Name, Year, Author of Book");
        System.out.println("Set value if you would like to filter by Name of Book: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Set value if you would like to filter by Year: ");

        String yearString = sc.nextLine();
        System.out.println("Set value if you would like to filter by Author: ");

        String author = sc.nextLine();

        Library.getLibrary().getBookList().stream().filter(
                book -> {
                    if(!name.equals("") && name.equals(book.getTitle())){
                        return true;
                    }else if(!name.equals("") && !name.equals(book.getTitle())){
                        return false;
                    }
                    return true;
                }
        ).filter(
                book -> {
                    if(!author.equals("") && author.equals(book.getAuthorOfBook())){
                        return true;
                    }else if(!author.equals("") && !author.equals(book.getAuthorOfBook())){
                        return false;
                    }
                    return true;
                }
        ).filter(
                book -> {
                    if(!yearString.equals("") && Integer.valueOf(yearString).equals(book.getYear())){
                        return true;
                    }else if (!yearString.equals("") && !Integer.valueOf(yearString).equals(book.getYear())){
                        return false;
                    }
                    return true;
                }
        ).forEach(System.out::println);
    }

    private Integer inputBookId(){

        System.out.println("Type Id of Boook to lent from library: ");
        System.out.println("Id: ");
        return sc.nextInt();
    }


}
