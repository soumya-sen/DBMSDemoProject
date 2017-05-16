package com.example.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.DAO.UserDAO;
import com.example.model.User;
import com.example.model.Votes;

public class UserBean {
	@Autowired
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUser(User user) {
		return userDAO.getUser(user);
	}
	
	public User insertUser(User user){
		return userDAO.insertUser(user);
	}

	public User insertUserQueries(User user)
	{
		return userDAO.insertUserQueries(user);
	}

	public HashMap<String, String> getVotes() 
	{
		
		return userDAO.getVotes();
	}
	
	public LinkedHashMap<String, ArrayList<String>> getVotesByStates()
	{
		return userDAO.getVotesByStates();
	}
	
	public LinkedHashMap<String, ArrayList<String>> getAllegianceShift()
	{
		return userDAO.getAllegianceShift();
	}
	
	public User doYourAnalysis(User user)
	{
		return userDAO.doYourAnalysis(user);
	}
	
	

	public User voters1(User user) {
		// TODO Auto-generated method stub
		return userDAO.voters1(user);
	}

	public LinkedHashMap<String, ArrayList<String>> FloridaCounty() {
		
		return userDAO.FloridaCounty();
		
	}

	public LinkedHashMap<String, String> getTopTweetsByHilary() {
		// TODO Auto-generated method stub
	return userDAO.getTopTweetsByHilary();
	}

	public LinkedHashMap<String, String> getNoOfCandidates() 
	{
		return userDAO.getNoOfCandidates();
	}

	public LinkedHashMap<String, String> getNoOfStates() 
	{
		
		return userDAO.getNoOfStates();
		
	}

	public LinkedHashMap<String, ArrayList<String>> getTotalExpenditure() 
	{
			return userDAO.getTotalExpenditure();
	}

	



}
