package com.example.blogs.controller;


import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GuyCui
 * @date 2021/6/3
 * @apiNote
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return ("index");
    }

  @RequestMapping("/")
    public String login(){
      return ("views/user/login");
    }

    @GetMapping("/user/captcha")
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setHeader("Pragma", "No-cache");
        response.setContentType("image/gif");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setFont(Captcha.FONT_1);
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        // 验证码存入session
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());
        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }

}
