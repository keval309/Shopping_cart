<%@page import="java.util.*"%>
<%@page import="cn.ajavaProject.dao.ProductDao"%>
<%@page import="cn.ajavaProject.connection.DbCon"%>
<%@page import="cn.ajavaProject.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ProductDao pd =new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();


ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);

}

%>
<!DOCTYPE html>
<html>
<head>

<title>Welcome To Shopping Cart</title>
<%@include file="includes/head.jsp"%>

</head>
<body style="overflow-x:hidden;">
	<%@include file="includes/navbar.jsp"%>


	<div class=m-1 h-100 w-100">
		<div class="row">
		<div class="card-header mt-3 mb-1 px-5 bg-light"><h4>All Products</h4></div>
		<%
		
		if(!products.isEmpty()){
			
			for(Product p:products){%>
			<div class="col-md-3 my-3" >
				<div class="card w-100 h-100"  style="width: 18rem;">
					<img class="card-img-top " src="product-image/<%=p.getImage() %>" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price"> <%=p.getPrice()%></h6>
						<h6 class="category">Category : <%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
						<a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Add to Cart</a>
						<a href="order-now?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy now</a>
						</div>
					</div>
				</div>
			</div>
			
			<% }
		}
		
		%>
			

		</div>
	</div>

	<%@include file="includes/footer1.jsp"%>

</body>
</html>