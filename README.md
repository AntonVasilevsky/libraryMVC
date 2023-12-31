﻿# libraryMVC
Required functionality:
1) Pages for adding, modifying, and deleting a person.
2) Pages for adding, modifying, and deleting a book.
3) Page with a list of all people (people are clickable - clicking on them leads to the person's page).
4) Page with a list of all books (books are clickable - clicking on them leads to the book's page).
5) Person's page displaying the values of their fields and a list of books they have borrowed. If the person hasn't borrowed any books, the list should display the text "The person hasn't borrowed any books yet."
6) Book's page displaying the values of its fields and the name of the person who borrowed it. If the book hasn't been borrowed by anyone, the text "This book is available" should be displayed.
7) On the book's page, if the book is borrowed by a person, there should be a button next to their name saying "Release Book." This button is clicked by a librarian when the reader returns the book to the library. After clicking this button, the book becomes available again and disappears from the person's list of books.
8) On the book's page, if the book is available, there should be a dropdown list (<select>) with all the people and a button saying "Assign Book." This button is clicked by a librarian when a reader wants to borrow the book. After clicking this button, the book should be assigned to the selected person and appear in their list of books.
9) All fields should be validated using @Valid and Spring Validator if necessary.
