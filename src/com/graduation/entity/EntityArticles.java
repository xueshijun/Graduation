package com.graduation.entity;

public class EntityArticles {
	 private int ArticleID;
     private int ClassID;
    private String ClassName;//Õ‚±Ì
    private String Title;
    private String KeyWords;
    private String Desription;
    private String Contents;
    private String PubDate;
    private int ViewTimes;
    private String Author;
	public int getArticleID() {
		return ArticleID;
	}
	public void setArticleID(int articleID) {
		ArticleID = articleID;
	}
	public int getClassID() {
		return ClassID;
	}
	public void setClassID(int classID) {
		ClassID = classID;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getKeyWords() {
		return KeyWords;
	}
	public void setKeyWords(String keyWords) {
		KeyWords = keyWords;
	}
	public String getDesription() {
		return Desription;
	}
	public void setDesription(String desription) {
		Desription = desription;
	}
	public String getContents() {
		return Contents;
	}
	public void setContents(String contents) {
		Contents = contents;
	}
	public String getPubDate() {
		return PubDate;
	}
	public void setPubDate(String pubDate) {
		PubDate = pubDate;
	}
	public int getViewTimes() {
		return ViewTimes;
	}
	public void setViewTimes(int viewTimes) {
		ViewTimes = viewTimes;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
    
    
}
