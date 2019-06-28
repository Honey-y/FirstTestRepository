// IBookInterface.aidl
package com.yangbowen.mytestwork.book;

// Declare any non-default types here with import statements
import com.yangbowen.mytestwork.book.Book;
interface IBookInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void addBook(in Book book);

    List<Book> getBookList();
}
