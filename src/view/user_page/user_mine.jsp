<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@page import="com.backshop.productDB.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Back Shop</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_main.css">
</head>

<body>
    <!-- 헤더  -->
    <%@ include file="user_module/user_header.jsp" %>
    
	<div class="clear"></div>
    <div class="banner"></div>
    
    <div class="clear"></div>
    <h2>나만의 코디</h2> <!-- DB -->
    <section id="main">
    
        <%
            // 로그인된 사용자 정보 가져오기
            String loginId = (String) session.getAttribute("id2");
            MemberDTO user = null;
            MemberDAO mDao = new MemberDAO();
            user = mDao.getUserInfo(loginId);

            // 세션에서 상품 목록 가져오기
            List<ProductDTO> shirtList = (List<ProductDTO>) session.getAttribute("shirtList");
            List<ProductDTO> MaleShirtList = (List<ProductDTO>) session.getAttribute("MaleShirtList");
            List<ProductDTO> FemaleShirtList = (List<ProductDTO>) session.getAttribute("FemaleShirtList");

            List<ProductDTO> pantsList = (List<ProductDTO>) session.getAttribute("pantsList");
            List<ProductDTO> MalePantsList = (List<ProductDTO>) session.getAttribute("MalePantsList");
            List<ProductDTO> FemalePantsList = (List<ProductDTO>) session.getAttribute("FemalePantsList");
	
            String gender = user.getGender(); // "남성", "여성", "선택 안함"
            String favorite = user.getFavorite(); // "셔츠", "바지", "선택 안함"
        %>


        <h3><%= user.getGender() %>, <%= user.getFavorite() %>을(를) 선호하시는 당신에게 추천해요!</h3>
        <div class="product-list">
            <%
                // 남성인 경우
                if ("남성".equals(gender)) {
                    if ("셔츠".equals(favorite)) {
                        if (MaleShirtList != null && !MaleShirtList.isEmpty()) {
                            for (ProductDTO product : MaleShirtList) {
            %>
		                <div class="product-item">
		                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
		                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
		                </a>
		                    <h3><%= product.getProductName() %></h3>
		                    <p>₩<%= product.getProductPrice() %></p>
		                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
			                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
			                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
			                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
			                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
			                    <input type="hidden" name="quantity" value="<%= 1 %>">
		                   		<button type="submit">주문하기</button>
		                    </form>
		                </div>
            <%
                            }
                        } else { %>
                            <p>상품이 없습니다.</p>
            <%
                        }
                    } else if ("바지".equals(favorite)) {
                        if (MalePantsList != null && !MalePantsList.isEmpty()) {
                            for (ProductDTO product : MalePantsList) {
            %>
			                <div class="product-item">
			                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
			                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
			                </a>
			                    <h3><%= product.getProductName() %></h3>
			                    <p>₩<%= product.getProductPrice() %></p>
			                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
				                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
				                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
				                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
				                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
				                    <input type="hidden" name="quantity" value="<%= 1 %>">
			                   		<button type="submit">주문하기</button>
			                    </form>
			                </div>
            <%
                            }
                        } else { %>
                            <p>상품이 없습니다.</p>
            <%
                        }
                    } else { // 선택 안함
                        if (MaleShirtList != null && !MaleShirtList.isEmpty()) {
                        	// 셔츠와 바지에서 각각 2개씩 추천
                            int shirtCount = 0;
                           
                        	for (ProductDTO product : MaleShirtList) {
                        		  if (shirtCount >= 2) break;
                                  shirtCount++;
            %>
			                <div class="product-item">
			                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
			                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
			                </a>
			                    <h3><%= product.getProductName() %></h3>
			                    <p>₩<%= product.getProductPrice() %></p>
			                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
				                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
				                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
				                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
				                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
				                    <input type="hidden" name="quantity" value="<%= 1 %>">
			                   		<button type="submit">주문하기</button>
			                    </form>
			                </div>
            <%
                            }
                        }
                        if (MalePantsList != null && !MalePantsList.isEmpty()) {
                        	// 셔츠와 바지에서 각각 2개씩 추천
                            int pantsCount = 0;
                        	
                            for (ProductDTO product : MalePantsList) {
                            	 if (pantsCount >= 2) break;
                                 pantsCount++;
            %>
			                <div class="product-item">
			                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
			                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
			                </a>
			                    <h3><%= product.getProductName() %></h3>
			                    <p>₩<%= product.getProductPrice() %></p>
			                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
				                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
				                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
				                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
				                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
				                    <input type="hidden" name="quantity" value="<%= 1 %>">
			                   		<button type="submit">주문하기</button>
			                    </form>
			                </div>
            <%
                            }
                        }
                    }
                } 
            
            // 여성인 경우
                else if ("여성".equals(gender)) {
                    if ("셔츠".equals(favorite)) {
                        if (FemaleShirtList != null && !FemaleShirtList.isEmpty()) {
                            for (ProductDTO product : FemaleShirtList) {
            %>
		                <div class="product-item">
		                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
		                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
		                </a>
		                    <h3><%= product.getProductName() %></h3>
		                    <p>₩<%= product.getProductPrice() %></p>
		                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
			                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
			                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
			                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
			                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
			                    <input type="hidden" name="quantity" value="<%= 1 %>">
		                   		<button type="submit">주문하기</button>
		                    </form>
		                </div>
            <%
                            }
                        } else { %>
                            <p>상품이 없습니다.</p>
            <%
                        }
                    } else if ("바지".equals(favorite)) {
                        if (FemalePantsList != null && !FemalePantsList.isEmpty()) {
                            for (ProductDTO product : FemalePantsList) {
            %>
		                <div class="product-item">
		                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
		                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
		                </a>
		                    <h3><%= product.getProductName() %></h3>
		                    <p>₩<%= product.getProductPrice() %></p>
		                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
			                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
			                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
			                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
			                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
			                    <input type="hidden" name="quantity" value="<%= 1 %>">
		                   		<button type="submit">주문하기</button>
		                    </form>
		                </div>
            <%
                            }
                        } else { %>
                            <p>상품이 없습니다.</p>
            <%
                        }
                    } else { // 선택 안함
                        if (FemaleShirtList != null && !FemaleShirtList.isEmpty()) {
                            int shirtCount = 0;

                            for (ProductDTO product : FemaleShirtList) {
                                if (shirtCount >= 2) break;
                                shirtCount++;
            %>
		                <div class="product-item">
		                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
		                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
		                </a>
		                    <h3><%= product.getProductName() %></h3>
		                    <p>₩<%= product.getProductPrice() %></p>
		                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
			                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
			                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
			                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
			                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
			                    <input type="hidden" name="quantity" value="<%= 1 %>">
		                   		<button type="submit">주문하기</button>
		                    </form>
		                </div>
            <%
                            }
                        }

                        if (FemalePantsList != null && !FemalePantsList.isEmpty()) {
                            int pantsCount = 0;

                            for (ProductDTO product : FemalePantsList) {
                                if (pantsCount >= 2) break;
                                pantsCount++;
            %>
		                <div class="product-item">
		                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
		                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
		                </a>
		                    <h3><%= product.getProductName() %></h3>
		                    <p>₩<%= product.getProductPrice() %></p>
		                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
			                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
			                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
			                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
			                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
			                    <input type="hidden" name="quantity" value="<%= 1 %>">
		                   		<button type="submit">주문하기</button>
		                    </form>
		                </div>
            <%
                            }
                        }
                    }
                }
            
                // 성별 선택 안함
                else {
                    if ("셔츠".equals(favorite) && shirtList != null) {
                        for (ProductDTO product : shirtList) { %>
		                <div class="product-item">
		                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
		                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
		                </a>
		                    <h3><%= product.getProductName() %></h3>
		                    <p>₩<%= product.getProductPrice() %></p>
		                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
			                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
			                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
			                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
			                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
			                    <input type="hidden" name="quantity" value="<%= 1 %>">
		                   		<button type="submit">주문하기</button>
		                    </form>
		                </div>
            <%          }
                    } else if ("바지".equals(favorite) && pantsList != null) {
                        for (ProductDTO product : pantsList) { %>
		                <div class="product-item">
		                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
		                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
		                </a>
		                    <h3><%= product.getProductName() %></h3>
		                    <p>₩<%= product.getProductPrice() %></p>
		                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
			                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
			                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
			                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
			                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
			                    <input type="hidden" name="quantity" value="<%= 1 %>">
		                   		<button type="submit">주문하기</button>
		                    </form>
		                </div>
            <%          }
                    } else { %>
                    <p>나만의 코디를 꾸며보세요~</p>
    		<%
                }                    
              }
            %>
            
        </div>
    </section>
    
    <%@ include file="user_module/user_chat.jsp" %>
    <%@ include file="user_module/user_footer.jsp" %>
</body>
</html>
