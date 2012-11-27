package com.media.api.base;

import java.util.List;

import com.media.api.model.Episode;
import com.media.api.model.Movie;
import com.media.api.model.Season;
import com.media.api.model.Section;
import com.media.api.model.Show;

public interface MediaApi {
	
	List<Section> getSections(String address);
	
	List<Movie> getAllMoviesForSection(String address, int section);
	
	Movie getMovieDetails(String address, String key);
	
	List<Show> getAllShowsForSection(String address, int section);
	
	List<Season> getSeasonsForShow(String address, int show);
	
	List<Episode> getEpisodesForSeason(String address, int season);
}
