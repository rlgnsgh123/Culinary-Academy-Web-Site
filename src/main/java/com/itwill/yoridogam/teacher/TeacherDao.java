package com.itwill.yoridogam.teacher;

import java.util.List;


public interface TeacherDao {
	/*
	 * INSERT 강사가입 (사용자생성)
	 */
	int create(Teacher teacher) throws Exception;
	/*
	 * UPDATE 강사 정보 수정 (회원수정)
	 */
	int update(Teacher teacher) throws Exception;
	/*
	 * SELECT_BY_ID 강사정보 상세 조회
	 */
	Teacher findTeacher(String t_id) throws Exception;
	
	/*
	 * REMOVE 회원정보 삭제
	 */
	Teacher remove(String t_id) throws Exception;
	
	/*
	 * SELECT ALL강사전체리스트 출력
	 */
	List<Teacher> findMemberList() throws Exception;
	
	/*
	 * isExisted 아이디 중복검사
	 */
	boolean existedMember(Teacher t_id) throws Exception;
}