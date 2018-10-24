package io.github.umangjpatel.charusatnoticeboard.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import io.github.umangjpatel.charusatnoticeboard.R;
import io.github.umangjpatel.charusatnoticeboard.models.Notice;


public class DetailFragment extends Fragment {

    private static final String KEY_NOTICE_DETAIL = "key_notice_detail";

    private Notice mDetailNotice;

    private AppCompatTextView mTitleTextView, mAuthorityTextView, mDateTextView, mContentTextView;

    public static DetailFragment newInstance(Notice notice) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_NOTICE_DETAIL, notice);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        wireUpWidgets(view);
        mDetailNotice = (Notice) Objects.requireNonNull(getArguments()).getSerializable(KEY_NOTICE_DETAIL);
        displayNoticeDetail();
        return view;
    }

    private void displayNoticeDetail() {
        mTitleTextView.setText(mDetailNotice.getTitle());
        //mAuthorityTextView.setText(notice.getDocName());
        //mDateTextView.setText(notice.getStartDate());
        mContentTextView.setText(mDetailNotice.getContent());
    }

    private void wireUpWidgets(View view) {
        mTitleTextView = view.findViewById(R.id.notice_detail_title_text_view);
        mAuthorityTextView = view.findViewById(R.id.notice_detail_authority_text_view);
        mDateTextView = view.findViewById(R.id.notice_detail_date_text_view);
        mContentTextView = view.findViewById(R.id.notice_detail_content_text_view);
    }
}
