/*EBM*/

var loginLayout =  document.getElementsByClassName("loginContainer")[0];
var homeContainer = document.getElementsByClassName("homeBody")[0];
var newUserRegister = document.getElementsByClassName("newUserInputForm")[0];
var newUserCheckForm = document.getElementsByClassName("referenceBody")[0];

function cookieCheck(){
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var responseData = JSON.parse(xhr.responseText);
			 console.log(responseData);
			 if(responseData.statusCode == 200){
				if(responseData.role == "admin"){
					adminLogin(); 
				}
				else{
					userLogin();
				}
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/EBM/cookieCheck", true);
	 xhr.send();
}

function adminLogin(){
	window.location.href = "adminUser.html";
}

function userLogin(){
	window.location.href = "userHome.html";
}


function loginPage(){
    document.getElementsByClassName("homeContainer")[0].style.display = "none";
    loginLayout.style.display = "flex";
}

function newUserForm(){
	 homeContainer.innerHTML = " ";
     homeContainer.style.display = "block";
     newUserRegister.style.display = "block";
     homeContainer.append(newUserRegister);
   
  /* homeContainer.innerHTML = newUserRegister;*/ 
}

function newUserLayout(){
    loginLayout.style.display = "none";
    document.getElementsByClassName("homeContainer")[0].style.display = "block";
   /* newUserRegister.style.display = "block";
    homeContainer.appendChild(newUserRegister);*/
    
    newUserForm();
   
}

function checkNewUseForm(){
	homeContainer.innerHTML = " ";
	homeContainer.style.display = "block";
	newUserCheckForm.style.display = "flex";
	homeContainer.append(newUserCheckForm);
}

function checkNewUserStatusRequest(){
	var requestReferenceID = document.getElementById("referenceId").value;
	console.log(requestReferenceID);
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var response = JSON.parse(this.responseText);
			 console.log(response);
			 if(response.statusCode == 200){
				var isPending = (response.status == "Pending");
				displayNewUserResult(response, isPending); 
			 }
		 }
	}
	
	xhr.open("POST", "http://localhost:8080/EBM/checkNewUserStatus", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send('referenceId='+requestReferenceID);
}


function displayNewUserResult(newUserStatus, isPending){
	console.log("hii");
	var userRequestDisplayView = document.getElementsByClassName("referenceDisplayBody")[0];
	var table = document.createElement("table");
	 table.className = "userReferenceViewTable";
	 var headerRow = document.createElement("tr");
	 var th = document.createElement("th");
     th.textContent = "Reference ID";
     headerRow.appendChild(th);
     th = document.createElement("th");
     th.textContent = "Status";
     headerRow.appendChild(th);
     if(!isPending){
		 th = document.createElement("th");
	     th.textContent = "User Name";
	     headerRow.appendChild(th);
	     th = document.createElement("th");
	     th.textContent = "Password"; 
	 }
	 
	 table.appendChild(headerRow);
     
     /* added a body of the table */
     headerRow = document.createElement("tr");
	 th = document.createElement("td");
     th.textContent = newUserStatus.referenceId;
     headerRow.appendChild(th);
     th = document.createElement("td");
     th.textContent = newUserStatus.status;
     headerRow.appendChild(th);
     if(!isPending){
		 th = document.createElement("td");
	     th.textContent = newUserStatus.userName;
	     headerRow.appendChild(th);
	     th = document.createElement("td");
	     th.textContent = newUserStatus.password; 
	 }
     /* add head row*/
     table.appendChild(headerRow);
     
     userRequestDisplayView.innerHTML = "";
     console.log(userRequestDisplayView);
     userRequestDisplayView.appendChild(table);
     console.log(userRequestDisplayView);
}

var inConsumerNoDisplay = true;

function alreadyHaveAccount(isUser){
    console.log(isUser.value);
     var userAccount = '<div class="newUserInputContainer"><label class="newUserText">Consumer No</label> <input type="text" id="consumerNo" required> </div>';
     var isAlreadyUser = isUser.value;
     if(isAlreadyUser == "yes" && inConsumerNoDisplay == true){
        var divElement = document.createElement("div");
        divElement.setAttribute("id","newUserConsumerInput"); 
        divElement.innerHTML = userAccount;
        var newUserContainer = document.getElementsByClassName("newUserInputContainer1")[0];
        newUserContainer.insertAdjacentElement('afterend', divElement);
        inConsumerNoDisplay = false;
     }else{
        inConsumerNoDisplay = true;
        document.getElementById("newUserConsumerInput").style.display = "none";
     }
}




// login request 
function loginToJs(){
	var loginInput = {};
	loginInput["username"] = document.getElementById("usernameLogin").value;
	loginInput["password"] = document.getElementById("passwordLogin").value;
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
				var responseTextJSON = JSON.parse(xhr.responseText);

				if(responseTextJSON.statusCode == 200){
					if(responseTextJSON.role == "admin"){
						 window.location.href = "adminUser.html";/// set a admin
					 	viewAllCustomers();
					}else if(responseTextJSON.role == "user"){
						window.location.href = "userHome.html";
					}
				}else{
					alert(responseTextJSON.message);
				}

			/* switch(this.responseText){
				 case "0":
					  alert("is not valid user name");
					 console.log("is not valid user name");
					 break;
				 case "1":
					 alert("Password is not correct");
					 console.log("Password is not correct");
					 break;
				case "2": // it is admin
					 window.location.href = "adminUser.html";/// set a admin
					 console.log("User is correct");
					 viewAllCustomers();
					 break;
				case "3":
					 window.location.href = "userHome.html";
			 }*/
		 }
	 }					 
	xhr.open("POST", "http://localhost:8080/EBM/Login", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send('username='+loginInput.username+"&password="+loginInput.password);
}

// new user request
function addReqNewUser(){
	checkUserInput();
	if (errorMessage != "") {
	    alert(errorMessage);
	    return;
    }
	var newUserDetails = {};
	newUserDetails["firstName"] = document.getElementById("firstName").value;
	newUserDetails["lastName"] = document.getElementById("lastName").value;
	newUserDetails["phoneNumber"] = document.getElementById("phoneNumber").value.trim();
	newUserDetails["street"] = document.getElementById("street").value;
	newUserDetails["district"] = document.getElementById("district").value;
	newUserDetails["state"] = document.getElementById("state").value;
	//newUserDetails["pincode"] = document.getElementById("postCode").value;
	 var request = document.querySelector('input[name="isAccount"]:checked');
     
  var consumerNo = "no";
	
	if(request == "yes"){
		 consumerNo = document.getElementById("consumerNo").value;
	}
	newUserDetails["userID"] = consumerNo;
	console.log(newUserDetails);
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var userReferenceId = JSON.parse(xhr.responseText);
				 if( userReferenceId.Statuscode == 200){
					   alert("Reference No. "+ userReferenceId.referenceId);
				 }
				 else if(userReferenceId.Statuscode == 500){
					  alert("Address already exists");
				 }else if(userReferenceId.Statuscode == 501){
				  alert("Address already add request");
			 	}
			 }
			 
		 }
			 
	xhr.open("POST", "http://localhost:8080/EBM/newUserServelt", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(newUserDetails));
	
}

var errorMessage = "";
function checkUserInput(){
	errorMessage = "";
	var firstName = document.getElementById("firstName").value.trim();
	var lastName = document.getElementById("lastName").value.trim();
	var phoneNumber = document.getElementById("phoneNumber").value.trim();
	var street = document.getElementById("street").value.trim();
	var district = document.getElementById("district").value.trim();
	var state = document.getElementById("state").value.trim();
	//var pincode = document.getElementById("postCode").value.trim();
	var request = document.querySelector('input[name="isAccount"]:checked');
	
	
	
	// Check for empty fields
	if (firstName === "") {
	    errorMessage += "First name is required.\n";
	}
	if (lastName === "") {
	    errorMessage += "Last name is required.\n";
	}
	if (phoneNumber === "") {
	    errorMessage += "Phone number is required.\n";
	} else if (phoneNumber.length !== 10) {
	    errorMessage += "Phone number must be 10 digits long.\n";
	}
	if (street === "") {
	    errorMessage += "Street is required.\n";
	}
	if (district === "") {
	    errorMessage += "District is required.\n";
	}
	if (state === "") {
	    errorMessage += "State is required.\n";
	}
	/*if (pincode === "") {
	    errorMessage += "Pincode is required.\n";
	}*/
	if (!request) {
	    errorMessage += "Please select an option for request.\n";
	}	
}


function viewProfile(){
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var response = JSON.parse(this.responseText);
			 console.log(response);
			  console.log(response.StatusCode);
			if(response.StatusCode == 200){
				var userProfile = response.Content;
				console.log(response.StatusCode);
				console.log(userProfile);
				var firstName = userProfile.FirstName;
				console.log(firstName);
				var lastName = userProfile.lastName;
				var phoneNumber = userProfile.PhoneNumber;
				var address = (userProfile.Address).split(",");
				createProfile(firstName,lastName,phoneNumber,address[0],address[1],address[2]);
			}else{
				// do a current bill is not
				console.log(response);
			}
		}
	 }
	 xhr.open("POST", "http://localhost:8080/EBM/viewProfile", true);
	 xhr.send();
}


function createProfile(firstName,secondName,phoneNumber,streetName,districtName,stateName){
var displayContainer = document.getElementById("displayRequest");
var divContainer =  document.createElement('div');

var profileContainer =  document.createElement('div');
profileContainer.classList.add('profileDisplayContainer');

var profileImageContainer = document.createElement('div');
profileImageContainer.classList.add('profileImage');
profileContainer.appendChild(profileImageContainer);

console.log("hiiii");
var secondDiv = document.createElement('div');
secondDiv.classList.add('profileContainerText');
secondDiv.innerHTML = '<p>First Name</p><p>'+firstName+'</p>';


var thirdDiv = document.createElement('div');
thirdDiv.classList.add('profileContainerText');
thirdDiv.innerHTML = '<p>Last Name</p><p>'+secondName+'</p>';


var fourthDiv = document.createElement('div');
fourthDiv.classList.add('profileContainerText');
fourthDiv.innerHTML = '<p>Phone no.</p><p>'+phoneNumber+'</p>';


var fifthDiv = document.createElement('div');
fifthDiv.classList.add('profileContainerText');
fifthDiv.innerHTML = '<p>Address</p><div><ul><li>'+streetName+'+</li><li>'+districtName+'</li><li>'+stateName+'</li></ul></div>';


profileContainer.appendChild(secondDiv);
profileContainer.appendChild(thirdDiv);
profileContainer.appendChild(fourthDiv);
profileContainer.appendChild(fifthDiv);

divContainer.appendChild(profileContainer);
displayContainer.innerHTML = divContainer.innerHTML;

}



var response;
// if the admin are login




/*
function DistrictsName() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parse the JSON response
            var districtNames = JSON.parse(xhr.responseText);

            // Populate the dropdown options
            var districtDropdown = document.getElementById("districtDropdown");
            districtNames.forEach(function(district) {
                var option = document.createElement("option");
                option.value = district;
                option.textContent = district;
                districtDropdown.appendChild(option);
                
                
                
                
                
                
                var responseText = xhr.responseText;
                
                 var districtNames = responseText.split('\n');

    // Populate the dropdown options
				    var districtDropdown = document.getElementById("districtDropdown");
				    districtDropdown.innerHTML = "";  // Clear existing options
				
				    districtNames.forEach(function(district) {
				        var option = document.createElement("option");
				        option.value = district;
				        option.textContent = district;
				        districtDropdown.appendChild(option);
            });
        }
    };

    // Open a POST request to the servlet
    xhr.open("POST", "DistrictServlet", true);

    // Set the Content-Type header for POST requests
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Send the request with an empty body (since it's a GET-like request)
    xhr.send();
}

DistrictsName();*/
