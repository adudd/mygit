package com.sj.ilaoshi.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;








import com.alibaba.fastjson.JSONObject;
import com.sj.ilaoshi.constants.Constants;
import com.sj.ilaoshi.entity.User;
import com.sj.ilaoshi.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	
	
	@RequestMapping(value = "/find")
	public void  find(PrintWriter printWriter,HttpServletResponse response,HttpServletRequest request)  throws Exception{
		
		Cookie cookie = getCookieByName(request,"token");
	    System.out.println("------------"+cookie.getValue());
	    
	    
	    
		if(cookie.getValue().equals("123456")){
			Map<String, Object> map = new HashMap<String, Object>();
			List<User> u = userService.findUser(map);	
			printWriter.write(JSONArray.fromObject(u).toString());
			printWriter.flush();
			printWriter.close();
		}else{
			JSONObject json = new JSONObject();
			json.put("code", "10000");
			json.put("msg", "验证失败");
			json.put("result", "");
			printWriter.write(json.toString());
			printWriter.flush();
			printWriter.close();
		}
		
	}
	
	@RequestMapping(value = "/login")
	public void  login(PrintWriter printWriter,HttpServletResponse response,HttpServletRequest request)  throws Exception{
		
		System.out.println("-----"+request.getMethod());
		
		String user = "{\"username\":\"admin\",\"age\":\"18\"}";	

		JSONObject jsonObject  = JSONObject.parseObject(user);
		
		JSONObject json = new JSONObject();
		json.put("code", "0000");
		json.put("msg", Constants.LOGIN_SUCCESS);
		json.put("result", jsonObject);
		
		Cookie cookie = new Cookie("token","111111");
		
        cookie.setMaxAge(30 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
		
		printWriter.write(json.toString());
		printWriter.flush();
		printWriter.close();
	}
	  /**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
	
}
