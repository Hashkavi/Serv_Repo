$(document).on("click", "#btnSave", function()
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateserviceForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidserviceCodeSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "serviceAPI", 
 type : type, 
 data : $("#formservice").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onserviceSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onserviceSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divserviceGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function()
		{ 
		 
		 $("#serviceCode").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#topic").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#description").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#date").val($(this).closest("tr").find('td:eq(3)').text()); 
		});




$(document).on("click", ".btnRemove", function()
		{ 
		 $.ajax( 
		 { 
		 url : "serviceAPI", 
		 type : "DELETE", 
		 data : "serviceCode=" + $(this).data("servicecode"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onserviceDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onserviceDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


}