import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/EShop")
public class EShop extends HttpServlet {
private Giohang gh=null; 
public EShop() {
super();
gh=new Giohang();
} 
public void init(ServletConfig config) throws ServletException {
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
doPost(request, response);
} 
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
double tongtien=0d;
try {
HttpSession session = request.getSession(true);
out.println("<html>");
out.println("<head>");
out.println("<title>Shopping Cart demo with servlet</title>");
out.println("</head>");
out.println("<body>");
out.println("<h1 align=\"center\">CHÀO MỪNG ĐẾN VỚI GIAN HÀNG TRÁI CÂY</h1>");
out.println("<form action=\"Xuly\" method=\"post\">");
out.println("<p>Chọn mặt hàng:<select name=\"cbohang\">");
out.println("<option>Xoài tượng</option>" +
"<option>Cóc dầm chua ngọt</option>" +
"<option>Mít tố nữ</option>" +
"<option>Mận ngọt Thái Lan</option>" +
"<option>Me dốt</option>");
out.println("</select>Số lượng<input type=\"text\" name=\"tfSoluong\" value=\"1\">");
out.println("</p>");
out.println("<input type=\"submit\" value=\"Mua Hàng\">");
out.println("<h2 align=\"center\">THÔNG TIN GIỎ HÀNG</h2>");
out.println("<table width=\"80%\" border=\"1\"><tr>"+
"<td>STT</td><td>Tên Sản phẩm</td><td>Số lượng</td>" +
"<td>Đơn giá</td><td>Thành tiền</td>"+
"</tr>"); Giohang gh=(Giohang)(session.getAttribute("giohang"));
if(gh!=null){
tongtien=gh.tongtien();
for(int i=0;i<=gh.soMonHang()-1;i++){
Monhang mh=gh.LayMonHang(i);
out.println("<tr><td>"+i+"</td><td>"+mh.getTen()+"</td><td>"+
mh.getSoluong()+"</td><td>"+mh.getGia1donvi()+"</td>"+
"<td>"+mh.getSoluong()*mh.getGia1donvi()+"</td>"+
"</tr>");
}
out.println("");
out.println("");
}
out.println("</table>");
out.println("<h2>Tổng tiền:"+tongtien+" đồng</h2>");
out.println("");
out.println("</form>");
out.println("</body>");
out.println("</html>");
} finally {
out.println("</html>");
out.close();
}
}
}
