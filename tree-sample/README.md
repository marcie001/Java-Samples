# CSV ファイルから木構造のオブジェクトをつくるサンプル

[住所.jp](http://jusyo.jp/csv/new.php) で配布している CSV から木構造のオブジェクトを作るサンプルです。

## 起動方法

`-Dexec.args` に CSV ファイルのパスを指定します。
読み込んだファイルと同じディレクトリに `読み込んだファイル名 + ".json"` という名前でファイルを作成するので注意してください。
作成したファイルの中身は生成した木構造オブジェクトを JSON にシリアライズしたものです。

```
$ ./mvnw compile
$ ./mvnw exec:java -Dexec.args=/path/to/zenkoku.csv
```

