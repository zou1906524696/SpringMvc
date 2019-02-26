package com.zzf.springmvc.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzf.springmvc.bean.Product;
import com.zzf.springmvc.form.ProductForm;
import com.zzf.springmvc.service.ProductService;

@WebServlet(name="ControllerServlet",urlPatterns = {"/input-product","/save-product"})
public class ControllerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();  //这里显示/app/input-product
		int lastIndex = uri.lastIndexOf("/"); //这里记录最后一个/
		String action = uri.substring(lastIndex+1); //剪切   成/input-product
		
		String dispatchUrl = null;
		if("input-product".equals(action)){
			dispatchUrl = "/jsp/ProductForm.jsp";
		}else if("save-product".equals(action)){
			ProductForm productForm = new ProductForm();
			productForm.setName(req.getParameter("name"));
			productForm.setDescription(req.getParameter("description"));
			productForm.setPrice(req.getParameter("price"));
			
			
			Product product = new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			try {
				product.setPrice(new BigDecimal(productForm.getPrice()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			ProductService saveProductAction = new ProductService();
			saveProductAction.save(product);
			
			req.setAttribute("product", product);
			dispatchUrl = "/jsp/ProductDetails.jsp";
			
		}
		if(dispatchUrl != null){
			//resp.sendRedirect("http://www.baidu.com"); 
			RequestDispatcher rd = req.getRequestDispatcher(dispatchUrl);
			rd.forward(req, resp);
		}
		
	}
	

}
