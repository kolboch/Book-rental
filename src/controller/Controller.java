package controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import exc.UnclearedException;
import model.Book;
import model.Client;
import model.Database;
import model.Rental;
import viewEvents.BookFormEvent;
import viewEvents.ClientFormEvent;

public class Controller {
	private Database db = new Database();
	
	private Book currentBook;
	private Client currentClient;
	
	public Controller(){
		
	}
	
	public List<Client> getClients(){
		return db.getClients();
	}
	
	public List<Book> getBooks(){
		return db.getBooks();
	}
	
	public List<Rental> getRentals(){
		return db.getRentals();
	}
	
	public void loadFile(File f){
		db.loadFromFile(f);
	}
	
	public void saveToFile(File f){
		db.saveToFile(f);
	}
	
	public void proceedCurrent(){
		if(currentClient != null && currentBook != null){
			Rental r = new Rental(currentClient, currentBook, new Date());
			db.addRental(r);
			currentClient.rentBook(currentBook);
		}
	}
	
	public void clearCurrent(){
		currentClient = null;
		currentBook = null;
	}
	
	public void addClient(ClientFormEvent e){
		String firstName = e.getFirstName();
		String lastName = e.getLastName();
		Client c = new Client(firstName, lastName);
		db.addClient(c);
	}
	
	public void removeClient(int index) throws UnclearedException{
		db.removeClient(index);
	}
	
	public void addBook(BookFormEvent e){
		String title = e.getTitle();
		String author = e.getAuthor();
		Book b = new Book(title, author);
		db.addBook(b);
	}
	
	public void removeBook(int index) throws UnclearedException{
		db.removeBook(index);
	}
	
	public Book getCurrentBook(){
		return currentBook;
	}
	
	public Client getCurrentClient(){
		return currentClient;
	}
	
	public void setCurrentBook(Book b){
		this.currentBook = b;
	}
	
	public void setCurrentClient(Client c){
		this.currentClient = c;
	}
}
