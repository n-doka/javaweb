package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.form.Form;

@Repository
public class TaskDao {

	private final JdbcTemplate db;

	/*
	 * コンストラクタ = クラスからオブジェクト作るときに必ず実行される
	 * コンストラクタで書いたものは、確実に利用できる
	 * コンストラクタの中でオブジェクトを作って、変数に保持する
	 */
	@Autowired
	public TaskDao(
			JdbcTemplate db) {
		this.db = db;
	}

	/* すべての表示 */
	public List<Form> searchDb() {
		// Sql でDBからデータを取得する（Map<String, Object>）
		// SQLを作成(全件取得)
		String sql = "SELECT * FROM task";
		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		// Entityのリストデータをつくって、それをリターンする
		List<Form> resultDb2 = new ArrayList<Form>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Form entformdb = new Form();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		return resultDb2;
	}

	/* doing検索 */
	public List<Form> doingDb() {
		// Sql でDBからデータを取得する（Map<String, Object>）
		// SQLを作成(doing取得)
		String sql = "SELECT * FROM task WHERE done = 0";
		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		// Entityのリストデータをつくって、それをリターンする
		List<Form> resultDb2 = new ArrayList<Form>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Form entformdb = new Form();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		return resultDb2;
	}

	/* doneの検索 */
	public List<Form> doneDb() {
		// Sql でDBからデータを取得する（Map<String, Object>）
		// SQLを作成(done取得)
		String sql = "SELECT * FROM task WHERE done = 1";
		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		// Entityのリストデータをつくって、それをリターンする
		List<Form> resultDb2 = new ArrayList<Form>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Form entformdb = new Form();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		return resultDb2;
	}

	/* 追加 */
	public void insertDb(Form taskForm) {
		this.db.update("INSERT INTO task(title, detail) VALUES(?, ?)", taskForm.getTitle(), taskForm.getDetail());
		System.out.println("add item");
	}

	/* 削除 */
	public void deleteDb(Long id) {
		// 削除を実行
		db.update("DELETE FROM task WHERE id = ?", id);

		System.out.println("DELETED" + id);
	}

}
