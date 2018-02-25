package cn.j0.j0teachingspirit.aacretro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by aa on 2018/2/25.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private Context mContext;
    private List<Book> mBooks;

    public BookAdapter(Context context, List<Book> books) {
        mContext = context;
        mBooks = books;
    }

    public void setBooks(List<Book> books) {
        mBooks = books;
        notifyDataSetChanged();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(mContext).inflate(R.layout.bookitem, parent, false));
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.mTextView.setText(mBooks.get(position).getTitle());
        Glide.with(mContext).load(mBooks.get(position).getCoverUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mBooks == null ? 0 : mBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        public BookViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
