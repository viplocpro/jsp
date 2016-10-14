

import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.regex.Pattern;

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have
 *  no values or multiple values.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

@WebServlet("/show-params")
public class ShowParameters extends HttpServlet 
{
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException 
  {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    String title = "Reading All Request Parameters";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>Parameter Name<TH>Parameter Value(s)");
    String usr = request.getParameter("username");
    String pwd = request.getParameter("password");
    String email = request.getParameter("email");
    String sex = request.getParameter("sex");
    String age = request.getParameter("age");
    String status = request.getParameter("status");
    //System.out.println(usr); //test

    try
    {
    	boolean found = false;
    	
    	File file = new File("F:\\data.txt");
    	FileReader fileReader = new FileReader(file);
    	BufferedReader bufferedReader = new BufferedReader(fileReader);
    	String temp  = null; 
    	    		
    	while ((temp = bufferedReader.readLine()) != null)
    	{
    		
    		String[] information = temp.split(Pattern.quote("^"));
    	 	String _usr = information[0];
    	 	//System.out.println(information[0]);
    	    //String _pwd = information[1];
    	    //String _email = information[2];
    	    //String _sex = information[3];
    	    //String _age = information[4];
    	    //String _status = information[5];
    	
    		if(usr.equals(_usr))
    		{
    			out.println("username already exist");
    			found = true;
    			//fileReader.close();
    			break;
    		}    		    	
    	}
    	//System.out.println(temp);
    	if(!found)
		{
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream("F:\\data.txt", true));
			fileWriter.println(usr + "^" + pwd + "^" + email + "^" + sex + "^" + age + "^" + status);
			out.println("Success!");
			fileWriter.close();
		}
    }
    catch(FileNotFoundException fnfe) 
    {
    	System.out.println(fnfe);
    }
    finally
    {
    	//System.out.println("Something wrong!");
    } 
  }



  @Override
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
  
}