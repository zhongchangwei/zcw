//关闭窗口
    function onClose(){
	mini.confirm("确定关闭窗口？", "确定",function(action){
		if (action == "ok") {
			if(window.parent!=null){
				window.CloseWindow(action);
			}else{
				CloseWindow("close");
			}
			
		}
		});
    }
	function CloseWindow(action) {
		if (window.CloseOwnerWindow) 
		return window.CloseOwnerWindow(action);
		else window.close();
		}