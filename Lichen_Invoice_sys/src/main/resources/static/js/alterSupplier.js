/**
 * 
 */

function saveAlter(){
	$.ajax({
		url: "/saveAlterSupplier",
		data:{
			"id" : $("#id").val(),
			"supid" : $("#supid").val(),
			"supname" : $("#supname").val(),
			"supphone" : $("#supphone").val(),
			"supaddress" : $("#supaddress").val(),
			"suptitle" : $("#suptitle").val(),
			"suptaxnumber" : $("#suptaxnumber").val(),
		},
		dataType: 'html',
		error: function() {
			alert("資訊未輸入完全");
		},
		success: function(data) {
			alert(data);
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
					location.reload(true);
				},
				type: 'GET'
			});
		},
		type: 'GET'
	})
}

function deleteSupplier(){
	var a=window.confirm("刪除廠商後，相對應的進項發票紀錄將全數刪除，請確認是否執行");
	if(a==false){
		return false;
	}
	var result=window.confirm("是否確定要刪除廠商?");
	if(result==false){
		return false;
	}
	$.ajax({
		url: "/deleteSupplier",
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
				url: 'selectAllSup',
				data: {
				},
				dataType: 'html',
				error: function() {
					alert("系統錯誤")
				},
				success: function() {
					window.location.href="/showSupplier";
				},
				type: 'GET'
			});
		}
		},
		type: 'GET'
	})
}