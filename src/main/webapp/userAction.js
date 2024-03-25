
function cookieCheck(){
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var responseData = JSON.parse(xhr.responseText);
			 console.log(responseData);
			 if(responseData.statusCode == 200){
				if(responseData.role == "user"){
					billInvoice();
				}
				
			 }else if(responseData.statusCode == 500){
					window.location.href = "index.html";
				}
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/EBM/cookieCheck", true);
	 xhr.send();
}


function billInvoice(){
	console.log("hii");
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var response = JSON.parse(this.responseText); 
			if(response.statusCode == 200){
				var billContent = response.Content;
				var amount =  billContent.amount;
				var paymentDate =  billContent.paymentDate;
				var dueDate =  billContent.dueDate;
				var paymentStatus = billContent.paymentStatus;
				createBill(1234,amount,dueDate,paymentDate,paymentStatus)
			}else{
				console.log(response);
			}
		}
	 }
	 xhr.open("POST", "http://localhost:8080/EBM/currentMonthBill", true);
	 xhr.send();
}


function createBill(billID, amount,DueDate, paymentDate, paymentStatus){
console.log("hii");
var displayContainer = document.getElementById("displayRequest");
var containerDiv = document.createElement('div');
containerDiv.classList.add('container');

var billDiv = document.createElement('div');
billDiv.classList.add('bill');

var billHeaderDiv = document.createElement('div');
billHeaderDiv.classList.add('bill-header');
billHeaderDiv.innerHTML = '<h2>Electric Bill</h2>';

var billDetailsDiv = document.createElement('div');
billDetailsDiv.classList.add('bill-details');
billDetailsDiv.innerHTML = '<p><strong>Bill ID:</strong>'+ billID+'</p><p><b>Amount:</b>Rs.'+ amount+'</p><p><b>Due Date:</b>'+ DueDate+'</p><p><b>Payment Date:</b>'+ paymentDate+'</p>';

var totalDiv = document.createElement('div');
totalDiv.innerHTML = '<p>Total: $50.00</p>';

	var payButtonDiv = document.createElement('div');
	payButtonDiv.classList.add('payButton');
	payButtonDiv.innerHTML = '<button>Pay Now</button>';
	
	
	
billDiv.appendChild(billHeaderDiv);
billDiv.appendChild(billDetailsDiv);
billDiv.appendChild(totalDiv);
billDiv.appendChild(payButtonDiv);




containerDiv.appendChild(billDiv);
displayContainer.innerHTML = "";
displayContainer.appendChild(containerDiv);

}

/*function viewProfile(){
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

MonthName
: 
"September-2023"
amount
: 
150.75
dueDate
: 
"2023-09-20"
leadingValue
: 
100.5
paymentDate
: 
"2023-09-12"
paymentStatus
: 
"PAID"

*/


function viewPaymentHistory(){
	console.log("Gopika");
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			 var responseData = JSON.parse(xhr.responseText);
			 console.log(responseData);
			 if(responseData.statusCode == 200){
				 createBillHeaderDisplay();
				 console.log( );
				 createBillRows(responseData.billList);
				// responseData.billList.forEach((billInfo) => createBillRows(billInfo));
				 console.log("SUcess Bill");
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/EBM/billHistoryServelt", true);
	 xhr.send();
}



function createBillHeaderDisplay(){
var displayContainer = document.getElementById("displayRequest");
	
var divElement = document.createElement("div");
divElement.id = "useBillHistoryContainer";
var table = document.createElement("table");
var thead = document.createElement("thead");
var headerRow = document.createElement("tr");
var headers = ["Bill Type", "Bill ID", "Amount", "Due Date", "Payment Date", "Payment Status", "Total", "Action"];
headers.forEach(function(headerText) {
    var th = document.createElement("th");
    th.textContent = headerText;
    headerRow.appendChild(th);
});
thead.appendChild(headerRow);
table.appendChild(thead);
var tbody = document.createElement("tbody");
tbody.id = "billTableBody";
table.appendChild(tbody);
divElement.appendChild(table);
displayContainer.innerHTML = "";
displayContainer.appendChild(divElement);

}


 function createBillRows(data) {
        var tableBody = document.getElementById("billTableBody");
        console.log();
        
        data.forEach(function(bill) {
            var row = document.createElement("tr");
            row.innerHTML = `
                <td>${bill.type}</td>
                <td>${bill.id}</td>
                <td>${bill.amount}</td>
                <td>${bill.dueDate}</td>
                <td>${bill.paymentDate}</td>
                <td>${bill.status}</td>
                <td>${bill.total}</td>
                <td><button class="view-details">View Details</button></td>
            `;
            tableBody.appendChild(row);
        });
    }




function displayCompliantBox(){
	
}

function logout(){
	console.log("hii");
	var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200) {
			  var response = JSON.parse(this.responseText);
			  console.log(response);
			  if(response.statusCode == 200){
				  console.log("logout");
				   /*window.location.href = "index.html";*/
				   cookieCheck();
			  }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/EBM/logoutCookie", true);
	 xhr.send();
}


//graphLayout();

function graphLayout() {
    // Data for electric usage and cost
    console.log("Graph");
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    var usageData = [100, 120, 110, 130, 140, 150, 160, 170, 180, 190, 200, 510]; // Example electric usage data
    var costData = [50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105]; // Example electric cost data

	var canvas = document.createElement('canvas');
    canvas.width = 800;
    canvas.height = 400;
    
    var chartContainer = document.getElementById('displayRequest');
    chartContainer.appendChild(canvas);
	 var ctx = canvas.getContext('2d');
    // Create a line chart
    //var ctx = document.getElementById('displayRequest').getContext('2d');
    /*var usageCostChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: months,
            datasets: [{
                label: 'Electric Usage',
                data: usageData,
                borderColor: 'rgba(255, 99, 132, 1)',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                yAxisID: 'usage-axis'
            }, {
                label: 'Electric Cost',
                data: costData,
                borderColor: 'rgba(54, 162, 235, 1)',
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                yAxisID: 'cost-axis'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    id: 'usage-axis',
                    type: 'linear',
                    position: 'left',
                    scaleLabel: {
                        display: true,
                        labelString: 'Electric Usage'
                    }
                }, {
                    id: 'cost-axis',
                    type: 'linear',
                    position: 'right',
                    scaleLabel: {
                        display: true,
                        labelString: 'Electric Cost ($)'
                    },
                    ticks: {
                        // Include a dollar sign in the ticks
                        callback: function (value, index, values) {
							console.log("chart works");
                            return '$' + value;
                        }
                    }
                }]
            }
        }
    });*/
    
    
     var usageCostChart = new Chart(ctx, {
        type: 'bar', // Change chart type to bar
        data: {
            labels: months,
            datasets: [{
                label: 'Electric Usage',
                data: usageData,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }, {
                label: 'Electric Cost',
                data: costData,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
    
}





