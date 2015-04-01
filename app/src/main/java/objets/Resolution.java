package objets;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mehdibeggas on 01/04/2015.
 */
public class Resolution implements Parcelable{

    private int height;
    private int width;

    //Constructeur
    public Resolution(int height, int width) {
        this.height = height;
        this.width = width;
    }

    //getter setter
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

    //from implements Parcelable
    //utile seulement si ma classe est héritée
    @Override
    public int describeContents() {
        return 0;
    }

    //from implements Parcelable
    // liste les données à parser
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(height);
        dest.writeInt(width);


    }

    //permet de parser ma classe
    public static final Parcelable.Creator<Resolution> CREATOR = new Parcelable.Creator<Resolution>()
    {
        @Override
        public Resolution createFromParcel(Parcel source)
        {
            return new Resolution(source);
        }

        @Override
        public Resolution[] newArray(int size)
        {
            return new Resolution[size];
        }
    };

    //constructeur utilisé par Parcelable.creator
    public Resolution(Parcel in) {

        this.height = in.readInt();
        this.width = in.readInt();

    }
}
