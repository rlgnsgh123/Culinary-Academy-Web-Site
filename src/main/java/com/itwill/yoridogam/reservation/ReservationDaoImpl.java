package com.itwill.yoridogam.reservation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ReservationDao")
public class ReservationDaoImpl implements ReservationDao{
	
	public static final String NAMESPACE="com.itwill.yoridogam.mapper.ReservationMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int create(Reservation reservation) throws Exception {
		
		return sqlSession.insert(NAMESPACE+"create",reservation);
	}

	@Override
	public List<Reservation> selectAll(String sUserId) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+"selectAll",sUserId);
	}

	@Override
	public Reservation selectById(Reservation reservation) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+"selectById",reservation);
	}

	@Override
	public int deleteById(Reservation reservation) throws Exception {
		
		return sqlSession.delete(NAMESPACE+"deleteById",reservation);
	}

	@Override
	public int deleteAll(String sUserId) throws Exception {
		
		return sqlSession.delete(NAMESPACE+"deleteAll",sUserId);
	}

}