package com.ytempest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ytempest.exception.ServiceException;
import com.ytempest.service.AdminInfoService;
import com.ytempest.vo.AdminInfoVO;

@Controller
public class LoginController {

	@Resource(name = "AdminInfoService")
	private AdminInfoService adminInfoService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			@RequestParam("admin") String admin,
			@RequestParam("pwd") String pwd) {
		
		AdminInfoVO adminInfoVO = null;

		try {
			adminInfoVO = adminInfoService.login(admin, pwd);
		} catch (ServiceException e) {
			String msg = e.getMessage();
			ModelAndView mav = new ModelAndView();
			mav.addObject("errorMsg", msg);
			return mav;
		}

		ModelAndView mav = new ModelAndView("redirect:index.jsp");

		request.getSession().setAttribute("adminInfo", adminInfoVO);

		return mav;
	}

	/**
	 * 跟据ID查询商品信息，跳转修改商品页面 演示默认支持的参数传递 Model/ModelMap返回数据模型
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */

	// @RequestMapping("itemEdit")
	// public String itemEdit(Model model, ModelMap modelMap, HttpServletRequest
	// request, HttpServletResponse response,
	// HttpSession session) {
	// String idStr = request.getParameter("id");
	// System.out.println("response:" + response);
	// System.out.println("session:" + session); // 查询商品信息
	// Item item = new Item(Integer.parseInt(idStr), "空调", 1333, new Date(),
	// "This is Good!");
	// // model返回数据模型
	// model.addAttribute("item", item);
	// // mav.addObject("item", item);
	//
	// // 这里return的值就是要打开的jsp文件，也就是打开itemEdit.jsp
	// return "itemEdit";
	// }

}
