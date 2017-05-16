package com.example.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.example.model.User;
import com.example.model.Votes;

public interface UserDAO {
	User getUser(User user);
	User insertUser(User user);
	User insertUserQueries(User user);
	HashMap<String,String> getVotes();
	LinkedHashMap<String, ArrayList<String>> getVotesByStates();
	LinkedHashMap<String, ArrayList<String>> getAllegianceShift();
	User doYourAnalysis(User user);
	
	User voters1(User user);
	LinkedHashMap<String, ArrayList<String>> FloridaCounty();
	LinkedHashMap<String, String> getTopTweetsByHilary();
	LinkedHashMap<String, String> getNoOfCandidates();
	LinkedHashMap<String, String> getNoOfStates();
	LinkedHashMap<String, ArrayList<String>> getTotalExpenditure();
}