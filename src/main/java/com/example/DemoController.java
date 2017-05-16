package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.UserBean;
import com.example.model.User;

@RestController
class DemoController2{
	
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
	UserBean userBean = (UserBean) applicationContext.getBean("userBean");
	
	@RequestMapping(value = "/login",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public User secondPage(@RequestBody User user){
		User userOutput = userBean.getUser(user);
		return userOutput;
	}
	
	@RequestMapping(value = "/signUp",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public User signUp(@RequestBody User user){
		User userOutput = userBean.insertUser(user);
		return userOutput;
	}
		@RequestMapping(value = "/openHome",method=RequestMethod.GET, consumes="application/json",produces="application/json")
		public User openHome(@RequestBody User user){
			User userOutput = userBean.getUser(user);
			return userOutput;
		}
			
			@RequestMapping(value = "/contactUs",method=RequestMethod.POST, consumes="application/json",produces="application/json")
			public User contactUs(@RequestBody User user)
			{
				User userOutput = userBean.insertUserQueries(user);
				System.out.println(userOutput.getMessage());
				return userOutput;
			}
				
			@RequestMapping(value = "/getVotes",method=RequestMethod.POST,produces="application/json")
				public HashMap<String,String> getVotes()
				{
					return userBean.getVotes();
				}
				
			@RequestMapping(value = "/getVotesByStates",method=RequestMethod.POST,produces="application/json")
			public LinkedHashMap<String,ArrayList<String>> getVotesByStates()
			{
				return userBean.getVotesByStates();
			}
			
			@RequestMapping(value = "/getAllegianceShift",method=RequestMethod.POST,produces="application/json")
			public LinkedHashMap<String,ArrayList<String>> getAllegianceShift()
			{
				return userBean.getAllegianceShift();
			}
			
			@RequestMapping(value = "/doYourAnalysis",method=RequestMethod.POST, consumes="application/json",produces="application/json")
			public User doYourAnalysis(@RequestBody User user){
				return userBean.doYourAnalysis(user);
				
			}
			
			@RequestMapping(value = "/voters1",method=RequestMethod.POST, consumes="application/json",produces="application/json")
			public User voters1(@RequestBody User user){
				return userBean.voters1(user);
				
			}
			
			@RequestMapping(value = "/FloridaCounty",method=RequestMethod.POST,produces="application/json")
			public LinkedHashMap<String, ArrayList<String>> FloridaCounty(){
				return userBean.FloridaCounty();
				
			}
			
			@RequestMapping(value = "/getTopTweetsByHilary",method=RequestMethod.POST,produces="application/json")
			public LinkedHashMap<String, String> getTopTweetsByHilary(){
				return userBean.getTopTweetsByHilary();
				
			}
			
			@RequestMapping(value = "/getNoOfCandidates",method=RequestMethod.POST,produces="application/json")
			public LinkedHashMap<String, String> getNoOfCandidates(){
				return userBean.getNoOfCandidates();
				
			}
			
			@RequestMapping(value = "/getNoOfStates",method=RequestMethod.POST,produces="application/json")
			public LinkedHashMap<String, String> getNoOfStates(){
				return userBean.getNoOfStates();
				
			}
			@RequestMapping(value = "/getTotalExpenditure",method=RequestMethod.POST,produces="application/json")
			public LinkedHashMap<String, ArrayList<String>> getTotalExpenditure(){
				return userBean.getTotalExpenditure();
				
			}
			
			
				
	}
