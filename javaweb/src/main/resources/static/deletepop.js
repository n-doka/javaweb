'use strict';

function check_del(){
	if(window.confirm("本当に削除しますか？")){
		let form = document.getElementsByTagName('form')[3];
		form.submit();
	}
}

function check_input(){
	alert("追加しました！");
}

function check_edit(){
	alert("変更しました！");
}