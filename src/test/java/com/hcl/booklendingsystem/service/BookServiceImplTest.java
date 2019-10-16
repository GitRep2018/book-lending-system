package com.hcl.booklendingsystem.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.booklendingsystem.dto.BookRequestDetails;
import com.hcl.booklendingsystem.dto.CommonResponse;
import com.hcl.booklendingsystem.entity.Author;
import com.hcl.booklendingsystem.entity.Book;
import com.hcl.booklendingsystem.entity.User;
import com.hcl.booklendingsystem.exception.UserNotFoundException;
import com.hcl.booklendingsystem.repository.AuthorRepository;
import com.hcl.booklendingsystem.repository.BookRepository;
import com.hcl.booklendingsystem.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@InjectMocks
	BookServiceImpl bookServiceImpl;

	@Mock
	BookRepository bookRepository;

	@Mock
	AuthorRepository authorRepository;

	@Mock
	UserRepository userRepository;

	BookRequestDetails bookRequestDetails;

	CommonResponse commonResponse;

	Book book;
	User user;

	@Before
	public void setup() {
		bookRequestDetails = new BookRequestDetails();
		bookRequestDetails.setUserId(1);
		bookRequestDetails.setAuthorName("kiruthika");
		bookRequestDetails.setBookName("java");

		user = new User();
		user.setUserId(1);

		book = new Book();
		book.setBookName("java");

		commonResponse = new CommonResponse();
	}

	@Test
	public void testAddBook() {

		Author author = new Author();

		author.setAuthorName("kiruthika");

		Mockito.when(userRepository.findById(bookRequestDetails.getUserId())).thenReturn(Optional.of(user));
		Mockito.when(authorRepository.findByAuthorName(author.getAuthorName())).thenReturn(Optional.of(author));

		commonResponse = bookServiceImpl.addBook(bookRequestDetails);

		assertEquals(bookRequestDetails.getBookName(), book.getBookName());

	}

	@Test(expected = UserNotFoundException.class)
	public void testAddBook1() {
		Mockito.when(userRepository.findById(bookRequestDetails.getUserId())).thenReturn(Optional.empty());

		commonResponse = bookServiceImpl.addBook(bookRequestDetails);

		assertEquals(bookRequestDetails.getBookName(), book.getBookName());

	}

	@Test
	public void testAddBook2() {

		Mockito.when(userRepository.findById(bookRequestDetails.getUserId())).thenReturn(Optional.of(user));

		Mockito.when(authorRepository.findByAuthorName(Mockito.anyString())).thenReturn(Optional.empty());

		commonResponse = bookServiceImpl.addBook(bookRequestDetails);

		assertEquals(bookRequestDetails.getBookName(), book.getBookName());

	}
}
