import java.util.ArrayList;
public class Giohang {
private ArrayList<Monhang>giohang;
public Giohang(){
giohang=new ArrayList<Monhang>();
}
/**
* Thêm 1 món hàng vào giỏ hàng
* @param mh là món hàng cần thêm
*/
public void ThemMonhang(Monhang mh){
if(giohang.contains(mh)){
Monhang mhang=giohang.get(giohang.indexOf(mh));
mhang.setSoluong(mhang.getSoluong()+mh.getSoluong());
}
else{
giohang.add(mh);
}
}
/**
* Láº¥y 1 mÃ³n
* @param i
* @return
*/
public Monhang LayMonHang(int i){
if(i<0||i>giohang.size()-1)
return null;
return giohang.get(i);
}
/**
* 
* @return
*/
public ArrayList<Monhang> getGiohang(){
return giohang;
}
/**
* 
* @param ten
* @return
*/
public boolean XoaMonHang(String ten){
Monhang mh=new Monhang(ten,0d,0);
if(!giohang.contains(mh))
return false;
giohang.remove(mh);
return true;
}
/**
* 
* @return
*/
public int soMonHang(){
return giohang.size();
}
/**
* 
* @return
*/
public double tongtien(){
double t=0d;
for(Monhang mh:giohang)
t+=mh.getGia1donvi()*mh.getSoluong();
return t;
}
}
