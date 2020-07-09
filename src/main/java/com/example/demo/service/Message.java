package com.example.demo.service;

/**対応したメッセージを定義したクラス*/
public class Message {

	/**DBに格納件数が0件の場合*/
	public static final String NODATE = "データが登録されていません";

	/**検索ワードが空の際に出すメッセージ*/
	public static final String SEARCH_EMPTY = "全件表示します。";

	/**検索にヒットした際に出すメッセージ*/
	public static final String SEARCH_HIT_COUNT = "件ヒットしました。";

	/**検索に用いたキーワードを出すメッセージ*/
	public static final String SEARCH_KEYWORD = "による検索結果";

	/**名前が検索にヒットしなかった際に出すメッセージ*/
	public static final String KEYWORD_NOT_HIT = "入力された名前はありません。";

	/**住所が検索にヒットしなかった際に出すメッセージ*/
	public static final String ADDRESS_NOT_HIT = "選択された住所はありません。";

	/**名前、住所共に検索にヒットしなかった際に出すメッセージ*/
	public static final String NOT_HIT = "入力された名前、選択された住所どちらにも合致するデータはありません";

	/**入力した値が空の場合に出すメッセージ*/
	public static final String INPUT_EMPTY = "名前、電話番号の入力は必須です。";

	/**入力された名前が20文字以上だった場合に出すメッセージ*/
	public static final String NAME_LIMIT = "名前は20文字以内で入力してください。";

//	/**入力した値が空の場合に出すメッセージ*/
//	public static final String PHONENUMBER_EMPTY = "電話番号の入力は必須です。";

	/**入力された電話番号が不正な場合に出すメッセージ*/
	public static final String PHONENUMBER_LIMIT = "電話番号は11文字、もしくは10文字で入力してください。";

	/**入力された電話番号が全角の場合に出すメッセージ*/
	public static final String SIZE_CHECK = "電話番号は半角数字のみ有効です。";

	/**アカウント登録や変更が正常に行われた際に出すメッセージ*/
	public static final String SUCCESS_REGIST = "登録成功";

	/**アカウント削除が正常に行われた際に出すメッセージ*/
	public static final String DELETE_ACCOUNT = "アカウントを削除しました。";

	/**更新成功メッセージ*/
	public static final String SUCCESS_UPDATE = "更新成功";

}
