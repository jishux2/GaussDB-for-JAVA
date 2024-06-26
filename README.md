### 编译运行
`javac -encoding utf-8 -cp .\opengaussjdbc.jar GaussDBDemo.java;java -cp ".;./opengaussjdbc.jar" GaussDBDemo`

### 注意事项
1. 如果标识符（模式名、表名、字段名等）中含有大写字母，那么在命名和引用时都应该用引号括起来，以区分大小写。
2. 在from子句中使用子查询时，必须给子查询的结果关系起一个别名，否则PostgreSQL会报错。
3. 由于year函数只能在B格式的数据库中使用，如果你使用的是其他格式的数据库，你可以用date_part函数来提取年份，或者用between运算符来判断年份的范围。