/**
 * 
 */

function deleteInvoice(i){
		var result=window.confirm("是否確定要刪除此筆資料");
		if(result==false){
			return false;
		}
		$.ajax({
			url :"/deleteInvoice",
			dataType:"HTML",
			type:"GET",
			data:{
				id:i.value
			},
			error: function(data){
				alert("系統錯誤")
			},
			success:function(data){
				if(data=="刪除成功"){
					alert(data);
					$.ajax({
						url: 'searchReport',
						data: {
							"startTime" :  $('#startTime').val(),
							"endTime" : $('#endTime').val()
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
			}
		})
}

function deleteIncomeInvoice(i){
		var result=window.confirm("是否確定要刪除此筆資料");
		if(result==false){
			return false;
		}
		$.ajax({
			url :"/deleteIncomeInvoice",
			dataType:"HTML",
			type:"GET",
			data:{
				id:i.value
			},
			error: function(data){
				alert("系統錯誤")
			},
			success:function(data){
				if(data=="刪除成功"){
					alert(data);
					$.ajax({
						url: 'searchReport',
						data: {
							"startTime" :  $('#startTime').val(),
							"endTime" : $('#endTime').val()
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
			}
		})
}