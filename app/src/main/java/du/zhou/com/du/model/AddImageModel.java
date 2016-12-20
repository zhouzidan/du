package du.zhou.com.du.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhou on 2016/12/11.
 */

public class AddImageModel implements Parcelable {
    public String localPath;
    public String remoteUrl;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.localPath);
        dest.writeString(this.remoteUrl);
    }

    public AddImageModel() {
    }

    protected AddImageModel(Parcel in) {
        this.localPath = in.readString();
        this.remoteUrl = in.readString();
    }

    public static final Creator<AddImageModel> CREATOR = new Creator<AddImageModel>() {
        @Override
        public AddImageModel createFromParcel(Parcel source) {
            return new AddImageModel(source);
        }

        @Override
        public AddImageModel[] newArray(int size) {
            return new AddImageModel[size];
        }
    };
}
