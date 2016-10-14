import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Xuly")
public class Xuly extends HttpServlet {
/** 
* Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
try {
HttpSession session = request.getSession(true);
Giohang gh=(Giohang)(session.getAttribute("giohang"));
if(gh==null)
gh=new Giohang();
String hang=request.getParameter("cbohang");
String sl=request.getParameter("tfSoluong");
int soluong=Integer.parseInt(sl);
if(soluong>0){
double gia=Tinhgia(hang);
Monhang mh=new Monhang(hang,gia,soluong);
gh.ThemMonhang(mh);
session.setAttribute("giohang", gh);
//System.out.println(mh);
}
RequestDispatcher rd=request.getRequestDispatcher("EShop");
rd.forward(request, response);
} finally { 
out.close();
}
} 
private double Tinhgia(String sp){
if(sp.startsWith("Xoài"))return 10000;
if(sp.startsWith("Cóc"))return 7000;
if(sp.startsWith("Mít"))return 16500;
if(sp.startsWith("Mận"))return 8000;
return 4000;//me
}
/** 
* Handles the HTTP <code>GET</code> method.
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
} 
/** 
* Handles the HTTP <code>POST</code> method.
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
processRequest(request, response);
}
}
