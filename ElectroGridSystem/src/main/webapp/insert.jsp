<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	
    <script src="assets/js/croppie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	
</head>
<body>
	
    <div class="container">
		
<br>
<p></p>

	                    <div>Add New Payment</div></br>
	                        <form id="addPayment">
	                            <div>
	                                <label>User ID</label>
	                                <div>
	                                    <input type="text" id="user_id" class="form-control" name="user_id">
	                                </div>
	                            </div></br>
	                            
	                            <div>
	                                <label>NIC No</label>
	                                <div>
	                                    <input type="text" id="card" class="form-control" name="card">
	                                </div>
	                            </div></br>
	                            
	                            <div>
	                                <label>Payment Date</label>
	                                <div>
	                                    <input type="date" id="date" class="form-control" name="date">
	                                </div>
	                            </div></br>
	                            
	                            <div>
	                                <label>Total Amount</label>
	                                <div>
	                                    <input type="number" id="total" class="form-control" name="total">
	                                </div>
	                            </div></br>
	                            
	                            <div>
	                                <button type="submit" class="btn btn-success">
	                                    Add
	                                </button>
	                                <a href="edit_n_delete.jsp" class="btn btn-success">
	                                    List
	                                </a>
	                            </div>
	                    	</form>
	                    </div>

</body>
</html>
<script>

$(document).ready(function () {

    $("#addPayment").validate({
        rules: {
        	user_id: "required",
        	card: "required",
        	date: "required",
        	total: "required"
        },
        messages: {
        	user_id: "N Required!",
        	card: "n Required!",
        	date: "p Required!",
        	total: "a Required!"
        },
        submitHandler: function () {
        	var fromData = JSON.stringify({
                "user_id" : $('#user_id').val(),
                "card" : $('#card').val(),
                "date" : $('#date').val(),
                "total" : $('#total').val()
            });
        	
        	console.log(fromData);
            $.ajax({
                type: "POST",
                url: 'app/payment',
                dataType : 'json',
				contentType : 'application/json',
				data: fromData,
                success: function(data){
                	console.log(data);
                	if(data['success']=="success"){
                		alert("Added Successfull!");
                        $("#addPayment")[0].reset();
					}else{
						alert("Unsuccessfull!");
					}
                },
                failure: function(errMsg) {
                	alert("Connection Error!");
                }
            });
        }
    });

    $("#addPayment").submit(function(e) {
        e.preventDefault();
    });

});

</script>