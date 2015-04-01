package objets;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mehdibeggas on 01/04/2015.
 */
public class Resolution implements Parcelable{

    private int height;
    private int width;

    public Resolution(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(height);
        dest.writeInt(width);


    }
}
