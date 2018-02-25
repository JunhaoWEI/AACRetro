package cn.j0.j0teachingspirit.aacretro;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
Context mContext = this;
    RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;
    private List<Book> mBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBookAdapter = new BookAdapter(mContext, mBooks);
        mRecyclerView.setAdapter(mBookAdapter);

        BookViewModel viewModel = ViewModelProviders.of(this)
                .get(BookViewModel.class);
        viewModel.getBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                mBooks = books;
                mBookAdapter.setBooks(books);
            }
        });
    }
}
