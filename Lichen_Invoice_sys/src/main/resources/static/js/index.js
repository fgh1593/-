/**
 * 
 */

function toInvoice() {
	$("#left div").css("display", "none");
	$("#invoice").css("display", "block");
}
function toIncomeInvoice() {
	$("#left div").css("display", "none");
	$("#income-invoice").css("display", "block");
}

function toCustomer() {
	$("#left div").css("display", "none");
	$("#customer").css("display", "block");
}

function toSupplier() {
	$("#left div").css("display", "none");
	$("#supplier").css("display", "block");
}

function toReport() {
	$("#left div").css("display", "none");
	$("#report").css("display", "block");
}

function getInvoice() {
	$.ajax({
		url: 'getInvoice',
		data: {
			"lichenid": $("#lichenid").val(),
			"taxExclude": $("#taxExclude").val()
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "no") {
				alert("查無此客戶，請確認代號是否正確");
			}
			if (data == "yes") {
				$("#iframe").html('<iframe src="/invoice" frameborder="no" width="100%" height="100%">');
			}

		},
		type: 'POST'
	});
}

function getIncomeInvoice() {
	$.ajax({
		url: 'getIncomeInvoice',
		data: {
			"suptaxnumber": $("#suptaxnumber").val(),
			"incomeTaxExclude": $("#incomeTaxExclude").val(),
			"time": $("#time").val()
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