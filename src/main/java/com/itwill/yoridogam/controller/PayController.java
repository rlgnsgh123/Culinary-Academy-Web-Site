package com.itwill.yoridogam.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.yoridogam.controller.interceptor.LoginCheck;
import com.itwill.yoridogam.member.Member;
import com.itwill.yoridogam.member.MemberService;
import com.itwill.yoridogam.pay.cart.Cart;
import com.itwill.yoridogam.pay.cart.CartService;
import com.itwill.yoridogam.pay.pay.Pay;
import com.itwill.yoridogam.pay.pay.PayService;
import com.itwill.yoridogam.pay.payItem.Pay_item;
import com.itwill.yoridogam.product.Product;
import com.itwill.yoridogam.product.ProductService;

@Controller
public class PayController {
	
	@Autowired
	private PayService payService;
	@Autowired
	private CartService cartService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ProductService productService;
	
	/*********************************************************************
	 강의상세->결제 페이지
	 *********************************************************************/
	@LoginCheck
	@RequestMapping("pay_form")
	//@PostMapping("pay_form")
	public String pay_form(HttpSession session, Model model) throws Exception{
	//public String pay_form(HttpSession session, int p_no, int qty) throws Exception{
		String sUserId=(String)session.getAttribute("sUserId");
		int qty=1;//test
		int p_no=1;//test
		session.setAttribute("qty",qty);
		model.addAttribute("sUserId", memberService.findMember(sUserId));
		model.addAttribute("product", productService.selectByNo(p_no));
		return "pay_form";
	}
	
	/*********************************************************************
	 장바구니->결제 페이지
	 *********************************************************************/
	@LoginCheck
	@RequestMapping("pay_form_cart")
	//@PostMapping("pay_form_cart")
	public String pay_form_fromCart(@RequestParam(value="ci_no") Integer[] ci_noArray,HttpSession session, Model model) throws Exception{
		String sUserId=(String)session.getAttribute("sUserId");
		List<Cart> cList=new ArrayList<>();
		for(int i=0; i<ci_noArray.length; i++) {
			cList.add(cartService.selectCartItem(ci_noArray[i]));
			cList.get(i).setProduct(productService.selectByNo(cList.get(i).getProduct().getP_no()));
		}
		//간결하게 다시...
		model.addAttribute("sUserId", memberService.findMember(sUserId));
		model.addAttribute("cartList", cList);
		return "pay_form_cart";
	}
	
	/*********************************************************************
	 결제
	 *********************************************************************/
	@LoginCheck
	@PostMapping("pay_action")
	public String pay_action_post(Pay pay,Member member,int qty,int p_no,HttpSession session, Model model) throws Exception{
		pay.setMember(member);
		int pay_no=payService.createPay(pay,qty,p_no);
		model.addAttribute("pay",payService.findPayDetailByNo(pay_no));
		return "pay_complete";
	}
	
	/*********************************************************************
	 장바구니 결제
	 *********************************************************************/
	@LoginCheck
	@PostMapping("pay_action_cart")
	public String pay_action_cart_post(Pay pay,Member member,int qty,int p_no,HttpSession session, Model model) throws Exception{
		pay.setMember(member);
		int pay_no=payService.createPayFromCart(pay,pay.getMember().getM_id());
		model.addAttribute("pay",payService.findPayDetailByNo(pay_no));
		return "pay_complete";
	}
	
	/*********************************************************************
	 결제 완료 후 결제정보페이지
	 *********************************************************************/
	@LoginCheck
	@RequestMapping(value = "pay_complete_form")
	//@PostMapping("pay_complete_form")
	public String pay_complete_form()throws Exception {
		return "pay_complete";
	}
	
	/*********************************************************************
	 결제 내역
	 *********************************************************************/
	@LoginCheck
	@RequestMapping(value = "pay_list_form")
	public String pay_list_form(HttpSession session,Model model)throws Exception {
		String sUserId=(String)session.getAttribute("sUserId");
		model.addAttribute("payList", payService.findPayDetailList(sUserId));
		return "pay_list_form";
	}
	
	/*********************************************************************
	 결제 내역 한 건 삭제
	 *********************************************************************/
	@LoginCheck
	@RequestMapping(value = "pay_delNo_action")
	public String pay_delNo_action(HttpSession session, int pay_no)throws Exception {
		payService.deletePayByNo(pay_no);
		return "pay_list_form";
	}
	
	/*********************************************************************
	 결제 내역 전체 삭제
	 *********************************************************************/
	@LoginCheck
	@RequestMapping(value = "pay_delAll_action")
	public String pay_delAll_action(HttpSession session)throws Exception {
		String sUserId=(String)session.getAttribute("sUserId");
		payService.deletePay(sUserId);
		return "pay_list_form";
	}
	
	/*********************************************************************
	 결제 내역 상세 보기(강의정보, 주문자, 결제정보)
	 *********************************************************************/
	@LoginCheck
	@RequestMapping(value = "pay_detail_form")
	public String pay_detail_form(HttpSession session,int pay_no)throws Exception {
		payService.findPayDetailByNo(pay_no);
		return "pay_list_form";
	}
}
