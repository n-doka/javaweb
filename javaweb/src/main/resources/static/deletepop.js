'use strict';

//消去時のポップアップ
function check_del() {
	if (window.confirm("本当に削除しますか？")) {
		let form = document.getElementsByTagName('form')[3];
		form.submit();
	}
}