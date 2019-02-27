package com.zzf.springmvc.filter;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.zzf.springmvc.bean.Product;
import com.zzf.springmvc.form.ProductForm;
import com.zzf.springmvc.service.ProductService;

@WebFilter(filterName = "DispatcherFilter",urlPatterns = {"/input-product-filter","/save-product-filter"})
public class DispatcherFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		String dispatchUrl = null;
		if("input-product-filter".equals(action)){
			System.out.println("这是输入方法");
			dispatchUrl = "/jsp/ProductForm.jsp";
		}else if("save-product-filter".equals(action)){
			System.out.println("这是保存方法");
			ProductForm productForm = new ProductForm();
			productForm.setName(request.getParameter("name"));
			productForm.setDescription(request.getParameter("description"));
			productForm.setPrice(request.getParameter("price"));
			
			
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
			
			request.setAttribute("product", product);
			dispatchUrl = "/jsp/ProductDetails.jsp";
		}
		if(dispatchUrl !=null){
			System.out.println("现在跳转地址" + dispatchUrl);
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
		}else{
			filterChain.doFilter(request, response);
		}
	}


}
