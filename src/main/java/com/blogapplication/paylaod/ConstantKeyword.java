package com.blogapplication.paylaod;

public class ConstantKeyword {

	public static final String PAGE_NUMBER="0";
	public static final String PAGE_SIZE="3";
	public static final String SORT_BY="asc";
	public static final String SORT_DIR="desc";
	
	public static final int NORMAL_USER=501;
	public static final int ADMIN_USER=502;
	
    public  static final String [] PUBLIC_URLS= {
			
			"/api/v1/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
			
	};
}
