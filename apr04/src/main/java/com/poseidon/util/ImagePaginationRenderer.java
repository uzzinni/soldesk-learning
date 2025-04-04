package com.poseidon.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class ImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware {

	private ServletContext servletContext;

	public ImagePaginationRenderer() {

	}

	public void initVariables() {
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\"><image src=\"./images/back_left.png\" border=0/></a>&#160;"; 
		previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\"><image src=\"./images/back.png\" border=0/></a>&#160;";
		currentPageLabel = "<strong>{0}</strong>&#160;";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
		nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\"><image src=\"./images/next.png\" border=0/></a>&#160;";
		lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\"><image src=\"./images/next_right.png\" border=0/></a>&#160;";
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}
