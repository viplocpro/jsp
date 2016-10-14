package forms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.util.regex.Pattern;
import java.security.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@WebServlet("/register-result")
public class RegisterRedisplay extends HttpServlet {
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {	    
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String errorData=MakeErrorData(request, response);
		
		response.sendRedirect("register"
                + "?" +errorData);
	}
	
	private String[] GetParameters(HttpServletRequest request, HttpServletResponse response) {
		String[] parameters = new String[5];
		
		parameters[0] = request.getParameter("username");
		parameters[1] = request.getParameter("password");
		parameters[2] = request.getParameter("age");
		parameters[3] = request.getParameter("gender");
		parameters[4] = request.getParameter("status");
		
		for (int i=0; i<parameters.length;i++)
			parameters[i]=parameters[i].trim();
		
		return parameters;
	}
	
	private boolean isMissing(String[] parameters) {
	    replaceNull(parameters);
		for (int i=0; i<parameters.length;i++)
	    {
			parameters[i]=parameters[i].trim();
	    	if (parameters[i].equals(""))
	    		return true;
	    }
	    return false;
	}
	
	private String[] replaceNull(String[] parameters) {
		for (int i=0; i<parameters.length;i++)
	    {
		    if(parameters[i] == null) 
		    	parameters[i]="";
	    }
		return parameters;
	}
	
	
	private String MakeErrorData (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorData = "";
		String[] parameters = GetParameters(request,response);
		
		if (isMissing(parameters))
		{
			//String hashPassword=HashPassword(parameters[1]);
			
			return errorData+="username="+parameters[0]+"&password="+parameters[1]+"&age="+parameters[2]+"&gender="+parameters[3]+"&status="+parameters[4];
		}
		else
		{
			if(isNumeric(parameters[2]))
			{
				int age=Integer.parseInt(parameters[2]);
				
				if (age>0 && age<101)
				{
					boolean done=CheckUserExist(parameters[0]);
					if (done)
						return errorData="username="+parameters[0]+"&result=exist";
					else
					{
						if(NewUser(parameters))
							return errorData="username="+parameters[0]+"&result=success";
						else
							return errorData="result=error";
					}
				}
				else //age out of range
					return errorData="username="+parameters[0]+"&age=wrong";
				
			}
			else //age is not numeric
				return errorData="username="+parameters[0]+"&age=wrong";
		}
		
	}

	private String HashPassword(String password){
		
		byte[] bytesOfMessage = null ;
		byte[] thedigest = null;
		String hashPassword = "";
		try {
			bytesOfMessage = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");
			thedigest = md.digest(bytesOfMessage);
			hashPassword = new String(thedigest);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashPassword;
	}
	
	private boolean NewUser(String[] parameters){
		BufferedWriter writer = null;
		try
		{
		    //writer = new BufferedWriter( new FileWriter("F:\\ChatDatabase.txt",true));
		    writer = new BufferedWriter
				    (new OutputStreamWriter(new FileOutputStream("F:\\ChatDatabase.txt",true),"UTF-8"));
		    //String hashPassword=HashPassword(parameters[1]);
		    
		    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Date date = new Date();
		    
		    
		    writer.write("\r\n"+parameters[0]+"^"+parameters[1]+"^"+parameters[2]+"^"+parameters[3]+"^"+parameters[4]+"^"+dateFormat.format(date));
		}
		catch ( IOException e)
		{
			return false;
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		        
		    }
		    catch ( IOException e)
		    {
		    	
		    }
		}    	
		return true;
		
	}
	
	private boolean CheckUserExist (String newUsername){
		BufferedReader br = null;

		try 
		{
			String currentLine ="";
			br = new BufferedReader
				    (new InputStreamReader(new FileInputStream("F:\\ChatDatabase.txt"),"UTF-8"));

			while ((currentLine = br.readLine()) != null){
				String dbUsername = currentLine.split(Pattern.quote("^"))[0]; //username co trong database
				if (dbUsername.equals(newUsername))
				{
					try {
						if (br != null)
							br.close();
					} 
					catch (IOException ex) {
						ex.printStackTrace();
					}
					return true;
				}
			}
		} 
		
		catch (IOException e) {
			e.printStackTrace();

		} 
		
		finally 
		{
			try {
				if (br != null)
					br.close();
			} 
			
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
		
		return false;
	}
	
	private boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
}
