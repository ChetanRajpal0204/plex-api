package com.media.api.model;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.os.Parcel;
import android.os.Parcelable;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.StartElementListener;

public class Movie implements Parcelable {
	
	private int mId;
	private String mKey;
	private String mStudio;
	private String mTitle;
	private String mSummary;
	private String mTagline;
	private String mContentRating;
	private float mRating;
	private int mYear;
	private String mThumb;
	private String mArt;
	private String mVideoLink;
	private String mGuid;
	private String mOriginallyAvailableAt;
	private String mDuration;
	private String mAddedAt;
	private String mVideoResolution;
	private String mAudioCodec;
	
	public static List<Movie> appendArrayListener(final Element parent, int depth) {
		final List<Movie> movies = new ArrayList<Movie>();
		final Movie movie = new Movie();
		
		Element movieElement = parent.getChild("Video");
		movieElement.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				movies.add(movie.copy());
				movie.clear();
			}
		});
		
		appendCommonListeners(movieElement, movie, depth);
		
		return movies;
	}
	
	private static void appendCommonListeners(final Element movieElement, final Movie movie, int depth) {
		movieElement.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("ratingKey");
				movie.setId(Integer.parseInt(value));
				
				value = attributes.getValue("key");
				movie.setKey(value);
				
				value = attributes.getValue("studio");
				movie.setStudio(value);
				
				value = attributes.getValue("title");
				movie.setTitle(value);
				
				value = attributes.getValue("summary");
				movie.setSummary(value);
				
				value = attributes.getValue("tagline");
				movie.setTagline(value);
				
				value = attributes.getValue("contentRating");
				movie.setContentRating(value);
				
				value = attributes.getValue("rating");
				if (value != null)
					movie.setRating(Float.parseFloat(value));
				
				value = attributes.getValue("year");
				if (value != null)
					movie.setYear(Integer.parseInt(value));
				
				value = attributes.getValue("thumb");
				movie.setThumb(value);
				
				value = attributes.getValue("art");
				movie.setArt(value);
				
				value = attributes.getValue("guid");
				movie.setGuid(value);
				
				value = attributes.getValue("originallyAvailableAt");
				movie.setOriginallyAvailableAt(value);
				
				value = attributes.getValue("addedAt");
				movie.setAddedAt(value);
			}
		});
		
		movieElement.getChild("Media").setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("duration");
				movie.setDuration(value);
				
				value = attributes.getValue("videoResolution");
				movie.setVideoResolution(value);
				
				value = attributes.getValue("audioCodec");
				movie.setAudioCodec(value);
			}
		});
		
		movieElement.getChild("Media").getChild("Part").setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("key");
				movie.setVideoLink(value);
			}
		});
	}
	
	public static Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    
    public Movie() { }
    
    public Movie(Parcel parcel) {
    	mId = parcel.readInt();
    	mRating = parcel.readFloat();
    	mYear = parcel.readInt();
    	mKey = parcel.readString();
    	mStudio = parcel.readString();
    	mTitle = parcel.readString();
    	mSummary = parcel.readString();
    	mTagline = parcel.readString();
    	mContentRating = parcel.readString();
    	mThumb = parcel.readString();
    	mArt = parcel.readString();
    	mVideoLink = parcel.readString();
    	mGuid = parcel.readString();
    	mOriginallyAvailableAt = parcel.readString();
    	mDuration = parcel.readString();
    	mAddedAt = parcel.readString();
    	mVideoResolution = parcel.readString();
    	mAudioCodec = parcel.readString();
    }
	
	public int getId() {
		return mId;
	}
	
	public void setId(int id) {
		mId = id;
	}
	
	public String getKey() {
		return mKey;
	}
	
	public void setKey(String key) {
		mKey = key;
	}
	
	public String getStudio() {
		return mStudio;
	}
	
	public void setStudio(String studio) {
		mStudio = studio;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public String getSummary() {
		return mSummary;
	}
	
	public void setSummary(String summary) {
		mSummary = summary;
	}
	
	public String getTagline() {
		return mTagline;
	}
	
	public void setTagline(String tagline) {
		mTagline = tagline;
	}
	
	public String getContentRating() {
		return mContentRating;
	}
	
	public void setContentRating(String contentRating) {
		mContentRating = contentRating;
	}
	
	public float getRating() {
		return mRating;
	}
	
	public void setRating(float rating) {
		mRating = rating;
	}
	
	public int getYear() {
		return mYear;
	}
	
	public void setYear(int year) {
		mYear = year;
	}
	
	public String getThumb() {
		return mThumb;
	}
	
	public void setThumb(String thumb) {
		mThumb = thumb;
	}
	
	public String getArt() {
		return mArt;
	}
	
	public void setArt(String art) {
		mArt = art;
	}
	
	public String getVideoLink() {
		return mVideoLink;
	}
	
	public void setVideoLink(String link) {
		mVideoLink = link;
	}
	
	public String getGuid() {
		return mGuid;
	}

	public void setGuid(String guid) {
		this.mGuid = guid;
	}
	
	public String getOriginallyAvailableAt() {
		return mOriginallyAvailableAt;
	}

	public void setOriginallyAvailableAt(String originallyAvailableAt) {
		mOriginallyAvailableAt = originallyAvailableAt;
	}

	public String getDuration() {
		return mDuration;
	}

	public void setDuration(String duration) {
		mDuration = duration;
	}

	public String getAddedAt() {
		return mAddedAt;
	}

	public void setAddedAt(String addedAt) {
		mAddedAt = addedAt;
	}

	public String getVideoResolution() {
		return mVideoResolution;
	}

	public void setVideoResolution(String videoResolution) {
		mVideoResolution = videoResolution;
	}

	public String getAudioCodec() {
		return mAudioCodec;
	}

	public void setAudioCodec(String audioCodec) {
		mAudioCodec = audioCodec;
	}

	public void clear() {
		this.setId(-1);
		this.setKey("");
		this.setStudio("");
		this.setTitle("");
		this.setSummary("");
		this.setTagline("");
		this.setContentRating("");
		this.setRating(0.0f);
		this.setYear(2000);
		this.setThumb("");
		this.setArt("");
		this.setVideoLink("");
		this.setGuid("");
		this.setOriginallyAvailableAt("");
		this.setDuration("");
		this.setAddedAt("");
		this.setVideoResolution("");
		this.setAudioCodec("");
	}
	
	public Movie copy() {
		Movie newMovie = new Movie();
		newMovie.setId(this.getId());
		newMovie.setKey(this.getKey());
		newMovie.setStudio(this.getStudio());
		newMovie.setTitle(this.getTitle());
		newMovie.setSummary(this.getSummary());
		newMovie.setTagline(this.getTagline());
		newMovie.setContentRating(this.getContentRating());
		newMovie.setRating(this.getRating());
		newMovie.setYear(this.getYear());
		newMovie.setThumb(this.getThumb());
		newMovie.setArt(this.getArt());
		newMovie.setVideoLink(this.getVideoLink());
		newMovie.setGuid(this.getGuid());
		newMovie.setOriginallyAvailableAt(this.getOriginallyAvailableAt());
		newMovie.setDuration(this.getDuration());
		newMovie.setAddedAt(this.getAddedAt());
		newMovie.setVideoResolution(this.getVideoResolution());
		newMovie.setAudioCodec(this.getAudioCodec());
		return newMovie;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeFloat(mRating);
		dest.writeInt(mYear);
		dest.writeString(mKey);
		dest.writeString(mStudio);
		dest.writeString(mTitle);
		dest.writeString(mSummary);
		dest.writeString(mTagline);
		dest.writeString(mContentRating);
		dest.writeString(mThumb);
		dest.writeString(mArt);
		dest.writeString(mVideoLink);
		dest.writeString(mGuid);
		dest.writeString(mOriginallyAvailableAt);
		dest.writeString(mDuration);
		dest.writeString(mAddedAt);
		dest.writeString(mVideoResolution);
		dest.writeString(mAudioCodec);
	}
}
