package cn.j0.j0teachingspirit.aacretro;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;


/**
 * Created by aa on 2018/2/25.
 */

public class BookViewModel extends AndroidViewModel{
    private MediatorLiveData<List<Book>> mObservableBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);

        mObservableBooks = new MediatorLiveData<>();
        mObservableBooks.setValue(null);

        LiveData<List<Book>> books = BookRepository.getInstance().getBooks();
        mObservableBooks.addSource(books, mObservableBooks::setValue);
    }

   public LiveData<List<Book>> getBooks() {
        return mObservableBooks;
   }
}
