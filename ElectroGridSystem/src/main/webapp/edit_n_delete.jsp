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
	                        <div id="usersDiv">
	                    	
	            			</div>
	                    </div>
	                    
	                   
<div id="hideDiv" style="display: none">
    <form id="userRegister">
    <input type="hidden" id="edit_id" name="edit_id">
          <div>
              <label>Name</label>
              <div>
                  <input type="text" id="name" class="form-control" name="name">
              </div>
          </div>
          
          <div>
              <label>Email</label>
              <div>
                  <input type="email" id="email" class="form-control" name="email">
              </div>
          </div>
          
          <div>
              <label>Nic</label>
              <div>
                  <input type="text" id="nic" class="form-control" name="nic">
              </div>
          </div>
          
          <div>
              <label>Phone</label>
              <div>
                  <input type="text" id="phone" class="form-control" name="phone">
              </div>
          </div>
          
          <div>
              <label>Address</label>
              <div>
                  <input type="text" id="address" class="form-control" name="address">
              </div>
          </div>
          
          <div>
              <label>Password</label>
              <div>
                  <input type="password" id="password" class="form-control" name="password">
              </div>
          </div>
          
          <div>
              <label>Privilege</label>
              <div>
                  <select name="privilege" class="form-control" id="privilege">
              		<option value="">Select</option>
              		<option value="1">Admin</option>
              		<option value="0">User</option>
              	</select>
              </div>
          </div>
          
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
                url: "app/user",
                data: JSON.stringify({ 'id' : id}),
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

        $("#userRegister").validate({
        	rules: {
            	name: "required",
            	email: {
                    email: true,
                    required: true
                },
            	nic: "required",
            	phone: "required",
            	address: "required",
            	password: "required",
            	privilege: "required"
            },
            messages: {
            	name: "N Required!",
            	email: {
                    email: "format",
                    required: "required"
                },
                nic: "n Required!",
                phone: "p Required!",
                address: "a Required!",
                password: "p Required!",
                privilege: "p Required!"
            },
            submitHandler: function () {
            	var fromData = JSON.stringify({
            		"id" : $('#edit_id').val(),
                    "name" : $('#name').val(),
                    "email" : $('#email').val(),
                    "nic" : $('#nic').val(),
                    "phone" : $('#phone').val(),
                    "address" : $('#address').val(),
                    "pass" : $('#password').val(),
                    "privilege" : $('#privilege').val()
                });
            	
            	console.log(fromData);

                $.ajax({
                    type: "PUT",
                    url: 'app/user',
                    dataType : 'json',
    				contentType : 'application/json',
    				data: fromData,
                    success: function(data){
                    	if(data['success']=="success"){
                    		alert("Edit Successfull!");
                        	document.getElementById("hideDiv").style.display = "none";
                            $("#userRegister")[0].reset();
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

        $("#userRegister").submit(function(e) {
            e.preventDefault();
        });

    });

    function reload(){
    	$.ajax({
            type: "GET",
            url: "app/user",
            success: function(data){
            	$("#usersDiv").html(data);
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
            url: "app/user/get",
            data: JSON.stringify({ 'id' : id}),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
            	console.log(data),
                $('#edit_id').val(data['id']),
                $('#name').val(data['name']),
                $('#email').val(data['email']),
                $('#nic').val(data['nic']),
                $('#phone').val(data['phone']),
                $('#address').val(data['address']),
                $('#password').val(data['password']),
                $('#privilege').val(data['privilege'])
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });

        
    }
    
    
</script>