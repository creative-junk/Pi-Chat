package crysoftdynamics.timeline;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class ChatActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {
    EditText messageInput;
    FloatingActionButton sendButton;
    MessageAdapter messageAdapter;

    final String MESSAGES_ENDPOINT = "http://pusher-chat-demo.herokuapp.com";
    final String USERNAME ="crysoft";
    private static final String TAG="Chat Activity";
    private boolean side = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageInput = (EditText) findViewById(R.id.message_input);
        messageInput.setOnKeyListener(this);


        sendButton = (FloatingActionButton) findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);

        messageAdapter = new MessageAdapter(this, new ArrayList<Message>());
        final ListView messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        messagesView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        messagesView.setAdapter(messageAdapter);

        //to scroll the list view to bottom on data change
        messageAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                messagesView.setSelection(messageAdapter.getCount() - 1);
            }
        });
        /*
        Pusher pusher = new Pusher("faa685e4bb3003eb825c");

        pusher.connect();

        Channel channel = pusher.subscribe("messages");
        channel.bind("new_message", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Message message = gson.fromJson(data, Message.class);
                        messageAdapter.add(message);
                        messagesView.setSelection(messageAdapter.getCount() - 1);
                    }
                });
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            postMessage();
        }
        return true;
    }

    private boolean postMessage() {
        //Get the message text
       /* String text = messageInput.getText().toString();
        //check if the message is empty
        if (text.equals("")) {
            return;
        }
        sendPusherMessage(text);*/
        messageAdapter.add(new Message(side, messageInput.getText().toString()));
        messageInput.setText("");
        side = !side;
        return true;
    }

    private void sendPusherMessage(String text) {
        RequestParams requestParams = new RequestParams();

        requestParams.put("text", text);
        requestParams.put("time", new Date().getTime());
        requestParams.put("name", USERNAME);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(MESSAGES_ENDPOINT + "/messages", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageInput.setText("");
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(ChatActivity.this, "Something Went Wrong :( ", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        postMessage();
    }


}
