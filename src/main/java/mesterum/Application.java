package mesterum;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@ServletComponentScan(basePackages="mesterum.controllers")
public class Application extends WebMvcConfigurerAdapter {
	
	@Bean
	public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
	}@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor(){
	    	@Autowired
	    	private MessageSource messageSource;

			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				ServletContext sc = request.getServletContext();
				final String pag = messageSource.getMessage("page.names", null, LocaleContextHolder.getLocale());
		        String[] paga = pag.split("[_ ,]+");
		        int i=0;
		        String[][] pages=(String[][]) sc.getAttribute("pages");
		        if(pages==null)return;
		        for(String p:paga){
		          if(i>=pages.length)break;
		          if(p.isEmpty())continue;
		          if(pages[i].length>1)
		            pages[i][1]=p;
		          i++;
		        }
		        sc.setAttribute("pages", pages);
			}
	    	
	    };
	    lci.setParamName("lang");
	    return lci;
	}@Override
	public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(localeChangeInterceptor());
	}
public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
