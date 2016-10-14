package forms;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/login-result")
public class LoginRedisplay extends HttpServlet {
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String errorData=MakeErrorData(request, response);
		HttpSession mySession=null;
		if (errorData.equals("success"))
		{
			String username = request.getParameter("username");
			mySession = request.getSession();
			mySession.setAttribute("user",username );
			response.sendRedirect("room-list");
		}
		else
		{
			response.sendRedirect("login"+ "?" + errorData);
		}
	}
	
	private String MakeErrorData (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String errorData = "";
		String[] parameters = GetParameters(request,response);
		
		if (isMissing(parameters))
		{
			//String hashPassword=HashPassword(parameters[1]);
			
			errorData+="username="+parameters[0]+"&password="+parameters[1];
		}
		else
		{
			if (CheckLogin(parameters[0],parameters[1]))
				return errorData="success";
			else
				return errorData="username="+parameters[0]+"&result=failed";
		}
		
		return errorData;
		
	}
	
	private String[] GetParameters(HttpServletRequest request, HttpServletResponse response) {
		String[] parameters = new String[2];
		
		parameters[0] = request.getParameter("username");
		parameters[1] = request.getParameter("password");
		
		for (int i=0; i<parameters.length;i++)
			parameters[i]=parameters[i].trim();
		
		return parameters;
	}
	
	private boolean isMissing(String[] parameters) {
	    replaceNull(parameters);
		for (int i=0; i<parameters.length;i++)
	    {
	    	if (parameters[i].trim().equals(""))
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
	
	private boolean CheckLogin (String crUsername, String crPassword){
		BufferedReader br = null;

		try 
		{
			String currentLine ="";
			br = new BufferedReader(new FileReader("F:\\ChatDatabase.txt"));

			while ((currentLine = br.readLine()) != null){
				
				String dbUsername = currentLine.split(Pattern.quote("^"))[0]; //username co trong database
				
				if (dbUsername.equals(crUsername))
				{
					String dbPassword = currentLine.split(Pattern.quote("^"))[1]; //password co trong database
					
					if (dbPassword.equals(crPassword))
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
}

