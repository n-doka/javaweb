package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.form.Form;
import com.example.demo.entity.Task;

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
	public List<Task> allDb() {
		// Sql でDBからデータを取得する（Map<String, Object>）
		// SQLを作成(全件取得)
		String sql = "SELECT * FROM task";
		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		// Entityのリストデータをつくって、それをリターンする
		List<Task> resultDb2 = new ArrayList<Task>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Task entformdb = new Task();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		System.out.println("ALL ITEM");
		return resultDb2;
	}

	/* doing検索 */
	public List<Task> doingDb() {
		// Sql でDBからデータを取得する（Map<String, Object>）
		// SQLを作成(doing取得)
		String sql = "SELECT * FROM task WHERE done = 0";
		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		// Entityのリストデータをつくって、それをリターンする
		List<Task> resultDb2 = new ArrayList<Task>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Task entformdb = new Task();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		System.out.println("DOING ITEM");
		return resultDb2;
	}

	/* doneの検索 */
	public List<Task> completeDb() {
		// Sql でDBからデータを取得する（Map<String, Object>）
		// SQLを作成(done取得)
		String sql = "SELECT * FROM task WHERE done = 1";
		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		// Entityのリストデータをつくって、それをリターンする
		List<Task> resultDb2 = new ArrayList<Task>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Task entformdb = new Task();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		System.out.println("COMPLETE ITEM");
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

	/* 状態変更 */
	public void doneDb(boolean num, Long id) {
		// 実行
		db.update("UPDATE task SET done = ? WHERE id = ?", num, id);
		System.out.println("CHANGED" + id);

	}

	/* 編集のデータ検索 */
	public List<Task> selectDb(Long id) {
		// Sql でDBからデータを取得する（Map<String, Object>）

		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM task WHERE id = ?", id);

		// Entityのリストデータをつくって、それをリターンする
		List<Task> resultDb2 = new ArrayList<Task>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Task entformdb = new Task();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		System.out.println("EDIT ITEM");
		return resultDb2;
	}

	/* 変更 */
	public void editDb(Task taskForm) {
		db.update("UPDATE task SET title = ?, detail = ?, done = ?  WHERE id = ?", taskForm.getTitle(),
				taskForm.getDetail(), taskForm.getDone(), taskForm.getId());
		System.out.println("EDIT COMPLETE");
	}

	/* 検索 */
	public List<Task> searchDb(String search) {
		// Sql でDBからデータを取得する（Map<String, Object>）

		// SQLを実行する
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM task WHERE title = ? OR detail = ?", search, search);

		// Entityのリストデータをつくって、それをリターンする
		List<Task> resultDb2 = new ArrayList<Task>();

		// 取得したSQLのデータ(Mapのデータ)を、Entityに詰め替える
		for (Map<String, Object> result1 : resultDb1) {
			// データ 1 件分を 1 つのまとまりとした EntForm 型の「 entformdb 」を生成
			Task entformdb = new Task();
			// データを entformdb に移す
			entformdb.setId((long) result1.get("id"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setDetail((String) result1.get("detail"));
			entformdb.setDone((Boolean) result1.get("done"));
			// 移し替えたデータを持った entformdb を、 resultDB2 に入れる
			resultDb2.add(entformdb);
		}
		System.out.println("COMPLETE ITEM");
		return resultDb2;
	}

}
