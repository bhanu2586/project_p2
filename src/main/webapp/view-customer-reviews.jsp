<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Retailer Product Reviews</title>
</head>
<body>
    <h2>All Product Reviews</h2>
    <%
        // Database connection parameters
        String dbURL = "jdbc:mysql://localhost:3306/shoppingsystem";
        String dbUser = "root";
        String dbPassword = "Bhanu@123";

        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        try {
            // Establish database connection
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL,dbUser,dbPassword );

            // Assume retailer ID is retrieved from session
            Integer retailerIdObj = (Integer) session.getAttribute("retailerId"); // Cast to Integer
            int retailerId = (retailerIdObj != null) ? retailerIdObj.intValue() : 0; // Convert to int safely

            // SQL query to get reviews for retailer's products
            String sql = "SELECT r.product_id, r.rating, r.review_text FROM reviews r";
            pstmt = conn.createStatement();
            
            rs = pstmt.executeQuery(sql);

    %>
    <table border="1">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Rating</th>
                <th>Review</th>
            </tr>
        </thead>
        <tbody>
    <%
            // Display the reviews
            boolean hasReviews = false;
            while (rs.next()) {
                hasReviews = true;
    %>
            <tr>
                <td><%= rs.getInt("product_id") %></td>
                <td><%= rs.getInt("rating") %></td>
                <td><%= rs.getString("review_text") %></td>
            </tr>
    <%
            }
            if (!hasReviews) {
    %>
            <tr>
                <td colspan="3">No reviews found for your products.</td>
            </tr>
    <%
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("Error closing resources: " + e.getMessage());
            }
        }
    %>
        </tbody>
    </table>
</body>
</html>
