package com.media.api;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.media.api.base.MediaApi;
import com.media.api.model.Episode;
import com.media.api.model.Movie;
import com.media.api.model.Season;
import com.media.api.model.Section;
import com.media.api.model.Show;

import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;

public class PlexApi implements MediaApi {

	private String mToken;
	
	public PlexApi(String token) {
		mToken = token;
	}
	
	private HttpGet createGet(String url) {
		if (mToken != null && mToken.length() > 0) {
			HttpGet req = new HttpGet(url);
			req.addHeader("X-Plex-Token", mToken);
			req.addHeader("X-Plex-Platform", "Android");
			// TODO: provide the below info
			req.addHeader("X-Plex-Platform-Version", "");
			req.addHeader("X-Plex-Device", "");
			req.addHeader("X-Plex-Client-Identifier", "");
			return req;
		}
		return new HttpGet(url);
	}
	
	public List<Section> getSections(String address) {
		HttpGet request = createGet(address + "/library/sections");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			System.err.println("Failed to connect to server.");
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		
		List<Section> sections = Section.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sections;
	}
	
	public List<Movie> getAllMoviesForSection(String address, int section) {
		HttpGet request = createGet(address + "/library/sections/" + section + "/all");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		List<Movie> movies = Movie.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("PlexAPI", "Found " + movies.size() + " movies.");
		
		return movies;
	}
	
	public Movie getMovieDetails(String address, String key) {
		HttpGet request = createGet(address + key);
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		List<Movie> movies = Movie.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return movies.get(0);
	}
	
	public List<Show> getAllShowsForSection(String address, int section) {
		HttpGet request = createGet(address +	"/library/sections/" + section + "/all");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		
		List<Show> shows = Show.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shows;
	}
	
	public List<Season> getSeasonsForShow(String address, int show) {
		HttpGet request = createGet(address + "/library/metadata/" + show + "/children");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		List<Season> seasons = Season.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return seasons;
	}
	
	public List<Episode> getEpisodesForSeason(String address, int season) {
		HttpGet request = createGet(address + "/library/metadata/" + season + "/children");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		List<Episode> episodes = Episode.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Log.d("PlexAPI", "Got Episodes: " + episodes.toString());
		
		return episodes;
	}
}
