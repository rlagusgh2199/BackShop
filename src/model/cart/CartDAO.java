package cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jdbcUtil.JDBCUtil;

@WebServlet("/CartDAO")
public class CartDAO extends HttpServlet {
	// 장바구니에 상품 추가
    public void addCartItem(CartDTO cartItem) {
        String sql = "INSERT INTO SHOP_CART (product_no, product_name, product_price, quantity, product_image, date, state, user_id) VALUES (?, ?, ?, ?, ?, NOW(), 'pending', ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cartItem.getProductNo());
            pstmt.setString(2, cartItem.getProductName());
            pstmt.setDouble(3, cartItem.getProductPrice());
            pstmt.setInt(4, cartItem.getQuantity());
            pstmt.setString(5, cartItem.getProductImage());
            pstmt.setString(6, cartItem.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 특정 사용자의 장바구니 아이템 가져오기
    public List<CartDTO> getCartItems(String userId) {
        List<CartDTO> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM SHOP_CART WHERE user_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CartDTO cartItem = new CartDTO();
                cartItem.setNo(rs.getInt("no"));
                cartItem.setProductNo(rs.getString("product_no"));
                cartItem.setProductName(rs.getString("product_name"));
                cartItem.setProductPrice(rs.getDouble("product_price"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setProductImage(rs.getString("product_image"));
                cartItem.setDate(rs.getString("date"));
                cartItem.setState(rs.getString("state"));
                cartItem.setUserId(rs.getString("user_id"));

                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    // 장바구니 아이템 삭제
    public boolean removeItemFromCart(int cartItemNo) {
        String sql = "DELETE FROM SHOP_CART WHERE no = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, cartItemNo);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // 삭제 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean addToCart(CartDTO cartItem) {
        String sql = "INSERT INTO SHOP_CART (product_no, product_name, product_price, quantity, product_image, user_id, date) "
                   + "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cartItem.getProductNo());
            pstmt.setString(2, cartItem.getProductName());
            pstmt.setDouble(3, cartItem.getProductPrice());
            pstmt.setInt(4, cartItem.getQuantity());
            pstmt.setString(5, cartItem.getProductImage());
            pstmt.setString(6, cartItem.getUserId());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
