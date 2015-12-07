package pcmdroid.instagramclient;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by parth.mehta on 12/5/15.
 */
public class InstagramPhotoAdapter  extends ArrayAdapter<InstagramPhoto>{
    private static final String TAG = InstagramPhotoAdapter.class.getSimpleName();
    List<InstagramPhoto> photos;

    private static class ViewHolder {
        ImageView ivPhoto;
        TextView tvNameNCaption;
        TextView tvUserName;
        ImageView ivProfilePhoto;
        TextView tvNumLikes;
    }

    public InstagramPhotoAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        photos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = photos.get(position);
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvNameNCaption = (TextView)convertView.findViewById(R.id.tvCaption);
            viewHolder.ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhotoListItem);
            viewHolder.ivProfilePhoto = (ImageView)convertView.findViewById(R.id.ivProfilePicture);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            viewHolder.tvNumLikes = (TextView)convertView.findViewById(R.id.tvNumLikes);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.tvNameNCaption.setText(getNameNCaptionSpannable(getContext(), photo.userName, photo.caption));
        viewHolder.tvUserName.setText(photo.userName);
        viewHolder.tvNumLikes.setText(photo.likes + " likes");
        viewHolder.ivPhoto.setImageResource(0);
        viewHolder.ivProfilePhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.profilePictureUrl).fit().centerCrop().into(viewHolder.ivProfilePhoto);
        // method 1
        //Picasso.with(getContext()).load(photo.url).fit().centerCrop().into(viewHolder.ivPhoto);
        //method2
//        int displayWidth = DeviceDimensionsHelper.getDisplayWidth(getContext());
//        int imageTargetHeight = (photo.imageHeight)*(displayWidth)/photo.imageWidth;
//        Picasso.with(getContext()).load(photo.url).resize(displayWidth,imageTargetHeight).into(viewHolder.ivPhoto);
        //method 3
        /*
        this seems to work best as the image view size is fixed before image is loaded which avoids jerks during fast scrolling
         */
        int displayWidth = DeviceDimensionsHelper.getDisplayWidth(getContext());
        int imageTargetHeight = (photo.imageHeight)*(displayWidth)/photo.imageWidth;
        Log.d(TAG, "Image Info original h x w:"+photo.imageHeight+"x" + photo.imageWidth+" Scaled h x w:" + displayWidth+"x"+imageTargetHeight);
        viewHolder.ivPhoto.getLayoutParams().height = imageTargetHeight;
        Picasso.with(getContext()).load(photo.url).fit().centerCrop().placeholder(R.drawable.image_loading).into(viewHolder.ivPhoto);
        return convertView;

    }


    public static Spannable getNameNCaptionSpannable(Context context, String userName, String caption) {
        String finalString= userName +" " + caption;
        Spannable sb = new SpannableString( finalString );
        sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.instagramBlue)), finalString.indexOf(userName),
                userName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), finalString.indexOf(userName),
                userName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

//    public int getDesiredImageHeight(Context context,int imageHeight) {
//
//    }
}
