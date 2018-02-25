package cn.j0.j0teachingspirit.aacretro;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aa on 2018/2/25.
 */


public class BookRepository {
    private static BookRepository sInstance;

    public static BookRepository getInstance() {
        if (sInstance == null) {
            synchronized (BookRepository.class) {
                if (sInstance == null) {
                    sInstance = new BookRepository();
                }
            }
        }
        return sInstance;
    }

    Map<String, Book> mCachedBooks;

    public LiveData<List<Book>> getBooks() {
        Log.i("wjh", "getBooks: ");
        final MutableLiveData<List<Book>> books = new MutableLiveData<>();
        if (mCachedBooks != null) {
            Log.i("wjh", "从内存中取");
            books.setValue(new ArrayList<>(mCachedBooks.values()));
            return books;
        }

        Log.i("wjh", "从网络中取数据");
        new Retrofit.Builder()
                .baseUrl("http://192.168.100.91/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Webservice.class)
                .getBooks(1, 12, 0, "enword", "J15665983754601472")
                .enqueue(new Callback<BaseEntity<List<Book>>>() {
                    @Override
                    public void onResponse(@NonNull Call<BaseEntity<List<Book>>> call, @NonNull Response<BaseEntity<List<Book>>> response) {
                        if (response.body().getStatus() == 200) {
                            Log.i("wjh", "网络请求成功");
                            List<Book> bookList = response.body().getData();
                            books.setValue(bookList);
                            if (mCachedBooks == null) {
                                mCachedBooks = new LinkedHashMap<>();
                            }
                            mCachedBooks.clear();
                            for (Book book :
                                    bookList) {
                                mCachedBooks.put(book.getWorkbookId(), book);
                            }

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BaseEntity<List<Book>>> call, @NonNull Throwable t) {

                    }
                });
        return books;
    }
}
