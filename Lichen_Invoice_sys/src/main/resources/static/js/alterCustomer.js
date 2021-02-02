/**
 * 
 */

function saveAlter() {
	$.ajax({
		url: "/saveAlterCustomer",
		data: {
			"id": $("#id").val(),
			"lichenid": $("#lichenid").val(),
			"storename": $("#storename").val(),
			"storephone": $("#storephone").val(),
			"shopmanager": $("#shopmanager").val(),
			"shopmanagerphone": $("#shopmanagerphone").val(),
			"storeaddress": $("#storeaddress").val(),
			"depositoryaddress": $("#depositoryaddress").val()
		},
		dataType: 'html',
		error: function() {
			alert("資訊未輸入完全");
		},
		success: function(data) {
			alert(data);
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
						location.reload(true);
					},
					type: 'GET'
				});
		},
		type: 'GET'

	})
}

function reload() {
	$.ajax({
		url: "/getSession",
		dataType: 'html',
		type: 'GET',
		data: {
			"id": $("#id").val()
		},
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
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
						location.reload(true);
					},
					type: 'GET'
				});
			
		}
	});
}

function deleteInvoiceInfo(i) {
	var result = window.confirm("是否確定要刪除此筆資料");
	if (result == false) {
		return false;
	}
	$.ajax({
		url: "/deleteInvoiceInfo",
		dataType: "HTML",
		type: "GET",
		data: {
			id: i.id,
			cid: i.value
		},
		error: function(data) {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "刪除成功") {
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
						location.reload(true);
					},
					type: 'GET'
				});
				
			}
		}
	})
}

function deleteCustomer(){
		var a=window.confirm("刪除客戶後，相對應的發票紀錄將全數刪除，請確認是否執行");
			if(a==false){
				return false;
			}
	var result=window.confirm("是否確定要刪除客戶?");
	if(result==false){
		return false;
	}
	$.ajax({
		url: "/deleteCustomer",
		data:{
			"id" : $("#id").val(),
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤");
		},
		success: function(data) {
			alert(data);
			if(data=="刪除成功"){
			$.ajax({
				url: 'selectAllCus',
				data: {
				},
				dataType: 'html',
				error: function() {
					alert("系統錯誤")
				},
				success: function() {
					window.location.href="/showCustomer";
				},
				type: 'GET'
			});
		}
		},
		type: 'GET'
	})
}