package cse.java2.project.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cse.java2.project.model.*;
import cse.java2.project.repository.DAO;

@Service
public class WebService {

	private final DAO dao;

	@Autowired
	public WebService(DAO dao) {
		this.dao = dao;
		this.dao.openDatasource();
	}

	public int[] getAnsweredQuestionCnt() {
		return dao.getAnsweredQuestionCnt();
	}

	public int[] getSolvedQuestionCnt() {
		return dao.getSolvedQuestionCnt();
	}

	public int[] getNotACMostUpvoteQuestionCnt() {
		return dao.getNotACMostUpvoteQuestionCnt();
	}

	public int getThreadUsers() {
		return dao.getThreadUsers();
	}

	public int getAnswerUsers() {
		return dao.getAnswerUsers();
	}

	public int getCommentUsers() {
		return dao.getCommentUsers();
	}

	public List<Integer> gettimeDistribution() {
		return dao.gettimeDistribution();
	}

	public List<Integer> getAnswerDistrubution() {
		return dao.getAnswerDistrubution();
	}

	public Map<String, Integer> getTags() {
		return dao.getTags();
	}

	public Map<String, Integer> getCombUpvote() {
		return dao.getCombUpvote();
	}

	public Map<String, Integer> getCombView() {
		return dao.getCombView();
	}

	@PreDestroy
	public void closeDataSource() {
		dao.closeDatasource();
	}
}
