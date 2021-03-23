package com.basic.basic.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.basic.service.UserService;
import com.basic.basic.util.Criteria;
import com.basic.basic.util.PageMaker;
import com.google.gson.Gson;

@Controller
public class BoardController {

	@Autowired
	private UserService userService;
	
	private final int NAV_PAGE_NUM = 10;
	
	@RequestMapping(value = "/intro/board", method = {RequestMethod.GET, RequestMethod.POST})
	public String board(@RequestParam HashMap<String, Object> params, Model model) {
		
		Criteria cri = null;
		PageMaker pm = null;
		
		List<HashMap<String, Object>> userList = null;
		
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
		
		List<HashMap<String, Object>> userList = null;
		HashMap<String, Object> userInfo = null;
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
	
	//페이지 이동 컨트롤러
	@RequestMapping(value = "/intro/boardJsVer", method = RequestMethod.GET)
	public String boardJsVer() {
		return "/intro/boardJsVer";
	}
	//리스트 데이터 컨트롤러
	@ResponseBody
	@RequestMapping(value = "/intro/boardList", method = RequestMethod.POST)
	public Object boardList(@RequestParam HashMap<String, Object> params, Model model) {
		
		model.addAttribute("search", params);
		
		Criteria cri;
		List<HashMap<String, Object>> userList = null;
		int totalCount = 0;
		
		HashMap<String, Object> pagination = new HashMap<String, Object>();
		
		if(params.get("startPage") != null) {
			int startPage = Integer.parseInt((String)params.get("startPage"));
			int perPageNum = Integer.parseInt((String)params.get("perPageNum"));
			
			cri = new Criteria(startPage, perPageNum);
		} else {
			cri = new Criteria();
		}
		
		try {
			params.put("startRows", cri.getStartRows());
			params.put("perPageNum", cri.getPerPageNum());
			
			userList = userService.selectUsers(params);
			
			totalCount = userService.selectUsersTotalCount(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		pagination.put("totalCount", totalCount);
		pagination.put("dataList", userList);
		
		return pagination;
	}
	
	@RequestMapping(value = "/intro/boardAngularVer", method = RequestMethod.GET)
	public String boardAngularVer() {
		return "/intro/boardAngularVer";
	}
	
	@ResponseBody
	@RequestMapping(value = "/intro/AngBoardList", method = RequestMethod.POST)
	public Object AngBoardList(@RequestParam HashMap<String, Object> params, Model model) {
		Criteria cri = null;
		PageMaker pm = null;
		
		List<HashMap<String, Object>> userList = null;
		HashMap<String, Object> pagination = null;
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
			
			pagination = new HashMap<String, Object>();
			pagination.put("pageMaker", pm);
			pagination.put("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
			return  "오류가 발생했습니다.";
		}
		return pagination;
	}
}
