package io.github.umangjpatel.charusatnoticeboard.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.charusatnoticeboard.R;
import io.github.umangjpatel.charusatnoticeboard.models.Message;
import io.github.umangjpatel.charusatnoticeboard.utils.adapters.ChatAdapter;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mChatRecyclerView;
    private ChatAdapter mAdapter;
    private ArrayList<Message> mMessageArrayList;
    private EditText mInputMessageEditText;
    private Button mSendMessageButton;
    com.ibm.watson.developer_cloud.conversation.v1.model.Context context = null;
    private boolean mInitialRequest;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, ChatActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mInputMessageEditText = findViewById(R.id.message);
        mSendMessageButton = findViewById(R.id.btn_send);
        mChatRecyclerView = findViewById(R.id.recycler_view);

        mMessageArrayList = new ArrayList<>();
        mAdapter = new ChatAdapter(mMessageArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        mChatRecyclerView.setLayoutManager(layoutManager);
        mChatRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mChatRecyclerView.setAdapter(mAdapter);
        mInputMessageEditText.setText("");
        mInitialRequest = true;
        sendMessage();
        setListeners();

    }

    private void setListeners() {
        mSendMessageButton.setOnClickListener(v -> {
            String inputMessage = mInputMessageEditText.getText().toString();
            if (!TextUtils.isEmpty(inputMessage))
                sendMessage();
        });
    }


    // Sending a message to Watson Conversation Service
    private void sendMessage() {
        String inputmessage = this.mInputMessageEditText.getText().toString().trim();
        if (!mInitialRequest) {
            Message inputMessage = new Message();
            inputMessage.setMessage(inputmessage);
            inputMessage.setId("1");
            mMessageArrayList.add(inputMessage);
        } else {
            Message inputMessage = new Message();
            inputMessage.setMessage(inputmessage);
            inputMessage.setId("100");
            mInitialRequest = false;

        }

        this.mInputMessageEditText.setText("");
        mAdapter.notifyDataSetChanged();

        Thread thread = new Thread(() -> {
            try {

                Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
                //username and password
                service.setUsernameAndPassword("b8b92ec5-70a7-4680-bded-aa679d923255", "QOmcnRU36JjD");

                InputData input = new InputData.Builder(inputmessage).build();
                //workspace id
                MessageOptions options = new MessageOptions.Builder("bb9270cf-c4e1-4b16-a615-5dce9c36393e").input(input).context(context).build();
                MessageResponse response = service.message(options).execute();

                //Passing Context of last conversation
                if (response.getContext() != null) {
                    //context.clear();
                    context = response.getContext();

                }
                Message outMessage = new Message();
                if (response.getOutput() != null && response.getOutput().containsKey("text")) {
                    ArrayList responseList = (ArrayList) response.getOutput().get("text");
                    if (null != responseList && responseList.size() > 0) {
                        //response (Html.fromHtml(Html.fromHtml(mHtmlString).toString())
                        //outMessage.setMessage((String)responseList.get(0));
                        String out = (Html.fromHtml((String) responseList.get(0)).toString());
                        outMessage.setMessage(out);

                        System.out.println((Html.fromHtml(Html.fromHtml((String) responseList.get(0)).toString())));
                        outMessage.setId("2");
                    }
                    mMessageArrayList.add(outMessage);
                }

                runOnUiThread(() -> {
                    mAdapter.notifyDataSetChanged();
                    if (mAdapter.getItemCount() > 1) {
                        Objects.requireNonNull(mChatRecyclerView.getLayoutManager())
                                .smoothScrollToPosition(mChatRecyclerView, null, mAdapter.getItemCount() - 1);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}