package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entities.Note;
import com.helper.FactoryProvider;


public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SaveNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			//title, content fetch
			
		String title= request.getParameter("title");
		String contet= request.getParameter("content");

			Note note=new Note(title, contet, new Date());
			
			//System.out.println(note.getId()+" : "+note.getTitle());
			
			//HIBERNATE: Save()
		
			Configuration cfg=new Configuration();
			SessionFactory f = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
			
			Session s = f.openSession();
			Transaction tx = s.beginTransaction();
			s.save(note);
			
			tx.commit();
			f.close();
	        s.close();  

	
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1 style='text-align:center;'>Note added sucessfully</h1>");
			out.println("<h1 style='text-align:center;'><a href='all_notes.jsp'>View all notes</a></h1>");

	
		}catch(Exception e) {
          
			e.printStackTrace();
			
		}
		
		
	}

}
