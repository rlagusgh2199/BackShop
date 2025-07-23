package order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcUtil.JDBCUtil;


@WebServlet("/OrderDAO")
public class OrderDAO extends HttpServlet {
	/*
	 * // 주문 추가 메서드 public void addOrder(OrderDTO order) throws SQLException {
	 * String sql =
	 * "INSERT INTO orders (user_id, product_no, product_name, product_price, quantity, order_status, order_date, delivery_date) VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)"
	 * ; Connection conn = null; PreparedStatement pstmt = null;
	 * 
	 * try { conn = JDBCUtil.getConnection(); // 연결 생성 pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, order.getUserId());
	 * pstmt.setInt(2, order.getProductNo()); pstmt.setString(3,
	 * order.getProductName()); pstmt.setDouble(4, order.getProductPrice());
	 * pstmt.setInt(5, order.getQuantity()); pstmt.setString(6, "주문접수"); // 초기 상태는
	 * '주문접수' pstmt.setTimestamp(7, order.getDeliveryDate()); pstmt.executeUpdate();
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * JDBCUtil.close(pstmt, conn); // 연결 종료 } }
	 * 
	 * 
	 * // 주문 상태 업데이트 메서드 public boolean updateOrderStatus(int orderId, String
	 * newStatus) { String updateQuery =
	 * "UPDATE orders SET order_status = ? WHERE order_id = ?"; try (Connection conn
	 * = JDBCUtil.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(updateQuery)) {
	 * 
	 * pstmt.setString(1, newStatus); pstmt.setInt(2, orderId);
	 * 
	 * int rowsAffected = pstmt.executeUpdate(); return rowsAffected > 0; } catch
	 * (SQLException e) { e.printStackTrace(); return false; } }
	 * 
	 * 
	 * // 주문 처리 메서드 public boolean processOrder(String userId, int productNo, String
	 * productName, double productPrice, int quantity) { Connection conn = null;
	 * PreparedStatement pstmt = null;
	 * 
	 * String sql =
	 * "INSERT INTO orders (user_id, product_no, product_name, product_price, quantity, order_date) "
	 * + "VALUES (?, ?, ?, ?, ?, NOW())";
	 * 
	 * try { conn = JDBCUtil.getConnection(); pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, userId); pstmt.setInt(2, productNo); pstmt.setString(3,
	 * productName); pstmt.setDouble(4, productPrice); pstmt.setInt(5, quantity);
	 * 
	 * int rowsAffected = pstmt.executeUpdate(); return rowsAffected > 0; // 성공적으로
	 * 추가되었는지 확인 } catch (SQLException e) { e.printStackTrace(); return false; // 실패
	 * 처리 } finally { JDBCUtil.close(pstmt, conn); } }
	 */
    
	
	// 주문 추가
    public void addOrder(OrderDTO order) {
        String sql = "INSERT INTO orders (product_no, product_name, product_price, quantity, order_state, user_id, product_image) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getProductNo());
            pstmt.setString(2, order.getProductName());
            pstmt.setDouble(3, order.getProductPrice());
            pstmt.setInt(4, order.getQuantity());
            pstmt.setString(5, order.getOrderStatus());
            pstmt.setString(6, order.getUserId());
            pstmt.setString(7,order.getProduct_image());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 사용자 주문 내역 가져오기
	/*
	 * public List<OrderDTO> getUserOrders(String userId) { List<OrderDTO> orders =
	 * new ArrayList<>(); String sql = "SELECT * FROM orders WHERE user_id = ?";
	 * 
	 * try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql)) {
	 * 
	 * pstmt.setString(1, userId); ResultSet rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { OrderDTO order = new OrderDTO();
	 * order.setOrderNo(rs.getInt("order_no"));
	 * order.setProductNo(rs.getInt("product_no"));
	 * order.setProductName(rs.getString("product_name"));
	 * order.setProductPrice(rs.getDouble("product_price"));
	 * order.setQuantity(rs.getInt("quantity"));
	 * order.setOrderStatus(rs.getString("order_state"));
	 * order.setOrderDate(rs.getTimestamp("order_date"));
	 * order.setUserId(rs.getString("user_id"));
	 * 
	 * orders.add(order); } } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return orders; }
	 */
    
    // 이게 유력한 getOrdersByUserId
	/*
	 * public List<OrderDTO> getOrdersByUserId(String userId) { List<OrderDTO>
	 * orders = new ArrayList<>(); String sql =
	 * "SELECT order_no, product_name, product_image, product_price, quantity, order_state FROM orders WHERE user_id = ?"
	 * ;
	 * 
	 * try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql)) { pstmt.setString(1, userId); try (ResultSet rs =
	 * pstmt.executeQuery()) { while (rs.next()) { OrderDTO order = new OrderDTO();
	 * order.setOrderNo(rs.getInt("order_no"));
	 * order.setProductName(rs.getString("product_name"));
	 * order.setProduct_image(rs.getString("product_image"));
	 * order.setProductPrice(rs.getDouble("product_price"));
	 * order.setQuantity(rs.getInt("quantity"));
	 * order.setOrderStatus(rs.getString("order_state")); // 추가된 부분
	 * orders.add(order); } } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return orders; // 반환값 확인 }
	 */
    
    public List<OrderDTO> getOrdersByUserId(String userId) {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setOrderNo(rs.getInt("order_no"));
                
                order.setProductName(rs.getString("product_name"));
                order.setProductPrice(rs.getDouble("product_price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setProduct_image(rs.getString("product_image"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setOrderStatus(rs.getString("order_state"));
                order.setUserId(rs.getString("user_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    
 // 주문 상태 수정 메서드
    public boolean updateOrderStatus(String orderId, String orderStatus) {
        String sql = "UPDATE orders SET order_state = ? WHERE order_no = ?";
        try (Connection conn = JDBCUtil.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderStatus);
            pstmt.setString(2, orderId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 // 주문 삭제 메서드
    public boolean deleteOrder(int orderNo) {
        String sql = "DELETE FROM orders WHERE order_no = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderNo);
            
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 // orders 테이블에서 모든 주문 데이터를 가져오는 메소드
    public List<OrderDTO> getOrders() {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";  // 주문 상태가 'pending'인 항목들만 가져옵니다.

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	OrderDTO order = new OrderDTO();
                order.setOrderNo(rs.getInt("order_no"));
                
                order.setProductName(rs.getString("product_name"));
                order.setProductPrice(rs.getDouble("product_price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setProduct_image(rs.getString("product_image"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setOrderStatus(rs.getString("order_state"));
                order.setUserId(rs.getString("user_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    // 주문 상태 수정
    public boolean updateOrderState(int orderNo, String newState) {
        String sql = "UPDATE orders SET order_state = ? WHERE order_no = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
        	 String kor_sta = "";
        	 if (newState.equals("pending")) {
        		 kor_sta = "대기";
        	 }
        	 if (newState.equals("shipped")) {
        		 kor_sta = "배송";
        	 }
        	 if (newState.equals("completed")) {
        		 kor_sta = "완료";
        	 }

            pstmt.setString(1, kor_sta);
            pstmt.setInt(2, orderNo);

            int rowsAffected = pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
}
