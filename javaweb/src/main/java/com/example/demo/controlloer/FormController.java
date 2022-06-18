package com.example.demo.controlloer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.TaskDao;
import com.example.demo.form.Form;

@Controller
public class FormController {

	/*
	 * リポジトリクラス(DBとの通信担当)を
	 * コントローラ側でいつでも使えるように
	 * メンバ変数の一つとして保持しておく
	 */
	private final TaskDao taskDao;

	/*
	 * コンストラクタで、SampleDaoのオブジェクトを生成して、
	 * メンバ変数の中に保存する（この方法をコンストラクタインジェクションと呼ぶ）
	 */
	@Autowired // これを書いておくと、引数の中のクラスを自動的にnewしてオブジェクトを渡してくれる
	public FormController(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	/* すべての表示(home) */
	@RequestMapping("/")
	public String view(Model model) {
		// DAOからSQLの実行結果を受け取る
		List<Form> list = taskDao.searchDb();

		// modelに受け取ったSQLのデータを渡しておく
		model.addAttribute("dbList", list);
		return "index.html";
	}

	@RequestMapping("/doing")
	public String doing(Model model) {
		// DAOからSQLの実行結果を受け取る
		List<Form> list = taskDao.doingDb();

		// modelに受け取ったSQLのデータを渡しておく
		model.addAttribute("dbList", list);
		return "index.html";
	}

	@RequestMapping("/done")
	public String done(Model model) {
		// DAOからSQLの実行結果を受け取る
		List<Form> list = taskDao.doneDb();

		// modelに受け取ったSQLのデータを渡しておく
		model.addAttribute("dbList", list);
		return "index.html";
	}

	/* 削除のときの処理 */
	@RequestMapping("del/{id}")
	public String destoroy(
			@PathVariable Long id) {
		/* 指定のIDのデータを削除する */
		taskDao.deleteDb(id);

		/* redirect:<URL> で、指定のURLに遷移する */
		return "redirect:/";
	}

	/* 追加のときの処理 */
	@RequestMapping("/input")
	public String form(Model model, Form form) {
		return ("/input");
	}

	/* 追加入力後の処理 */
	@RequestMapping("/input/complete")
	public String confirm(Model model, @Validated Form form, BindingResult result) {
		if (result.hasErrors()) {
			/* 入力内容にエラーがあった場合の動作：元の画面に戻る */
			return ("/input");
		}else{
		/* EntFormをSampleDaoに渡したいので、newする */
		Form entForm = new Form();

		/* formオブジェクトに入っている、ユーザーが画面で入力した値を、entFormに渡す */
		entForm.setTitle(form.getTitle());
		entForm.setDetail(form.getDetail());

		/* SampleDaoにEntFormのオブジェクトを渡して、データベースに保存させる */
		this.taskDao.insertDb(entForm);
			return "redirect:/";
		}
	}

}
