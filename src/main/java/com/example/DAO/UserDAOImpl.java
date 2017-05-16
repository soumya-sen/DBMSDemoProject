package com.example.DAO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


import com.example.model.User;
import com.example.model.Votes;

public class UserDAOImpl implements UserDAO {
	private JdbcTemplate jdbcTemplate;

	
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
   

	@Override
	public User getUser(User user) {
		final User userOut = new User();
        String quer = "SELECT * from USERDETAILS where username = '" + user.getEmail() + "' and password = '" + user.getPwd() + "'";
        return (User) jdbcTemplate.query(quer, new ResultSetExtractor<User>() {
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                	userOut.setCountry(rs.getString(1));
                	userOut.setFullName(rs.getString(2));
                	userOut.setAge(rs.getString(3));
                	userOut.setPoliticalPreference(rs.getString(4));
                	userOut.setEmailId(rs.getString(5));
                	userOut.setPassword(rs.getString(6));
                }
                System.out.println(userOut);
                return userOut;
            }
        });
	}
	
	@Override
	public User insertUser(User user) 
	{
		
        String query = "INSERT into USERDETAILS values(?,?,?,?,?,?)";
        jdbcTemplate.update(query, new Object[] {user.getCountry(),user.getFullName(), user.getAge(),
        		user.getPoliticalPreference(),user.getEmailId(), user.getPassword() 
        	});
   
        return user;
	}
	
	@Override
	public User insertUserQueries(User user) 
	{
		
        String query = "INSERT into Opinion values(?,?,?,?)";
        jdbcTemplate.update(query, new Object[] {user.getFullName(), user.getEmailId(),user.getPhoneNumber(),
        		user.getMessage()
        	});
   
        return user;
	}
	@Override
	public HashMap<String, String> getVotes()
	{
		String quer = "select sum(votes_dem), sum(votes_gop) from votes_2016";
		HashMap<String, String> hm = new HashMap<String,String>();
		 return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		 {
	            public HashMap<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException 
	            {
	            	 if (rs.next()) 
	            	 {
	            		 hm.put("Democrats",rs.getString(1));
	            		 hm.put("Republican", rs.getString(2));
	            	 }
	            	 return hm;
	            }
	            
		
		 });
	}
		 
		 @Override
		 public LinkedHashMap<String,ArrayList<String>> getVotesByStates()
		 {
String quer = "select T1.STATE, T1.DEM_2012, T1.GOP_2012, T2.DEM_2016,T2.GOP_2016 from (select V1.STATE, SUM(V1.VOTES_DEM) AS DEM_2012, SUM(V1.VOTES_GOP) as GOP_2012 from VOTES_2012 V1 group by V1.state order by V1.STATE asc) t1 inner join (select  V2.STATE, SUM(V2.VOTES_DEM) AS DEM_2016, SUM(V2.VOTES_GOP) as GOP_2016 from VOTES_2016 V2 group by V2.state order by V2.STATE asc) t2 on t1.state = t2.state";
		LinkedHashMap<String, ArrayList<String>> hm = new LinkedHashMap<String,ArrayList<String>>();
		return (LinkedHashMap<String, ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<LinkedHashMap<String, ArrayList<String>>>() 
		{
			 public LinkedHashMap<String, ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
	            {
				 while(rs.next())
				 {
					 ArrayList<String> al = new ArrayList<String>();
			            al.add(rs.getString(2));
			            al.add(rs.getString(3));
			            al.add(rs.getString(4));
			            al.add(rs.getString(5));
			            hm.put(rs.getString(1), al);
				 }
				
				 return hm;
	            }
				
		 });
		 
	}

		@Override
		public LinkedHashMap<String, ArrayList<String>> getAllegianceShift() 
		{
		
			String quer = "select STATE, DIFFERENCE_IN_2012, DIFFERENCE_IN_2016  from (select T1.STATE, T1.DEM_2012, T1.GOP_2012, T2.DEM_2016,T2.GOP_2016, DIFFERENCE_IN_2012, DIFFERENCE_IN_2016 from ((select V1.STATE, SUM(V1.VOTES_DEM) AS DEM_2012, SUM(V1.VOTES_GOP)  as GOP_2012, SUM(V1.VOTES_GOP) - SUM(V1.VOTES_DEM) AS DIFFERENCE_IN_2012  from VOTES_2012 V1 group by V1.state order by V1.STATE asc) T1 inner join (select  V2.STATE, SUM(V2.VOTES_DEM) AS DEM_2016, SUM(V2.VOTES_GOP) as GOP_2016, SUM(V2.VOTES_GOP) - SUM(V2.VOTES_DEM) AS DIFFERENCE_IN_2016  from VOTES_2016 V2 group by V2.state order by V2.STATE asc) T2 on t1.state = t2.state))where (DIFFERENCE_IN_2012<0 and DIFFERENCE_IN_2016>0) or (DIFFERENCE_IN_2012>0 and DIFFERENCE_IN_2016<0)"
					;
			LinkedHashMap<String, ArrayList<String>> hm = new LinkedHashMap<String,ArrayList<String>>();
			return (LinkedHashMap<String, ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<LinkedHashMap<String, ArrayList<String>>>() 
			{
				 public LinkedHashMap<String, ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
		            {
					 while(rs.next())
					 {
						 ArrayList<String> al = new ArrayList<String>();
				            al.add(rs.getString(2));
				            al.add(rs.getString(3));
				            
				            hm.put(rs.getString(1), al);
					 }
					 System.out.println(hm.keySet());
					 return hm;
		            }
					
			 });
		}

		@Override
		public User doYourAnalysis(User user) 
		{
		
			final User userOut = new User();
			String quer="";
			final String p = user.party;
			System.out.println(p);
			System.out.print(user.getState()+" "+user.getCounty());
				if(user.getCounty() == null)
				{
				quer ="select votes_2016.state, sum(votes_2012.votes_dem), sum(votes_2012.votes_gop), sum(votes_2016.votes_dem), "
						+ "sum(votes_2016.votes_gop) from votes_2012,votes_2016 where votes_2012.state=votes_2016.state and votes_2016.state= '" + user.getState()+"' group by votes_2016.state";
				}
		        		
				else
				quer = "SELECT votes_2016.state, votes_2012.votes_gop, votes_2016.votes_gop, votes_2012.votes_dem, votes_2016.votes_dem, votes_2016.county from votes_2012,votes_2016 where votes_2012.county=votes_2016.county and votes_2012.state=votes_2016.state and votes_2012.county=votes_2016.county and votes_2012.state=votes_2016.state and votes_2016.state= '" + user.getState() + 
		        		"' and votes_2016.county = '" + user.getCounty()+"'";
			return (User) jdbcTemplate.query(quer, new ResultSetExtractor<User>() {
	            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
	                if (rs.next()) 
	                {
	                	if(user.getCounty() == null)
	                	{
	                		user.setCountry(null);
	                		
	                	}
	                	else
	                		
	                	{	userOut.setCounty(rs.getString(6));
	                	
	                	}
	                	userOut.setState(rs.getString(1));
	                	userOut.setGOP2012(rs.getString(2));
	                	userOut.setGOP2016(rs.getString(3));
	                	userOut.setDemocrats2012(rs.getString(4));
	                	userOut.setDemocrats2016(rs.getString(5));
	                	
	                

	                }
	                System.out.println(userOut.getCounty()+ " " + userOut.getState()+" " + userOut.getDemocrats2012() +" "+userOut.getDemocrats2016()+ " "+userOut.getGOP2012() +" "+userOut.getGOP2016());
	                return userOut;
	            }
	        });

		}
		
		@Override
		public User voters1(User user) 
		{
		
			final User userOut = new User();
			String query="";
			
			final String p = user.party;
			System.out.println(p);
			System.out.print(user.getState());
			
		
		query ="select voters2012.sex_female,  voters2016.sex_female, voters2012.sex_male,voters2016.sex_male, voters2012.race_hispanic,voters2016.race_hispanic, voters2012.race_whites,voters2016.race_whites,voters2012.race_asians,voters2016.race_asians,voters2012.race_black,voters2016.race_black,voters2012.age_above65,voters2016.age_above65,voters2012.age_above18_below65,voters2016.age_above18_below65from voters2012, voters2016 where voters2012.state = voters2016.state and voters2012.state = '" + user.getState()+"'";
					
		
			return (User) jdbcTemplate.query(query, new ResultSetExtractor<User>() {
	            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
	                if (rs.next()) 
	                {
	                	
	                	userOut.setFemale2012(rs.getString(1));
	                	userOut.setFemale2016(rs.getString(2));
	                	userOut.setMale2012(rs.getString(3));
	                	userOut.setMale2016(rs.getString(4));
	                	userOut.setraceHispanic2012(rs.getString(5));
	                	userOut.setraceHispanic2016(rs.getString(6));
	                	userOut.setraceAsian2012(rs.getString(9));
	                	userOut.setraceAsian2016(rs.getString(10));
	                	userOut.setraceBlack2012(rs.getString(11));
	                	userOut.setraceBlack2016(rs.getString(12));
	                	userOut.setraceWhite2012(rs.getString(7));
	                	userOut.setraceWhite2016(rs.getString(8));
	                	userOut.set65age2012(rs.getString(13));
	                	userOut.set65age2016(rs.getString(14));
	                	userOut.set18bet652012(rs.getString(15));
	                	userOut.set18bet652016(rs.getString(16));
	                	
	                

	                }
	                System.out.println(userOut.getCounty()+ " " + userOut.getState()+" " + userOut.getDemocrats2012() +" "+userOut.getDemocrats2016()+ " "+userOut.getGOP2012() +" "+userOut.getGOP2016());
	                return userOut;
	            }
	        });
			
			

		}

		@Override
		public LinkedHashMap FloridaCounty() 
		{
		
			String quer = "select county, difference1, difference2 from fl_county";
			LinkedHashMap<String, ArrayList<String>> hm = new LinkedHashMap<String,ArrayList<String>>();
			return (LinkedHashMap<String, ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<LinkedHashMap<String, ArrayList<String>>>() 
			{
				 public LinkedHashMap<String, ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
		            {
					 while(rs.next())
					 {
						 ArrayList<String> al = new ArrayList<String>();
				            al.add(rs.getString(2));
				            al.add(rs.getString(3));
				            
				            hm.put(rs.getString(1), al);
					 }
					 System.out.println(hm.keySet());
					 return hm;
		            }
					
			 });
		}
		@Override
		public LinkedHashMap<String, String> getTopTweetsByHilary() 
		{
		
			String quer = "select rownum, text from (select text, retweets from tweet_details where handle_name='HillaryClinton' order by retweets desc) where rownum<8";
					
			LinkedHashMap<String, String> hm = new LinkedHashMap<String,String>();
			return (LinkedHashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<LinkedHashMap<String, String>>() 
			{
				 public LinkedHashMap<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException 
		            {
		            	 while (rs.next()) 
		            	 {
		            		 hm.put(rs.getString(1), rs.getString(2));
		            	 }
		            	 System.out.println(hm.keySet());
		            	 return hm;
		            }
					
			 });
		}
		
		@Override
		public LinkedHashMap<String, String> getNoOfCandidates() 
		{
		
			String quer = "select (select count(*) from candidate_finance where (political_party = 'DEM' and (expenditure > (select avg(expenditure) from candidate_finance where political_party= 'DEM'))))  as DEM_Expenditure, (select count(*)as REP_EXPENDITURE from candidate_finance where (political_party = 'REP'  and (expenditure > (select avg(expenditure) from candidate_finance where political_party= 'REP'))))  as REP_Expenditure from Candidate_Finance where rownum = 1";
			LinkedHashMap<String, String> hm = new LinkedHashMap<String,String>();
			System.out.print("Entering here");
			return (LinkedHashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<LinkedHashMap<String, String>>() 
			{
				 public LinkedHashMap<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException 
		            {
		            	 while (rs.next()) 
		            	 {
		            		 hm.put("Democrats",rs.getString(1));
		            		 hm.put("Republican", rs.getString(2));
		            	 }
		            	 System.out.println(hm.keySet());
		            	 return hm;
		            }
					
			 });
		}
		
		@Override
		public LinkedHashMap<String, String> getNoOfStates() 
		{
		
			String quer = "select rownum,voters2016.state from voters2012, voters2016 where (((voters2016.AGE_ABOVE18_BELOW65 - voters2012.AGE_ABOVE18_BELOW65) > 0) and ((voters2016.age_above65 - voters2012.age_above65) > 0) and (voters2012.state = voters2016.state) and ((voters2016.sex_female - voters2012.sex_female)>0) and ((voters2016.sex_male - voters2012.sex_male)>0) and ((voters2016.race_hispanic - voters2012.race_hispanic)>0) and ((voters2016.race_whites - voters2012.race_whites)>0) and ((voters2016.race_asians - voters2012.race_asians)>0) and ((voters2016.race_black - voters2012.race_black)>0))";
			LinkedHashMap<String, String> hm = new LinkedHashMap<String,String>();
			System.out.print("Entering here");
			return (LinkedHashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<LinkedHashMap<String, String>>() 
			{
				 public LinkedHashMap<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException 
		            {
		            	 while (rs.next()) 
		            	 {
		            		 hm.put(rs.getString(1), rs.getString(2));
		            	 }
		            	 System.out.println(hm.keySet());
		            	 return hm;
		            }
					
			 });
		}
		

		@Override
		public LinkedHashMap<String, ArrayList<String>> getTotalExpenditure() 
		{
			 String quer = "select rownum,DEM_EXPENDITURE1, REP_EXPENDITURE1 from (select DEM_EXPENDITURE1, REP_EXPENDITURE1  from (select sum(Expenditure) as DEM_EXPENDITURE1, Year  from candidate_finance where Political_Party ='DEM' and Year = 2016  group by Political_Party, year) T1 inner join(select sum(Expenditure) as REP_EXPENDITURE1, Year from candidate_finance where Political_Party = 'REP' and Year = 2016  group by Political_Party, year) T2 on T1.year=T2.year union select DEM_EXPENDITURE2,REP_EXPENDITURE2  from (select sum(Expenditure) as DEM_EXPENDITURE2,Year  from candidate_finance where Political_Party ='DEM' and Year = 2012  group by Political_Party, Year) T3 inner join(select sum(Expenditure) as REP_EXPENDITURE2, Year from candidate_finance where Political_Party = 'REP' and Year = 2012  group by Political_Party, Year) T4 on T3.year=T4.year)";
			 LinkedHashMap<String, ArrayList<String>> hm = new LinkedHashMap<String,ArrayList<String>>();
			 System.out.println("Entering at Expenditure ");
				return (LinkedHashMap<String, ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<LinkedHashMap<String, ArrayList<String>>>() 
				{
					 public LinkedHashMap<String, ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
			            {
						 while(rs.next())
						 {
							 ArrayList<String> al = new ArrayList<String>();
					            al.add(rs.getString(2));
					            al.add(rs.getString(3));
					            
					            hm.put(rs.getString(1), al);
						 }
						 System.out.println(hm.keySet());
						 return hm;
			            }
						
				 });
			}


}
		



 
