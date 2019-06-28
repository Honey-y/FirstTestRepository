package com.yangbowen.mytestwork.book;

import android.app.ListActivity;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {

    private List<Book> bookList ;

    @Override
    public void onCreate() {
        super.onCreate();
        bookList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setBookId(i);
            book.setBookName("书籍"+i);
            bookList.add(book);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return stub;
    }

    private IBookInterface.Stub stub = new IBookInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void addBook(Book book) throws RemoteException {
            bookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }
    };

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.i("服务","断开");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("服务","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("服务","销毁");
    }
}
