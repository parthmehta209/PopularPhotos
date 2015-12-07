package pcmdroid.instagramclient;

/**
 * Created by parth.mehta on 12/5/15.
 */
public class InstagramPhoto {
    String userName;
    String caption;
    String url;
    String profilePictureUrl;
    int imageHeight;
    int imageWidth;
    int likes;

    public InstagramPhoto(String userName,String caption,String url,String profilePictureUrl,int imageHeight, int imageWidth, int likes) {
        this.userName = userName;
        this.caption = caption;
        this.url = url;
        this.imageHeight = imageHeight;
        this.profilePictureUrl = profilePictureUrl;
        this.likes = likes;
        this.imageWidth = imageWidth;
    }
}
