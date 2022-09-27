package com.ay.exchange.filter;

import com.ay.exchange.common.error.dto.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Autowired
    private ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("JwtExceptionFilter");
        try{
            chain.doFilter(request,response);
        }catch(JwtException e){
            setErrorResponse(HttpStatus.UNAUTHORIZED,response,e);
        }
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, JwtException e) {
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        try {
            response.getWriter()
                    .write(mapper.writeValueAsString(
                            new ErrorDto(status.getReasonPhrase(),e.getMessage())));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
