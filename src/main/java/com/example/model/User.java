package com.example.model;

public class User {
	
	private String id;
	private String fullName;
	private String age;
	private String phoneNumber;
	private String emailId;
	private String email;
	private String password;
	private String pwd;
	private String country;
	private String state;
	private String politicalPreference;
	private String username;
	private String message; 
	private String county;
	public String party;
	private String Democrats2012;
	private String Democrats2016;
	private String GOP2012;
	private String GOP2016;
	private String race;
	private String gender;
	
	
	public String getId() 
	{
		return id;
	}
	
	public String getParty() 
	{
		return party;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() 
	{
		return fullName;
	}
	public String getPoliticalPreference()
	{
		return politicalPreference;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getCountry() {
		return country;
	}
	
	public String getEmailId() 
	{
		return emailId;
	}
	public void setEmailId(String emailId) 
	{
		this.emailId = emailId;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public String getPwd() {
		return pwd;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage() {
		this.message = message;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + fullName + ", age=" + age + ", phoneNumber=" + phoneNumber
				+ ", emailId=" + emailId + ", county="+county+", state="+state+", password=" + password + "]";
	}
	public void setCountry(String country) 
	{

		this.country = country;
	}
	
	public void setFullName(String fullName) 
	{

		this.fullName = fullName;
	}
	public void setState(String state) {
		this.state=state;
		
	}
	public void setPoliticalPreference(String politicalPreference) 
	{

		this.politicalPreference = politicalPreference;
		
	}
	public String getState() {
		return state;
	}
	public String getCounty() {
	
		return county;
	}
	public String getDemocrats2012() {
		
		return Democrats2012;
	}
public String getDemocrats2016() {
		
		return Democrats2016;
	}
	public String getGOP2012() {

		return GOP2012;
	}
	public String getGOP2016() {

		return GOP2016;
	}
	public void setCounty(String county)
	{
		this.county=county;
	}
	public void setDemocrats2012(String Democrats2012) 
	{
		this.Democrats2012=Democrats2012;
		
	}
	public void setDemocrats2016(String Democrats2016) 
	{
		this.Democrats2016=Democrats2016;
		
	}
	public void setGOP2012(String GOP2012) 
	{
		this.GOP2012=GOP2012;
		
	}
	public void setGOP2016(String GOP2016) 
	{
		this.GOP2016=GOP2016;
		
	}
	
	public String setMale2016(String male16)
	{
		return male16;
	}
	public String setMale2012(String male12)
	{
		return male12;
	}
	public String setFemale2016(String female16)
	{
		return female16;
	}
	public String setFemale2012(String female12)
	{
		return female12;
	}
	public String setraceAsian2016(String rasian16)
	{
		return rasian16;
	}
	public String setraceAsian2012(String rasian12)
	{
		return rasian12;
	}
	public String setraceHispanic2016(String rhispanic16)
	{
		return rhispanic16;
	}
	public String setraceHispanic2012(String rhispanic12)
	{
		return rhispanic12;
	}
	public String setraceWhite2016(String rwhite16)
	{
		return rwhite16;
	}
	public String setraceWhite2012(String rwhite12)
	{
		return rwhite12;
	}
	public String setraceBlack2016(String rblack16)
	{
		return rblack16;
	}
	public String setraceBlack2012(String rblack12)
	{
		return rblack12;
	}
	public String set65age2016(String aabove6516)
	{
		return aabove6516;
	}
	public String set65age2012(String aabove6512)
	{
		return aabove6512;
	}
	public String set18bet652016(String a18bet6516)
	{
		return a18bet6516;
	}

	public String set18bet652012(String a18bet6512) 
	{
		// TODO Auto-generated method stub
		return a18bet6512;
	}
	
	
	
	
	
	
	
}