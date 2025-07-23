package cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CartDTO")
public class CartDTO extends HttpServlet {
	private int no;                // 장바구니 항목 번호
    private String productNo;      // 상품 번호
    private String productName;    // 상품명
    private double productPrice;   // 상품 가격
    private int quantity;          // 수량
    private String productImage;   // 상품 이미지 URL
    private String date;           // 날짜
    private String state;          // 상태
    private String userId;         // 사용자 ID

    // Getters and Setters
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
