public class Monhang {
private String ten;
private double gia1donvi;
private int soluong; 
@Override
public String toString() {
return ten+"; "+soluong+"; "+gia1donvi;
}
public String getTen() {
return ten;
}
public void setTen(String ten) {
this.ten = ten;
}
public double getGia1donvi() {
return gia1donvi;
}
public void setGia1donvi(double gia1donvi) {
this.gia1donvi = gia1donvi;
}
public int getSoluong() {
return soluong;
}
public void setSoluong(int soluong) {
this.soluong = soluong;
}
public Monhang(String ten, double gia1donvi, int soluong) {
super();
this.ten = ten;
this.gia1donvi = gia1donvi;
this.soluong = soluong;
}
public Monhang() {
super();
}
@Override
public int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + ((ten == null) ? 0 : ten.hashCode());
return result;
}
@Override
public boolean equals(Object obj) {
if (this == obj)
return true;
if (obj == null)
return false;
if (getClass() != obj.getClass())
return false;
Monhang other = (Monhang) obj;
if (ten == null) {
if (other.ten != null)
return false;
} else if (!ten.equals(other.ten))
return false;
return true;
}
}
