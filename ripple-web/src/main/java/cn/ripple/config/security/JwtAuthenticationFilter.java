package cn.ripple.config.security;

import cn.hutool.core.util.StrUtil;
import cn.ripple.constant.SecurityConstant;
import cn.ripple.enums.ResponseCodeEnum;
import cn.ripple.exception.RippleException;
import cn.ripple.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: Edwin
 * @Description:
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstant.HEADER);
        if (StrUtil.isBlank(header)) {
            header = request.getParameter(SecurityConstant.HEADER);
        }
        if (StrUtil.isBlank(header) || !header.startsWith(SecurityConstant.TOKEN_SPLIT)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(SecurityConstant.HEADER);
        if (StrUtil.isNotBlank(token)) {
            // 解析token
            Claims claims;
            try {
                claims = Jwts.parser()
                        .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                        .parseClaimsJws(token.replace(SecurityConstant.TOKEN_SPLIT, ""))
                        .getBody();

                //获取用户名
                String openId = claims.getSubject();
                if (StrUtil.isNotBlank(openId)) {
                    String userCode = claims.get("userCode").toString();
                    ServletContext context = request.getServletContext();
                    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
                    UserService userService = ctx.getBean(UserService.class);
                    if(userService.getUserByCode(userCode)!=null){
                        return new UsernamePasswordAuthenticationToken(userCode, null, new ArrayList<>());
                    }
                }
            } catch (ExpiredJwtException e) {
                throw new RippleException(ResponseCodeEnum.TOKEN_TIME_OUT.getValue(), ResponseCodeEnum.TOKEN_TIME_OUT.getName());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RippleException(ResponseCodeEnum.BUSINESS_ERROR.getValue(), "超时");
            }
        }
        return null;
    }

}

