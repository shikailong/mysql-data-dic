# 数据库字典生成工具
## 配置
在DataQueryService.java中配置mysql数据库的url，用户名，密码

## 使用过程

1. 直接修改DataQueryService.java文件中的配置信息，运行App.java的main方法即可
2. 打成jar包之后，直接使用java -jar 命令运行

## 生成结果
会在项目的根目录下生成一个名为《数据字典》的excel

## 缺陷 
1. 只能支持mysql，后期会支持更多的数据库。
2. 配置点太少，不够完善，增加更多人性化的配置。