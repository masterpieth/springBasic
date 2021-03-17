package com.basic.basic.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.basic.basic.service.UserService;
import com.basic.basic.util.Criteria;
import com.basic.basic.util.PageMaker;

@Controller
public class BoardController {

	@Autowired
	private UserService userService;
	
	private final int NAV_PAGE_NUM = 10;
	
	@RequestMapping(value = "/intro/board", method = {RequestMethod.GET, RequestMethod.POST})
	public String board(@RequestParam HashMap<String, Object> params, Model model) {
		
		Criteria cri;
		PageMaker pm;
		
		List<HashMap<String, Object>> userList;
		
		int totalCount = 0;
		
		if(params.get("curPage") != null) {
			int curPage = Integer.parseInt((String)params.get("curPage"));
			int perPageNum = Integer.parseInt((String)params.get("perPageNum"));
			
			cri = new Criteria(curPage, perPageNum);
		} else {
			cri = new Criteria();
		}
		
		try {
			totalCount = userService.selectUsersTotalCount(params);
			
			pm = new PageMaker(cri, totalCount, NAV_PAGE_NUM);
			
			params.put("startRows", pm.getCri().getStartRows());
			params.put("perPageNum", pm.getCri().getPerPageNum());
			
			userList = userService.selectUsers(params);
			
			model.addAttribute("pageMaker", pm);
			model.addAttribute("userList", userList);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "오류가 발생했습니다.");
		}
		
		return "/intro/board";
	}
	
	@RequestMapping(value = "/intro/boardDetail", method = RequestMethod.POST)
	public String boardDetail(@RequestParam HashMap<String, Object> params, Model model) {
		
		model.addAttribute("search", params);
		
		List<HashMap<String, Object>> userList;
		HashMap<String, Object> userInfo;
		try {
			userList = userService.selectUsers(params);
			userInfo = userList.get(0);
			model.addAttribute("detail", userInfo);
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "오류가 발생했습니다.");
		}
		
		return "/intro/boardDetail";
	}
}
