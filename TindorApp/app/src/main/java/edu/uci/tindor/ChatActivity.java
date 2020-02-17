package edu.uci.tindor;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;

import edu.uci.tindor.Adapters.MsgAdapter;
import edu.uci.tindor.DataModel.Message;
import edu.uci.tindor.Utils.Config;

public class ChatActivity extends AppCompatActivity{

    MsgAdapter msgAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        String name = getIntent().getStringExtra("name");
        String imageUrl = getIntent().getStringExtra("imageUrl");
//        chatId = intent.extras.getString(PARAM_CHAT_ID)
//        userId = intent.extras.getString(PARAM_USER_ID)
//        imageUrl = intent.extras.getString(PARAM_IMAGE_URL)
//        otherUserId = intent.extras.getString(PARAM_OTHER_USER_ID)
//        if(chatId.isNullOrEmpty() || userId.isNullOrEmpty() || imageUrl.isNullOrEmpty() || otherUserId.isNullOrEmpty()) {
//            Toast.makeText(this, "Chat room error", Toast.LENGTH_SHORT).show()
//            finish()
//        }

        String userId = "111";
        msgAdapter = new MsgAdapter(new ArrayList(), userId);
        RecyclerView matchesRV = findViewById(R.id.messagesRV);
        matchesRV.setHasFixedSize(false);
        matchesRV.setLayoutManager(new LinearLayoutManager(this));
        matchesRV.setAdapter(msgAdapter);

        TextView topNameTV = findViewById(R.id.topNameTV);
        ImageView topPhotoIV = findViewById(R.id.topPhotoIV);
        //user.name
        topNameTV.setText(name);
        Glide.with(this)
                .load(imageUrl)
                .into(topPhotoIV);

        Message message = new Message("222", "How are you doing?", Calendar.getInstance().getTime().toString());
        msgAdapter.addMessage(message);
        RecyclerView messagesRV = findViewById(R.id.messagesRV);
        messagesRV.smoothScrollToPosition(msgAdapter.getItemCount() - 1);

//        topPhotoIV.setOnClickListener {
//            startActivity(UserInfoActivity.newIntent(this@ChatActivity, otherUserId))
//        }
    }

    public void onSend(View view) {
        EditText messageET = findViewById(R.id.messageET);
        Message message = new Message("111", messageET.getText().toString(), Calendar.getInstance().getTime().toString());
        messageET.setText("", TextView.BufferType.EDITABLE);
        msgAdapter.addMessage(message);
        RecyclerView messagesRV = findViewById(R.id.messagesRV);
        messagesRV.smoothScrollToPosition(msgAdapter.getItemCount() - 1);
    }
}
