'use strict';

//消去時のポップアップ
function check_del(n) {
	if (window.confirm("本当に削除しますか？")) {
		//指定したidの要素をcsに入れる	
		let form = document.querySelector(`[id=${CSS.escape(n)}]`);
		form.submit();
	}
}