package com.spotify.api;

import static com.wrapper.api.RestAssureHTTPWrapper.get;
import static com.wrapper.api.RestAssureHTTPWrapper.post;
import static com.wrapper.api.RestAssureHTTPWrapper.update;

import com.spotify.pojo.PlayList;
import com.utility.PropertiesReader;

import io.restassured.response.Response;

public class PlayListAPI {
	public static String fileName = "Config";
	public static String userid = PropertiesReader.getPropertyValue(fileName, "user_id");
	
	public static Response postCreatePlaylist(PlayList reqPlayList) {
		return post(reqPlayList, "/users/"+userid+"/playlists");
	}
	

	public static Response editPlaylist(PlayList reqPlayList,String playListID) {
		return update(reqPlayList, "/playlists/"+playListID);
		}
	
	public static Response getPlayList(String playListID) {
		return get("/playlists/"+playListID);
		}
	
}
