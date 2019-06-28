package com.yangbowen.mytestwork;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yangbowen.mytestwork.book.Book;
import com.yangbowen.mytestwork.book.IBookInterface;

import java.util.List;

/**


*/

public class MainActivity extends AppCompatActivity {

    private TextView mResult;
    private IBookInterface iBookInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult = findViewById(R.id.result);
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iBookInterface != null){
                    try {
                        Book book = new Book();
                        book.setBookName("新添书籍");
                        iBookInterface.addBook(book);
                        ToastTip("添加成功");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button btnQuery = findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResult.setText("");
                if (iBookInterface != null){
                    try {
                        List<Book> books = iBookInterface.getBookList();
                        mResult.setText(getBooksResult(books));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button btnBind = findViewById(R.id.btn_bind);
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.yangbowen.mytestwork.book.BookService");
                intent.setPackage(getPackageName());
                bindService(intent,connection,BIND_AUTO_CREATE);
                ToastTip("服务已连接");
            }
        });

        Button btnDisBind = findViewById(R.id.btn_disBind);
        btnDisBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    unbindService(connection);
                    ToastTip("服务已断开");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastTip(e.getMessage());
                }
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookInterface = IBookInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iBookInterface = null;
        }
    };

    private void ToastTip (String tip){
        Toast.makeText(this,tip,Toast.LENGTH_SHORT).show();
    }

    private String getBooksResult(List<Book> list){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i).getBookName()).append("\n");
        }
        return builder.toString();
    }
}
