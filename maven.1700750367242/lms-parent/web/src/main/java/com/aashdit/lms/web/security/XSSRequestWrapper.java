package com.aashdit.lms.web.security;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

	private final Map<String, String[]> mutableParams = new HashMap<>();
	
	public XSSRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}
	
	

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);

		if (values == null) {
			return null;
		}

		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = stripXSS(values[i]);
		}

		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);

		return stripXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return stripXSS(value);
	}
	
	 public XSSRequestWrapper addParameter(String name, String value) {
	        if (value != null)
	            mutableParams.put(name, new String[] { value });
	           
	        return this;
	    }

	   
	    @Override
	    public Map<String, String[]> getParameterMap() {
	        Map<String, String[]> allParameters = new HashMap<>();
	        allParameters.putAll(super.getParameterMap());
	       //mutableParams.addParameter("","");
	       // allParameters.putAll(mutableParams);
	      String[] syska_ad =  allParameters.get("syska_ad");
	      if(syska_ad!=null) {
	    	  JSONArray jsonArray=new JSONArray(syska_ad[0]);
	    	  jsonArray.forEach(obj->{
				JSONObject jsonObj=new JSONObject(obj.toString());
				  mutableParams.put(jsonObj.getString("name"), new String[] { jsonObj.getString("value") });
				  if(jsonObj.getString("name").equals("isActive")) {
					 // addParameter(jsonObj.getString("name"), "true");
				  }else {
					//  addParameter(jsonObj.getString("name"), jsonObj.getString("value")); 
				  }
				
			});
	      }else {
	    	  return Collections.unmodifiableMap(allParameters);  
	      }
	     return Collections.unmodifiableMap(mutableParams);
	    
	    }

	private String stripXSS(String value) {
		if (value != null) {
			// System.out.println("escapeHTML work successfully and escapeHTML value is : " + StringEscapeUtils.escapeHtml(value));
			// return StringEscapeUtils.escapeHtml(value);

			// Avoid null characters
			value = value.replaceAll("", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);

			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

			value = scriptPattern.matcher(value).replaceAll("");

			//value = value.replaceAll("[^a-zA-Z1-90_\\- \\.\\@\\#\\/\\,\\=\\+]*", "").replaceAll("\\s+", " ");
		} 
		return value;
	}
	
	  @Override
	    public Enumeration<String> getParameterNames() {
	        return Collections.enumeration(getParameterMap().keySet());
	    }
	  
	  

	  
}