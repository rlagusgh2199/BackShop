package com.backshop.productDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jdbcUtil.JDBCUtil;

public class ProductDAO {
    private Connection connection;
    // 기본 생성자
    public ProductDAO() {
        this.connection = JDBCUtil.getConnection(); // 기본적으로 연결을 생성
    }

    public void addProduct(ProductDTO product) throws SQLException {
        String sql = "INSERT INTO products (product_category, product_gender, product_name, product_price, product_stock, product_temperature, product_image, product_description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductCategory());
            pstmt.setString(2, product.getProductGender());
            pstmt.setString(3, product.getProductName());
            pstmt.setDouble(4, product.getProductPrice());
            pstmt.setInt(5, product.getProductStock());
            pstmt.setDouble(6, product.getProductTemperature());
            pstmt.setString(7, product.getProductImage());
            pstmt.setString(8, product.getProductDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt, conn); // pstmt.executeUpdate();
        }
    }
    
    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return productList;
    }
    
    public List<ProductDTO> getAllProducts_desc() throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY created_at DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return productList;
    }
    
    public List<CategoryCountDTO> getCategoryCounts() throws SQLException {
        List<CategoryCountDTO> categoryCounts = new ArrayList<>();
        String sql = "SELECT product_category, COUNT(*) AS product_count FROM products WHERE product_category IN ('상의', '하의') GROUP BY product_category";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String category = rs.getString("product_category");
                int count = rs.getInt("product_count");
                categoryCounts.add(new CategoryCountDTO(category, count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return categoryCounts;
    }
    
    public List<ProductDTO> getShirts() throws SQLException {
        List<ProductDTO> shirtList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_category = '상의'";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductGender(rs.getString("product_gender"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                shirtList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return shirtList;
    }
    
    public List<ProductDTO> getMaleShirts() throws SQLException {
        List<ProductDTO> MaleShirtList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_category = '상의' and product_gender='남자' ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductGender(rs.getString("product_gender"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                MaleShirtList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return MaleShirtList;
    }
    
    public List<ProductDTO> getFemaleShirts() throws SQLException {
        List<ProductDTO> FemaleShirtList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_category = '상의' and product_gender='여자' ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductGender(rs.getString("product_gender"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                FemaleShirtList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return FemaleShirtList;
    }
    
    
    public List<ProductDTO> getPants() throws SQLException {
        List<ProductDTO> pantsList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_category = '하의'";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductGender(rs.getString("product_gender"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                pantsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return pantsList;
    }
    
    public List<ProductDTO> getMalePants() throws SQLException {
        List<ProductDTO> MalePantsList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_category = '하의' and product_gender='남자' ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductGender(rs.getString("product_gender"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                MalePantsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return MalePantsList;
    }
    
    public List<ProductDTO> getFemalePants() throws SQLException {
        List<ProductDTO> FemalePantsList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_category = '하의' and product_gender='여자' ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductGender(rs.getString("product_gender"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                FemalePantsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return FemalePantsList;
    }
    
    
    
    public ProductDTO getProductById_na(int productId) {
        String sql = "SELECT * FROM products WHERE id = ?";
        ProductDTO product = null;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new ProductDTO();
                    product.setId(rs.getInt("id"));
                    product.setProductCategory(rs.getString("product_category"));
                    product.setProductName(rs.getString("product_name"));
                    product.setProductPrice(rs.getDouble("product_price"));
                    product.setProductStock(rs.getInt("product_stock"));
                    product.setProductImage(rs.getString("product_image"));
                    product.setProductDescription(rs.getString("product_description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public void updateProduct(ProductDTO product) throws SQLException {
        String sql = "UPDATE products SET product_name = ?, product_price = ?, product_stock = ?, product_description = ?, product_image = ?, product_category = ?, product_temperature = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getProductPrice());
            pstmt.setInt(3, product.getProductStock());
            pstmt.setString(4, product.getProductDescription());
            pstmt.setString(5, product.getProductImage());
            pstmt.setString(6, product.getProductCategory());
            pstmt.setDouble(7, product.getProductTemperature());
            pstmt.setInt(8, product.getId());
            pstmt.executeUpdate(); // 업데이트 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, pstmt, conn); // 모든 자원 종료
        }
    }
    
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id); // ID 세팅
            pstmt.executeUpdate(); // 삭제 실행
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // 예외를 다시 던져서 호출자에게 알림
        } finally {
            JDBCUtil.close(null, pstmt, conn); // 모든 자원 종료
        }
    }
    
    
    public List<ProductDTO> getProductsByCategory(String category) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_category = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category); // 카테고리 조건 추가
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        return productList;
    }
    
    
    
    public ProductDTO getProductById(int id) throws SQLException {
        ProductDTO product = null;
        String sql = "SELECT * FROM products WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // 연결 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id); // ID 세팅
            rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setProductCategory(rs.getString("product_category"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductTemperature(rs.getDouble("product_temperature"));
                product.setProductHumidity(rs.getDouble("product_humidity"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductDescription(rs.getString("product_description"));
                product.setRegisterDate(rs.getDate("created_at")); // register_date 추가
                // 필요한 다른 필드들도 추가
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn); // 모든 자원 종료
        }
        
        return product;
    }
    
    
 // 상품 재고 업데이트 메서드
    public boolean updateProductStock(ProductDTO product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String updateQuery = "UPDATE products SET product_stock = ? WHERE id = ?";

        try {
            // 데이터베이스 연결
            conn = JDBCUtil.getConnection();
            
            // PreparedStatement 생성
            pstmt = conn.prepareStatement(updateQuery);
            
            // 파라미터 설정
            pstmt.setInt(1, product.getProductStock());  // 새 재고 수
            pstmt.setInt(2, product.getId());  // 상품 ID
            
            // 쿼리 실행
            int rowsAffected = pstmt.executeUpdate();
            
            // 재고 업데이트가 성공했으면 1 이상의 값을 반환
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 리소스 해제
            JDBCUtil.close(pstmt, conn);
        }
    }
    
    



}
