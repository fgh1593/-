/**
 * 
 */

	function insertItem() {
		$.ajax({
			url : 'insertItem',
			data : {
				"name" : $("#newItem").val()
			},
			dataType : 'html',
			error : function() {
				alert("系統錯誤")
			},
			success : function(data) {
				alert(data);
				if (data = "新增成功") {
					$.ajax({
						url : 'toInvoiceItem',
						data : {},
						dataType : 'html',
						error : function() {
							alert("系統錯誤")
						},
						success : function() {
							location.reload(true);
						},
						type : 'GET'
					});
				}
			},
			type : 'GET'
		});
	}
	
	
		function deleteItem(e) {
			var a=window.confirm("刪除品項後，相對應品項的發票紀錄將全數刪除，請確認是否執行");
			if(a==false){
				return false;
			}
			var b=window.confirm("再次確認是否刪除品項?")
			if(b==false){
				return false;
			}
		$.ajax({
			url : 'deleteItem',
			data : {
				"id" : e.id
			},
			dataType : 'html',
			error : function() {
				alert("系統錯誤")
			},
			success : function(data) {
				alert(data);
				if (data = "刪除成功") {
					$.ajax({
						url : 'toInvoiceItem',
						data : {},
						dataType : 'html',
						error : function() {
							alert("系統錯誤")
						},
						success : function() {
							location.reload(true);
						},
						type : 'GET'
					});
				}
			},
			type : 'GET'
		});
	}