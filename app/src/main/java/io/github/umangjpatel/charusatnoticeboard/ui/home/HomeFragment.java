package io.github.umangjpatel.charusatnoticeboard.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.github.umangjpatel.charusatnoticeboard.R;
import io.github.umangjpatel.charusatnoticeboard.ui.chat.ChatActivity;
import io.github.umangjpatel.charusatnoticeboard.utils.adapters.NoticeListAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    private CoordinatorLayout mMainLayout;
    private RecyclerView mNoticeListRecyclerView;
    private LinearLayoutCompat mErrorLayout;
    private FloatingActionButton mChatbotButton;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private NoticeListAdapter mNoticeListAdapter;

    static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        wireUpWidgets(view);
        setListeners();
        showResponseCode(1);
        setHasOptionsMenu(true);
        return view;
    }

    private void setListeners() {
        mChatbotButton.setOnClickListener(v -> startActivity(ChatActivity.newIntent(getActivity())));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            showResponseCode(1);
            mViewModel.fetchNotices();
        });
    }

    private void wireUpWidgets(View view) {
        mMainLayout = view.findViewById(R.id.main_home);
        mNoticeListRecyclerView = view.findViewById(R.id.notice_list_recycler_view);
        mNoticeListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mErrorLayout = view.findViewById(R.id.error_layout);
        mChatbotButton = view.findViewById(R.id.chat_bot_button);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
    }

    @Override
    public void onStart() {
        super.onStart();
        observeNoticeListData();
    }

    private void observeNoticeListData() {
        mViewModel.getNoticesLiveData().observe(this, notices -> {
            if (notices == null) {
                showResponseCode(0);
            } else if (notices.isEmpty())
                showResponseCode(1);
            else {
                showResponseCode(2);
                if (mNoticeListAdapter == null) {
                    mNoticeListAdapter = new NoticeListAdapter(notices);
                    mNoticeListRecyclerView.setAdapter(mNoticeListAdapter);
                } else {
                    mNoticeListAdapter.setNoticeList(notices);
                    mNoticeListAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void showResponseCode(int responseCode) {
        switch (responseCode) {
            case 0:
                mErrorLayout.setVisibility(View.VISIBLE);
                mNoticeListRecyclerView.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case 1:
                mErrorLayout.setVisibility(View.GONE);
                mNoticeListRecyclerView.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(true);
                break;
            case 2:
                mNoticeListRecyclerView.setVisibility(View.VISIBLE);
                mErrorLayout.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_home, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_notices).getActionView();
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.getNoticesByTitle(query.toLowerCase());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        searchView.setOnCloseListener(() -> {
            mViewModel.fetchNotices();
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                showResponseCode(1);
                mSwipeRefreshLayout.setRefreshing(true);
                mViewModel.fetchNotices();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
