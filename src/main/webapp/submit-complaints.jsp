<form action="SubmitComplaintServlet" method="post">
    <label for="userId">User ID:</label>
    <input type="number" name="userId" id="userId" required><br>

    <label for="retailerId">Retailer ID:</label>
    <input type="number" name="retailerId" id="retailerId" required><br>

    <label for="complaintText">Complaint Text:</label>
    <textarea name="complaintText" id="complaintText" required></textarea><br>

    <input type="submit" value="Submit Complaint">
</form>
