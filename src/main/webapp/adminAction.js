
// assecpt and reject request
// add a logger to all
// bill view in an users
// complaints View both admin and users
//contract users and admin in the same time

function viewAllCustomers(){
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 console.log(this.responseText);
			var response = JSON.parse(this.responseText);
			  console.log(response.statusCode);
			 if(response.statusCode == 200){
				// create a ui
				displayAllCustomer(response.customerLists);
			 }else{
				 alert("Something went wrong");
			 }
			 
			 console.log(response);
		 }
	 }					 
	xhr.open("POST", "http://localhost:8080/EBM/viewCustomers", true);	
	xhr.send();
}

var displayContainer = document.getElementById("displayRequest");
function displayAllCustomer(allCustomerList){
    console.log(displayContainer)
	var table = document.createElement("table");
	table.className = "allConstomerTable";
    var tr = document.createElement("tr");
    var headers = ["User ID", "First Name", "Last Name", "Phone Number", "Address", "Select"];
    headers.forEach(headerText => { 
			var th = document.createElement("th");
			
            th.textContent = headerText;
            tr.appendChild(th);
     });
        table.appendChild(tr);
       
     var keys = ["UserId","FirstName","lastName","PhoneNumber","Address"]
     
     allCustomerList.forEach(user => {
		 console.log(user);
            var tr = document.createElement("tr");
             tr.setAttribute("id",user.UserId);
             console.log(user.length);
             for(let i=0;i<keys.length;i++){
				 console.log("hii");
				var td = document.createElement("td");
				console.log(user[keys[i]]);
                td.textContent = user[keys[i]];
                tr.appendChild(td);
			 }
            
      
            var buttonTd = document.createElement("td");
            var button = document.createElement("button");
            button.textContent = "View details";
            
             buttonTd.appendChild(button);
             tr.appendChild(buttonTd);
             table.appendChild(tr);
	});
	displayContainer.innerHTML = " ";
	displayContainer.appendChild(table);
	// add into container 
}

function logout(){
			  
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			  var response = JSON.parse(this.responseText);
			  console.log(response);
			  if(response.statusCode == 200){
				   window.location.href = "index.html";
			  }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/EBM/logoutCookie", true);
	 xhr.send();
}


function userNewRequest(){
	
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
			 if(this.readyState == 4 && this.status == 200) {
				 var response = JSON.parse(this.responseText);
				 console.log(response);
				if(response.statusCode == 200){
					displayContainer.innerHTML = " ";
					response.content.forEach((item) => {
					    console.log(item);
					    createNewRequestDisplay(item);
					});
				} 
			 }
    }
    
    
    xhr.open("POST", "http://localhost:8080/EBM/newUserRequestView", true);
	xhr.send();
}



function createNewRequestDisplay(userRequest){
	// add reference number id
	
	
	var requestContainer = document.createElement("div");
    requestContainer.classList.add("requestContainer");
    requestContainer.setAttribute("id",userRequest.Reference);
    
    var newRequestImages = document.createElement("div");
    newRequestImages.classList.add("newRequestImges");

	var h3 = document.createElement("h3");
    h3.textContent = "Form Number "+userRequest.Reference;
    newRequestImages.appendChild(h3);
    
     var userImage = document.createElement("div");
    userImage.classList.add("userImage");
    newRequestImages.appendChild(userImage);
    
     requestContainer.appendChild(newRequestImages);
     
     var viewTextLayout = document.createElement("div");
    viewTextLayout.classList.add("viewTextLayout");
    
    var newUserContent = [ "First Name","Last Name","Phone no.","Address" ];
     
    var divInsideLayout = document.createElement("div");
     // firstValue
    var displayContentText = newRequestDisplay(newUserContent[0],userRequest.FirstName);
    divInsideLayout.appendChild(displayContentText);
    
    displayContentText = newRequestDisplay(newUserContent[1],userRequest.lastName);
    divInsideLayout.appendChild(displayContentText);
    
    displayContentText = newRequestDisplay(newUserContent[2],userRequest.PhoneNumber);
    divInsideLayout.appendChild(displayContentText);
    
    	var addressDiv =document.createElement("div");
        addressDiv.classList.add("newUserRequestContainer");
        var pAddress = document.createElement("p");
        pAddress.textContent = newUserContent[3];
        
        var ul = document.createElement("ul");
        console.log(userRequest.Address);
        var addressItems = userRequest.Address.split(",");
        console.log(addressItems);
        /* Add address in ui */
        for(let i=1;i<addressItems.length;i++){
			console.log(addressItems[i]);
				var li = document.createElement("li");
				if(i==1){
					li.textContent = (addressItems[0]+" ," +addressItems[i]);
				}else{
		        li.textContent = addressItems[i];
		       
		        }
		       ul.appendChild(li);
		}
        
        
    var divAddressViewBox = document.createElement("div");
    divAddressViewBox.setAttribute("id", "addressViewBox");
    divAddressViewBox.appendChild(ul);
    addressDiv.appendChild(pAddress);
    addressDiv.appendChild(divAddressViewBox);
    divInsideLayout.appendChild(addressDiv);
    var requestButtons = createRequestButton();
    divInsideLayout.appendChild(requestButtons);
    
    requestContainer.appendChild(newRequestImages);
    requestContainer.appendChild(divInsideLayout);
    displayContainer.appendChild(requestContainer);
}


function newRequestDisplay(contentText, contentValue){
    	var newUserRequestContainer = document.createElement("div");
        newUserRequestContainer.classList.add("newUserRequestContainer");
        var p1 = document.createElement("p");
        p1.textContent = contentText;
        var p2 = document.createElement("p");
        p2.textContent = contentValue; 
        newUserRequestContainer.appendChild(p1);
        newUserRequestContainer.appendChild(p2);	
        return newUserRequestContainer;
}

function createRequestButton(){
	var userButtonContainer = document.createElement("div");
    userButtonContainer.classList.add("userButtonContainer");
    var rejectButton = document.createElement("button");
    rejectButton.textContent = "Reject";
    rejectButton.onclick =  function() {
   		 rejectUserRequest(this);
    };
    var acceptButton = document.createElement("button");
    acceptButton.textContent = "Accept";
    acceptButton.onclick = function() {
   		 acceptUserNewRequest(this);
	};
    userButtonContainer.appendChild(rejectButton);
    userButtonContainer.appendChild(acceptButton);
    return userButtonContainer;
}


function acceptUserNewRequest(parameter){
	var acceptuserRequestID = parameter.parentNode.parentNode.parentNode.id;
	var requestObj = {
		"referenceId": acceptuserRequestID,
		"reason": "approved"
	}
	updateNewUsersRequest(requestObj);
}

function rejectUserRequest(parameter){
	var rejectedUserRequestID = parameter.parentNode.parentNode.parentNode.id;
	var requestObj = {
		"referenceId": rejectedUserRequestID,
		"reason": "rejected"
	}
	
	updateNewUsersRequest(requestObj);
}


function updateNewUsersRequest(updateOBJ){
	var xhr = new XMLHttpRequest();
	console.log(updateOBJ);
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var response = JSON.parse(this.responseText);
			 if(response.statusCode == 200){
				 userNewRequest();
			 }else{
				 alert("Something went wrong. Please try again ");
			 }
		 }	 
    }
    
    
    xhr.open("POST", "http://localhost:8080/EBM/updateNewuserStatus", true);
    xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(updateOBJ));
	
}
