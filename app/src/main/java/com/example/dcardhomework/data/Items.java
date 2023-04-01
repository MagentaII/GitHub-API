package com.example.dcardhomework.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Items implements Parcelable {

    public final int id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("description")
    private String description;

    @SerializedName("stargazers_count")
    private int stargazers_count;

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("language")
    private String language;

    @SerializedName("forks_count")
    private int forks_count;

//    public Items(int id, String name, String full_name, String description, int stargazers_count, Owner owner, String language, int forks_count) {
//        this.id = id;
//        this.name = name;
//        this.full_name = full_name;
//        this.description = description;
//        this.stargazers_count = stargazers_count;
//        this.owner = owner;
//        this.language = language;
//        this.forks_count = forks_count;
//    }

    protected Items(Parcel in) {
        id = in.readInt();
        name = in.readString();
        full_name = in.readString();
        description = in.readString();
        stargazers_count = in.readInt();
        owner = in.readParcelable(Owner.class.getClassLoader());
        language = in.readString();
        forks_count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(full_name);
        dest.writeString(description);
        dest.writeInt(stargazers_count);
        dest.writeParcelable(owner, flags);
        dest.writeString(language);
        dest.writeInt(forks_count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }
}
