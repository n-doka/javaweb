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
import com.example.demo.entity.Task;

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
		List<Task> list = taskDao.allDb();

		// modelに受け取ったSQLのデータを渡しておく
		model.addAttribute("taskList", list);
		return "index.html";
	}

	/* 処理中の表示(home) */
	@RequestMapping("/doing")
	public String doing(Model model) {
		// DAOからSQLの実行結果を受け取る
		List<Task> list = taskDao.doingDb();

		// modelに受け取ったSQLのデータを渡しておく
		model.addAttribute("taskList", list);
		return "index.html";
	}

	/* 完了の表示(home) */
	@RequestMapping("/complete")
	public String complete(Model model) {
		// DAOからSQLの実行結果を受け取る
		List<Task> list = taskDao.completeDb();

		// modelに受け取ったSQLのデータを渡しておく
		model.addAttribute("taskList", list);
		return "index.html";
	}

	/* 削除のときの処理 */
	@RequestMapping("del/{id}")
	public String delete(@PathVariable Long id) {
		/* 指定のIDのデータを削除する */
		taskDao.deleteDb(id);

		/* redirect:<URL> で、指定のURLに遷移する */
		return "redirect:/";
	}

	/* 追加のときの処理 */
	@RequestMapping("/input")
	public String form(Model model, Task form) {
		model.addAttribute("sub", "追加");
		return ("/input");
	}

	/* 追加入力後の処理 */
	@RequestMapping("/input/complete")
	public String confirm(Model model, @Validated Task form, BindingResult result) {
		if (result.hasErrors()) {
			/* 入力内容にエラーがあった場合の動作：元の画面に戻る */
			model.addAttribute("sub", "追加");
			model.addAttribute("contents", "保存できませんでした。");
			return ("/input");
		} else {
			/* EntFormをSampleDaoに渡したいので、newする */
			Task entForm = new Task();

			/* formオブジェクトに入っている、ユーザーが画面で入力した値を、entFormに渡す */
			entForm.setTitle(form.getTitle());
			entForm.setDetail(form.getDetail());

			/* SampleDaoにEntFormのオブジェクトを渡して、データベースに保存させる */
			this.taskDao.insertDb(entForm);
			return "redirect:/";
		}
	}

	/* 状態変更のときの処理 */
	@RequestMapping("/done/{num}/{id}")
	public String comp(@PathVariable boolean num, @PathVariable Long id) {
		/* 指定のIDの状態を変更する */
		taskDao.doneDb(num, id);
		/* redirect:<URL> で、指定のURLに遷移する */
		return "redirect:/";
	}

	/* 編集のときの処理 */
	@RequestMapping("/edit/{id}")
	public String form(Model model, @PathVariable Long id) {
		// DAOからSQLの実行結果を受け取る
		List<Task> pagelist = taskDao.selectDb(id);
		// modelに受け取ったSQLのデータを渡す
		model.addAttribute("sub", "編集");
		model.addAttribute("taskList", pagelist);
		return ("/edit");
	}

	/* 編集更新のときの処理 */
	@RequestMapping("/edit/fin")
	public String edit(Model model, @Validated Task form, BindingResult result) {
		if (result.hasErrors()) {
			/* 入力内容にエラーがあった場合の動作：元の画面に戻る */
			model.addAttribute("sub", "編集");
			model.addAttribute("contents", "保存できませんでした。");
			return ("/edit");
		} else {
			/* EntFormをTaskDaoに渡したいので、newする */
			Task taskForm = new Task();

			/* formオブジェクトに入っている、ユーザーが画面で入力した値を、entFormに渡す */
			taskForm.setId(form.getId());
			taskForm.setTitle(form.getTitle());
			taskForm.setDetail(form.getDetail());
			taskForm.setDone(form.getDone());

			/* 指定のIDのデータを編集する */
			taskDao.editDb(taskForm);
			/* redirect:<URL> で、指定のURLに遷移する */
			return "redirect:/";
		}
	}

	@RequestMapping("/search")
	public String search(Model model, @Validated Task form, BindingResult result) {
		// DAOからSQLの実行結果を受け取る
		List<Task> list = taskDao.searchDb(form.getName());

		// modelに受け取ったSQLのデータを渡す
		model.addAttribute("taskList", list);
		model.addAttribute("sub", "検索結果" + form.getName());
		return "index.html";
	}

}
