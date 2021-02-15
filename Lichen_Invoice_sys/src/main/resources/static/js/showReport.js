/**
 * 
 */

function deleteInvoice(i) {
	var result = window.confirm("是否確定要刪除此筆資料");
	if (result == false) {
		return false;
	}
	$.ajax({
		url: "/deleteInvoice",
		dataType: "HTML",
		type: "GET",
		data: {
			id: i.value
		},
		error: function(data) {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "刪除成功") {
				alert(data);
				reload();
			}
		}
	})
}

function deleteIncomeInvoice(i) {
	var result = window.confirm("是否確定要刪除此筆資料");
	if (result == false) {
		return false;
	}
	$.ajax({
		url: "/deleteIncomeInvoice",
		dataType: "HTML",
		type: "GET",
		data: {
			id: i.value
		},
		error: function(data) {
			alert("系統錯誤")
		},
		success: function(data) {
			if (data == "刪除成功") {
				alert(data);
				reload();
			}
		}
	})
}

function alterNum(e) {
	var id = e.value;
	window.open('/alterInvoiceNum?id=' + id, '修改發票號碼', 'height=300,width=300');
}


function reload() {
	$.ajax({
		url: 'searchReport',
		data: {
			"startTime": $('#startTime').val(),
			"endTime": $('#endTime').val()
		},
		dataType: 'html',
		error: function() {
			alert("系統錯誤")
		},
		success: function(data) {
			location.reload();
		},
		type: 'POST'
	});
}