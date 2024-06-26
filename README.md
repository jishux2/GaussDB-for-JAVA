### 编译运行
`javac -encoding utf-8 -cp .\opengaussjdbc.jar GaussDBDemo.java;java -cp ".;./opengaussjdbc.jar" GaussDBDemo`

### 注意事项
1. 如果标识符（模式名、表名、字段名等）中含有大写字母，那么在命名和引用时都应该用引号括起来，以区分大小写。
2. 在from子句中使用子查询时，必须给子查询的结果关系起一个别名，否则PostgreSQL会报错。
3. 由于year函数只能在B格式的数据库中使用，如果你使用的是其他格式的数据库，你可以用date_part函数来提取年份，或者用between运算符来判断年份的范围。
4. 在SQL语句中，要用`.`符号来连接模式名和关系名，表示关系属于哪个模式。例如，`"Schema_ljh_zzh".UserInfo`表示UserInfo关系属于Schema_ljh_zzh模式。
5. 在ResultSet中，不能用别名来获取列的值，比如f1.UID1和f2.UID1。这些别名只对SQL语句有用，ResultSet中只认识原来的列名，也就是UID1。如果想用别名，可以在SQL语句中给查询的列起一个新名字，或者直接用列的序号来访问。