package io.github.umangjpatel.charusatnoticeboard.utils.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.charusatnoticeboard.models.Notice;
import io.github.umangjpatel.charusatnoticeboard.utils.viewholders.NoticeListViewHolder;

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListViewHolder> {

    private List<Notice> mNoticeList;

    public NoticeListAdapter(List<Notice> notices) {
        mNoticeList = notices;
    }

    public void setNoticeList(List<Notice> noticeList) {
        mNoticeList = noticeList;
    }

    @NonNull
    @Override
    public NoticeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NoticeListViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeListViewHolder holder, int position) {
        holder.bind(mNoticeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNoticeList.size();
    }
}
