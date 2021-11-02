package com.itwill.yoridogam.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.yoridogam.notice.page.PagingVO;
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public int insertNoti(Notice notice) {
		noticeDao.insertNoti(notice);
		return notice.getNoti_no();
	}

	@Override
	public int updateNoti(Notice notice) {
		noticeDao.updateNoti(notice);
		return notice.getNoti_no();
	}

	@Override
	public int deleteNoti(int noti_no) {
		return noticeDao.deleteNoti(noti_no);
	}

	@Override
	public List<Notice> notiList() {
		return noticeDao.notiList();
	}

	@Override
	public Notice selectByNotiNo(int noti_no) {
		return noticeDao.selectByNotiNo(noti_no);
	}

	@Override
	public boolean updateNotiViewCount(int noti_no) {
		boolean result = false;
		result = noticeDao.updateNotiViewCount(noti_no);
		return result;
	}
	/*************** 공지사항 총 개수 ***************/
	@Override
	public int listCount() {
		return noticeDao.listCount();
	}
	/*************** 공지사항 페이징 처리 게시글 목록 조회 ***************/
	@Override
	public List<PagingVO> noticePageList(PagingVO vo) {
		return noticeDao.noticePageList(vo);
	}

	

	

}
