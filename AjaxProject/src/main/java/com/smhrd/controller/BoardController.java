package com.smhrd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.model.Board;


@WebServlet("/boardList")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 비동기 통신은 기본적으로 요청을 허용하지 않음..
		// CORS(Cross-Origin Resource Sharing)
		// : 브라우저 보안 정책으로 인해 같은 도메인 주소가 아닌 다른 도메인 주소 요청에 제한을 두는 정책
		// : vs code에서 LiveServer -> Tomcat Server 요청하는 행위가 허용되지 않기 때문에
		//   아래와 같이 setHeader()로 특정 IP 주소를 허용하도록 설정이 필요, "*"는 모든 IP 허용
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		// 이런 메소드들을 허용해드릴게요
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); 
		
		// 음...
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		
		System.out.println("비동기 요청 들어옴...");
	
		// JSON 형식으로 응답하기 위한 설정
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
//		out.print("{\"name\":\"테스트\"}");
		
		Gson gson = new Gson();
		
		ArrayList<Board> list = new ArrayList<Board>();
		list.add(new Board(1, "테스트 글", "테스트", "2025-08-05", 2));
		list.add(new Board(2, "점심밥", "와랄라", "2025-08-02", 2));
		list.add(new Board(3, "김치볶음밥", "호롤로", "2025-06-05", 2));
		list.add(new Board(4, "프로젝트", "예렐레", "2025-08-30", 2));
		list.add(new Board(5, "시험", "바밤바", "2025-07-25", 2));
		
		// toJson : Java객체 -> JSON 형식으로 변경
		String jsonStr = gson.toJson(list);
		
		out.print(jsonStr);
	}

}
