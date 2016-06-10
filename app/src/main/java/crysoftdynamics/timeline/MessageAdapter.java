package crysoftdynamics.timeline;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxx on 6/9/2016.
 */
public class MessageAdapter extends BaseAdapter {
    Context messageContext;
    List<Message> messageList;

    //New way of doing it
    private TextView chatText;
    private List chatMessageList = new ArrayList();
    private LinearLayout singleMessageContainer;

    public MessageAdapter(Context context, List<Message> messages){
        messageList = messages;
        messageContext = context;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* MessageViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater messageInflater = (LayoutInflater) messageContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = messageInflater.inflate(R.layout.message,null);
            viewHolder =  new MessageViewHolder();
          //  viewHolder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            viewHolder.senderView = (TextView) convertView.findViewById(R.id.message_sender);
            viewHolder.bodyView = (TextView) convertView.findViewById(R.id.message_body);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (MessageViewHolder) convertView.getTag();
        }

        Message message = (Message) getItem(position);

        viewHolder.bodyView.setText(message.text);
        viewHolder.senderView.setText(message.name);


        return convertView;*/
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) messageContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.message, parent, false);
        }
        singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);
        Message chatMessageObj = (Message) getItem(position);
        chatText = (TextView) row.findViewById(R.id.singleMessage);
        chatText.setText(chatMessageObj.message);
        chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.msg_in : R.drawable.msg_out);
        singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.LEFT : Gravity.RIGHT);
        return row;
    }
    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public void add(Message message){
        messageList.add(message);
        notifyDataSetChanged();
    }
    private static class MessageViewHolder{
        public ImageView thumbnailImageView;
        public TextView senderView;
        public TextView bodyView;
    }
}
