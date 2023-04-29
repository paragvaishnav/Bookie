package com.bookie.rest.api.utility;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.bookie.rest.api.controller.UserController;
import com.bookie.rest.api.serviceImpl.CustomUserDetailService;
import com.bookie.rest.api.serviceImpl.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
@Order(2)
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger logger = LogManager.getLogger(JwtRequestFilter.class);

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader("Authorization");
		logger.warn("in header of request ------->" + header);
		String username = null;
		String authToken = null;
		Iterator<String> temp = req.getHeaderNames().asIterator();

//		while (temp.hasNext())
//			System.out.println(temp.next() + " " + req.getHeader(temp.next()));

		if (header != null && header.startsWith("Bearer")) {
			authToken = header.replace("Bearer", "");
			try {
				username = jwtUtils.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("the token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

			if (jwtUtils.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = jwtUtils.getAuthentication(authToken,
						SecurityContextHolder.getContext().getAuthentication(), userDetails);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				logger.info("authenticated user " + username + ", setting security context "
						+ authentication.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		try {
			chain.doFilter(req, res);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
