package mesterum.controllers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@WebListener
public class MenuContextListener implements ServletContextListener {

	@Autowired
	private MessageSource messageSource;
   @Override
    public void contextInitialized(ServletContextEvent event) {
        
        ServletContext sc = event.getServletContext();

        // initialize the menu pages
        //String pag = sc.getInitParameter("pages");
       final String pag = messageSource.getMessage("page.names", null, LocaleContextHolder.getLocale());//Locale.getDefault();
        String[] paga = pag.split(",");
        int i=0;
        String[][] pages=new String[paga.length][];
        for(String p:paga){
            String[] pa = p.split("[ _]",2);
            if(pa[0].equals(""))
                pa[0]=pa[1].toLowerCase();
            pages[i++]=pa;
        }
            
        sc.setAttribute("pages", pages);
   }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // no cleanup necessary
    }
}
