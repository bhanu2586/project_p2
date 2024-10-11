
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Reviews</title>
</head>
<body>
    

    <h3>Add a Review</h3>
    <form action="SubmitReviewServlet" method="post">
        <input type="hidden" name="productId" value="<%= request.getParameter("productId") %>">
       
        
        <label for="rating">Rating (1-5):</label>
        <input type="number" name="rating" min="1" max="5" required>
        <br>
        <label for="reviewText">Review:</label>
        <textarea name="reviewText" rows="4" cols="50"></textarea>
        <br>
        <button type="submit">Submit Review</button>
    </form>
</body>
</html>
