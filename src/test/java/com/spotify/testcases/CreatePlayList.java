package com.spotify.testcases;

import static com.spotify.base.SpecBuilder.getRequestSpec;
import static com.spotify.base.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.spotify.api.PlayListAPI.*;

import org.testng.annotations.Test;

import com.spotify.pojo.ErrorRoot;
import com.spotify.pojo.PlayList;
import com.utility.PropertiesReader;

import io.restassured.response.Response;

public class CreatePlayList {

	String fileName = "Config";

	String playListID = "";
	String newReqName = "";
	String newReqDesc = "";
	boolean newReqPublic = false;
	
	@Test(priority = 1)
	public void createPlayListBodyAsAString() {
		PlayList reqPlayList = new PlayList();
		reqPlayList.setName("Mathan Playlist For Batch 3 using pojo class");
		reqPlayList.setDescription("Mathan playlist description for Batch 3 using pojo class");
		reqPlayList.setPublic(false);
		
		Response response = postCreatePlaylist(reqPlayList);
		
		PlayList responsePlayList = response.as(PlayList.class);
		
		assertThat(response.getStatusCode(), equalTo(201));
		assertThat(responsePlayList.getName(), equalTo(reqPlayList.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(reqPlayList.getDescription()));
		assertThat(responsePlayList.getPublic(), equalTo(reqPlayList.getPublic()));
		playListID = responsePlayList.getId();
		newReqName = reqPlayList.getName();
		newReqDesc = reqPlayList.getDescription();
		newReqPublic = reqPlayList.getPublic();
		System.out.println("Play List ID : "+playListID);
	}
	
	@Test(priority = 2)
	public void updatePlayListBodyAsAString() {
		PlayList reqPlayList = new PlayList();
		reqPlayList.setName("Mathan Playlist For Batch 3 using pojo class");
		reqPlayList.setDescription("Mathan playlist description for Batch 3 using pojo class");
		reqPlayList.setPublic(false);
		Response response = editPlaylist(reqPlayList, playListID);
		assertThat(response.getStatusCode(), equalTo(200));
	}
	
	@Test(priority = 3)
	public void getAllPlayListBasedOnUserID() {
		Response response = getPlayList(playListID);
		PlayList responsePlayList = response.as(PlayList.class);
		
		assertThat(response.getStatusCode(), equalTo(200));
		assertThat(responsePlayList.getName(), equalTo(newReqName));
		assertThat(responsePlayList.getDescription(), equalTo(newReqDesc));
		assertThat(responsePlayList.getPublic(), equalTo(newReqPublic));
	}
	
	@Test(priority = 1)
	public void createPlayListWithinvalidPayload() {
		PlayList playList = new PlayList();
		playList.setName("");
		playList.setDescription("Mathan playlist description for Batch 3 using pojo class");
		playList.setPublic(false);
		
		Response response = postCreatePlaylist(playList);
		ErrorRoot responseError = response.as(ErrorRoot.class);
		assertThat(responseError.getError().getStatus(), equalTo(400));
		assertThat(responseError.getError().getMessage(), equalTo("Missing required field: name"));
	}
	
}
