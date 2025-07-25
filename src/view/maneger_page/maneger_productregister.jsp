<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록 페이지</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_productregister.css">

</head>
<body>
    <h1>상품 등록</h1>
    
    <div class="container1">
        <form action="<%= request.getContextPath() %>/registerProduct" method="post" enctype="multipart/form-data">
            <h2>카테고리</h2>
            <select name="product_category" id="category">
                <option value="상의">상의</option>
                <option value="하의">하의</option>
            </select>
            
            <br/>
            
            <h2>성별</h2>
          	<select name="product_gender" id="gender">
                <option value="남자">남자</option>
                <option value="여자">여자</option>
            </select>
            
            <br/>
            
            <h2>상품명</h2>
            <input type="text" name="product_name" id="name" required/>
            
            <br/>
            
            <h2>가격</h2>
            <input type="number" name="product_price" id="price" required step="0.01"/>
            
            <br/>
            
            <h2>재고 수량</h2>
            <input type="number" name="product_stock" id="number" required/>
            
            <br/>
            
            <h2>상품에 적합한 온도</h2>
            <input type="number" name="product_temperature" id="temperature"/>
            
            <br/>
            
            <h2>상품 이미지</h2>
            <input type="file" name="product_image" id="img"/>
            
            <br/>
            
            <h2>상품 설명</h2>
            <textarea name="product_description" id="explan" ></textarea>
            
            <br/>
            <input type="submit" value="상품등록" id="btn"/>
        </form>
    </div>

</body>
</html>
