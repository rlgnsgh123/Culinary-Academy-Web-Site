package com.itwill.yoridogam.controller;

import java.util.ArrayList;
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
	
	@LoginCheck
	@PostMapping("pay_form")
	public String pay_form(HttpSession session, Model model) throws Exception{
	//public String pay_form(HttpSession session, @RequestParam("p_no") int p_no, @RequestParam("pi_qty") int pi_qty) throws Exception{
		String sUserId=(String)session.getAttribute("sUserId");
		int pQty=1;//test
		int p_no=1;//test
		session.setAttribute("qty",pQty);
		model.addAttribute("sUserId", memberService.findMember(sUserId));
		model.addAttribute("product", productService.selectByNo(p_no));
		return "pay_form";
	}
	
	@LoginCheck
	@PostMapping("pay_form_cart")
	public String pay_form_fromCart(HttpSession session, Model model) throws Exception{
		String sUserId=(String)session.getAttribute("sUserId");
		List<Cart> cList=cartService.cartList(sUserId);
		model.addAttribute("sUserId", memberService.findMember(sUserId));
		model.addAttribute("cartList", cList);
		return "pay_form_cart";
	}
	
	@LoginCheck
	@PostMapping("pay_action")
	public String pay_action_post(Pay pay,Member member,int qty,int p_no,HttpSession session, Model model) throws Exception{
		member.setM_id((String)session.getAttribute("sUserId"));
		pay.setMember(member);
		int pay_no=payService.createPay(pay,qty,p_no);
		model.addAttribute("pay",payService.findPayDetailByNo(pay_no));
		model.addAttribute("member",member);
		System.out.println(pay);
		return "pay_complete";
	}
	
	
	@LoginCheck
	@PostMapping("pay_action_cart")
	public String pay_action_cart_post(Pay pay,Member member,int qty,int p_no,HttpSession session, Model model) throws Exception{
		//상기 action과 일원화 예정...
		String sUserId=(String)session.getAttribute("sUserId");
		member.setM_id(sUserId);
		pay.setMember(member);
		int pay_no=payService.createPayFromCart(pay,sUserId);
		model.addAttribute("pay",payService.findPayDetailByNo(pay_no));
		model.addAttribute("member",member);
		return "pay_complete";
	}
	
	@LoginCheck
	@RequestMapping(value = "pay_complete_form")
	public String pay_complete_form(Model model)throws Exception {
		return "pay_complete";
	}
	
	@LoginCheck
	@RequestMapping(value = "pay_list_form")
	public String pay_list_form(HttpSession session,Model model)throws Exception {
		String sUserId=(String)session.getAttribute("sUserId");
		model.addAttribute("payList", payService.findPayDetailList(sUserId));
		return "pay_list_form";
	}
	
	@LoginCheck
	@RequestMapping(value = "pay_delNo_action")
	public String pay_delNo_action(HttpSession session, int pay_no)throws Exception {
		String sUserId=(String)session.getAttribute("sUserId");
		payService.deletePayByNo(pay_no);
		return "pay_list_form";
	}
	
	@LoginCheck
	@RequestMapping(value = "pay_delAll_action")
	public String pay_delAll_action(HttpSession session)throws Exception {
		String sUserId=(String)session.getAttribute("sUserId");
		payService.deletePay(sUserId);
		return "pay_list_form";
	}
	
	@LoginCheck
	@RequestMapping(value = "pay_detail_form")
	public String pay_detail_form(HttpSession session,int pay_no)throws Exception {
		String sUserId=(String)session.getAttribute("sUserId");
		payService.findPayDetailByNo(pay_no);
		return "pay_list_form";
	}
}
