package io.github.umangjpatel.charusatnoticeboard.utils.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.charusatnoticeboard.R;
import io.github.umangjpatel.charusatnoticeboard.models.Notice;
import io.github.umangjpatel.charusatnoticeboard.ui.detail.DetailActivity;

public class NoticeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Notice mNotice;

    private AppCompatTextView mCategoryTextView, mTitleTextView, mContentTextView, mDateTextView;

    public NoticeListViewHolder(@NonNull LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.notice_list_item, parent, false));
        mCategoryTextView = itemView.findViewById(R.id.notice_category_text_view);
        mTitleTextView = itemView.findViewById(R.id.notice_title_text_view);
        mContentTextView = itemView.findViewById(R.id.notice_content_text_view);
        mDateTextView = itemView.findViewById(R.id.notice_date_text_view);
        itemView.setOnClickListener(this);
    }

    public void bind(Notice notice) {
        mNotice = notice;
        mCategoryTextView.setText(notice.getCategory());
        mTitleTextView.setText(notice.getTitle());
        mContentTextView.setText(notice.getContent());
        //TODO : CHeck date format
        // mDateTextView.setText(notice.getStartDate());
    }

    @Override
    public void onClick(View v) {
        itemView.getContext().startActivity(DetailActivity.newIntent(itemView.getContext(), mNotice));
    }
}
