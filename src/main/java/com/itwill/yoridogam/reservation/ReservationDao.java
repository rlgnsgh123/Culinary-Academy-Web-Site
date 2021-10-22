package com.itwill.yoridogam.reservation;

import java.util.List;

public interface ReservationDao {
/*
 <insert> m_id 회원 오프라인 강의 예약
 */
	int create(Reservation reservation) throws Exception;
/*
 <selectAll> 회원 예약 전체 조회
 */
	List<Reservation> selectAll(String m_id) throws Exception;
/*
 <select> 회원 예약 특정 상세 조회
 */
	Reservation selectById(Reservation reservation) throws Exception;
/*
 <delete> 회원 특정 예약 취소 및 내역 삭제
 */
	int deleteById(Reservation reservation) throws Exception;
/*
 <delectAll> 회원 전체 예약 내역  삭제
 */
	int deleteAll(String sUserId) throws Exception;
}