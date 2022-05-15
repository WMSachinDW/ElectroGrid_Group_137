<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
	
    
    <script src="assets/js/croppie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	
</head>
<body>
	
    <div class="container">
		
<br>
<p></p>
	                    <div>Payment Details</div>
	                    <div>
	                        <div id="paymentDiv">
	                    	
	            			</div>
	                    </div>
	                    
	                   
<div id="hideDiv" style="display: none">
    <form id="addPayment">
    <input type="hidden" id="edit_id" name="edit_id">
	                            <div>
	                                <label>User ID</label>
	                                <div>
	                                    <input type="text" id="user_id" class="form-control" name="user_id">
	                                </div>
	                            </div></br>
          
	                            <div>
	                                <label>Card No</label>
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
                  Edit
              </button>
          </div>
  	</form>
</div> 
	                    
</div>
	
</body>
</html>

<script>

    function deletes(id) {
    	if (confirm("Delete ?") == true) {
        	$.ajax({
                type: "DELETE",
                url: "app/payment",
                data: JSON.stringify({ 'user_id' : user_id}),
                dataType: "json",
    			contentType : 'application/json',
                success: function(data){
                	if(data['success']=="success"){
                		alert("Delete Successfull!");
    					reload();
    				}else if(data['success']=="0"){
    					alert("Delete Unsuccessful!");
    				}
                },
                failure: function(errMsg) {
                    alert('Error');
                }
            });
    	}
    }

    $(document).ready(function () {

        $("#addPayment").validate({
        	rules: {
            	user_id: "required",
            	card: "required",
            	date: "required",
            	total: "required"
            },
            messages: {
            	user_id: "User ID Required!",
            	card: "Card No Required!",
            	date: "Date Required!",
            	total: "Total Amount Required!"
            },
            submitHandler: function () {
            	var fromData = JSON.stringify({
            		"user_id" : $('#edit_id').val(),
                    "card" : $('#card_number').val(),
                    "date" : $('#date').val(),
                    "total" : $('#total').val()
                });
            	
            	console.log(fromData);

                $.ajax({
                    type: "PUT",
                    url: 'app/payment',
                    dataType : 'json',
    				contentType : 'application/json',
    				data: fromData,
                    success: function(data){
                    	if(data['success']=="success"){
                    		alert("Edit Successfull!");
                        	document.getElementById("hideDiv").style.display = "none";
                            $("#addPayment")[0].reset();
    						reload();
    					}else{
    						alert("Unsuccessful!");
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

    function reload(){
    	$.ajax({
            type: "GET",
            url: "app/payment",
            success: function(data){
            	$("#paymentDiv").html(data);
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });
    }

    reload();
    
    function edit(id) {
    	document.getElementById("hideDiv").style.display = "block";
    	$.ajax({
            type: "POST",
            url: "app/payment/get",
            data: JSON.stringify({ 'user_id' : user_id}),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
            	console.log(data),
                $('#edit_id').val(data['user_id']),
                $('#card_number').val(data['card']),
                $('#date').val(data['date']),
                $('#total').val(data['total'])
            },
            failure: function(errMsg) {
                alert('Error');
            }
        }); 
    }
    
    
</script>