/**
 * 
 */


function _key() { 
if(event.keyCode ==13) 
	getInvoice();
} 

function _key2() { 
if(event.keyCode ==13) 
	getIncomeInvoice();
} 

function _key3() { 
if(event.keyCode ==13) 
	searchReport();
}

function _key4() { 
if(event.keyCode ==13) 
	searchCus()
}  


function _key5() { 
if(event.keyCode ==13) 
	searchSup()
} 

function toInvoice() {
	$.getJSON({
		url: 'getInvoiceItem',
		
		data: {
		},
		success: function(data) {
			$("#selitem").html("");
			var list=data;
			for(var i=0;i<list.length;i++){
				$("#selitem").append("<option value='"+list[i].name+"' >"+list[i].name+"</option>");
			}
		}
	});
	$.ajax({
		url: 'getInvoiceNumber',
		data: {
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			if(data=="no"){
				$("#invoiceNumberBox").html("<button type='button' class='col-8 btn btn-secondary btn-block r1' onclick='addInvoiceNum()'>建立發票字軌組</button>");
			}else{
			$("#invoiceNumberBox").html("<input class='form-control r1' type='text' placeholder='"+data+"' disabled>");
			$("#invoiceNumberBox").append("<button type='button' class='col-8 btn btn-secondary btn-block r1' onclick='removeInvoiceNum()'>移除發票字軌組</button>");
			}
		},
		type: 'GET'
	});
	
	$(".func").css("display", "none");
	$("#invoice").css("display", "block");
	$("input").val("");
	$("#seltitle").html("");
	$("#wrongCus").css("display","inline");	
	$(".nopick").css("display", "block");
	$(".pick").css("display", "none");
	$("#a .pick").css("display", "block");
	$("#a .nopick").css("display", "none");
}


function addInvoiceNum(){
	window.open('/addInvoiceNum','新增發票字軌號碼','height=300,width=300');
}

function removeInvoiceNum(){
	$.ajax({
		url: 'removeInvoiceNum',
		data: {
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			toInvoice();
		},
		type: 'GET'
	});
}
function toIncomeInvoice() {
	$(".func").css("display", "none");
	$("#income-invoice").css("display", "block");
	$("#iframe").html('');
	$("input").val("");
	$(".nopick").css("display", "block");
	$(".pick").css("display", "none");
	$("#b .pick").css("display", "block");
	$("#b .nopick").css("display", "none");
}

function toReport() {
	$(".func").css("display", "none");
	$("#report").css("display", "block");
	$("#iframe").html('');
	$("input").val("");
		$(".nopick").css("display", "block");
	$(".pick").css("display", "none");
	$("#c .pick").css("display", "block");
	$("#c .nopick").css("display", "none");
}
function toCustomer() {
	
	$(".func").css("display", "none");
	$("#customer").css("display", "block");
	$("#iframe").html('');
	$("input").val("");
		$(".nopick").css("display", "block");
	$(".pick").css("display", "none");
	$("#d .pick").css("display", "block");
	$("#d .nopick").css("display", "none");
}

function toSupplier() {
	$(".func").css("display", "none");
	$("#supplier").css("display", "block");
	$("#iframe").html('');
	$("input").val("");
		$(".nopick").css("display", "block");
	$(".pick").css("display", "none");
	$("#e .pick").css("display", "block");
	$("#e .nopick").css("display", "none");
}


function toItem() {
	$(".func").css("display", "none");
	$("input").val("");
		$(".nopick").css("display", "block");
	$(".pick").css("display", "none");
	$("#f .pick").css("display", "block");
	$("#f .nopick").css("display", "none");
	$.ajax({
		url: 'toInvoiceItem',
		data: {
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function() {
				$("#iframe").html('<iframe src="/invoice-item" frameborder="no" width="100%" height="100%">');
		},
		type: 'GET'
	});
}

function getInvoice() {
	$.ajax({
		url: 'getInvoice',
		data: {
			"lichenid": $("#lichenid").val(),
			"taxExclude": $("#taxExclude").val(),
			"seltitle": $("#seltitle").val(),
			"selitem": $("#selitem").val()
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "no") {
				alert("查無此客戶，請確認代號是否正確");
			}
			if(data == "notax"){
				alert("未輸入金額")
			}
			if (data == "yes") {
				$("#iframe").html('<iframe src="/invoice" frameborder="no" width="100%" height="100%">');
			}

		},
		type: 'POST'
	});
}

function getTitle(){
	$.getJSON({
		url: 'selectByLcid',
		data: {
			"lichenid": $("#lichenid").val()
		},
		success: function(data) {
			if (data == "no") {
			
				$("#wrongCus").css("display","inline");
				$("#seltitle").html("");
			}
		
			if (data != "no") {
				$("#wrongCus").css("display","none");
				$("#seltitle").html("");
				var list=data;
				for (i=0 ;i<list.length;i++){
					$("#seltitle").append("<option value='"+list[i].invoiceTitle+"'>"+list[i].invoiceTitle+"</option>");
				}
				
			}

		},
	});
}

function getIncomeInvoice() {
	$.ajax({
		url: 'getIncomeInvoice',
		data: {
			"suptaxnumber": $("#suptaxnumber").val(),
			"incomeTaxExclude": $("#incomeTaxExclude").val(),
			"time": $("#time").val(),
			"incomeInvoiceHead" :$("#incomeInvoiceHead").val(),
			"incomeInvoiceNum" :$("#incomeInvoiceNum").val()
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "no") {
				alert("查無此廠商，請確認統編是否正確，或者建立新的廠商資料");
			}
			if (data == "notax") {
				alert("未輸入發票金額");
			}
			if (data == "notime") {
				alert("未輸入發票時間");
			}
			if (data == "yes") {
				$("#iframe").html('<iframe src="/income-invoice" frameborder="no" width="100%" height="100%">');
			}

		},
		type: 'POST'
	});
}

function addCustomer() {
	$("#iframe").html('<iframe src="/addCustomer" frameborder="no" width="100%" height="100%">');
}

function addInvoiceInfo() {
	$("#iframe").html('<iframe src="/addInvoiceInfo" frameborder="no" width="100%" height="100%">');
}

function addSupplier() {
	$("#iframe").html('<iframe src="/addSupplier" frameborder="no" width="100%" height="100%">');
}

function searchCus() {
	$.ajax({
		url: 'searchCustomer',
		data: {
			"searchCus": $("#searchCus").val(),
	
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "no") {
				alert("查無資料");
			}
		
			if (data == "yes") {
				$("#iframe").html('<iframe src="/showCustomer" frameborder="no" width="100%" height="100%">');
			}

		},
		type: 'POST'
	});
}

function searchSup() {
	$.ajax({
		url: 'searchSupplier',
		data: {
			"searchSup": $("#searchSup").val(),
	
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "no") {
				alert("查無資料");
			}
		
			if (data == "yes") {
				$("#iframe").html('<iframe src="/showSupplier" frameborder="no" width="100%" height="100%">');
			}

		},
		type: 'POST'
	});
}

function searchReport() {
	$.ajax({
		url: 'searchReport',
		data: {
			"startTime" : $("#startTime").val(),
			"endTime" : $("#endTime").val()
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
				$("#iframe").html('<iframe src="/showReport" frameborder="no" width="100%" height="100%">');
		},
		type: 'POST'
	});
}

function selectAllCus() {
	$.ajax({
		url: 'selectAllCus',
		data: {
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			$("#iframe").html('<iframe src="/showCustomer" frameborder="no" width="100%" height="100%">');
		},
		type: 'POST'
	});
}

function selectAllSup() {
	$.ajax({
		url: 'selectAllSup',
		data: {
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			$("#iframe").html('<iframe src="/showSupplier" frameborder="no" width="100%" height="100%">');
		},
		type: 'POST'
	});
}