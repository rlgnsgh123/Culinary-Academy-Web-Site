package com.itwill.yoridogam.controller;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.yoridogam.controller.interceptor.LoginCheck;
import com.itwill.yoridogam.member.Member;
import com.itwill.yoridogam.member.MemberService;
import com.itwill.yoridogam.product.ProductService;
import com.itwill.yoridogam.productTime.ProductTime;
import com.itwill.yoridogam.productTime.ProductTimeDao;
import com.itwill.yoridogam.productTime.productTimeService;
import com.itwill.yoridogam.reservation.ReservationService;

@Controller
public class RsvController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private productTimeService productTimeService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ProductService productService;
	
	//상품 디테일에서 강의 결제 누르면 오프라인 예약화면
	//view
	@LoginCheck
	@RequestMapping("rsv_form")
	public String rsv_form(HttpSession session, Model model) throws Exception {
		String sUserId = (String)session.getAttribute("sUserId"); //회원 아이디
		int p_no =1;
		//int p_no = (int)session.getAttribute("p_no"); // 상품 넘버
		int pt_no =1;
		//int pt_no = (int)session.getAttribute("pt_no"); // 상품 예약 시간
		int qty =1;
		//int qty = (int)session.getAttribute("qty"); // 예약 인원 받기
		
		model.addAttribute("sUserId", memberService.findMember(sUserId));// 고객정보 보여줄때
		model.addAttribute("product", productService.selectByNo(p_no)); // 예약한 상품 보여줄때
		model.addAttribute("productTime", productTimeService.selectByNo(pt_no)); // 예약한 시간 보여줄때
		model.addAttribute("qty",qty);
		//상품
		return "rsv_form";
	}
	/*
	//오프라인 강의 예약하기 insert
	@LoginCheck
	@PostMapping(value = "rsv_action")
	public String rsv_action_post(HttpSession session)throws Exception {
		String sUserId = (String)session.getAttribute("sUserId"); // 회원아이디
		
		reservationService.insert(reservation, productTime, sUserId);
		return "";
	}
	*/
	
	//예약 성공 화면
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}