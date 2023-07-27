## 一、安装 Node.js

```
node-v10.14.2-x64.msi
# 查看版本
node -v
npm -v
# 配置 npm 淘宝镜像
npm config set registry http://registry.npm.taobao.org/
```

## 二、下载 Vue 脚手架

```
# 全局下载
npm install -g @vue/cli
# 查看版本
vue --version
```

## 三、创建 Vue 项目

```
vue create appName
```

## 四、启动项目

```shell
# 进行项目根目录, 启动项目
npm run serve
```

## 五、配置文件：vue.config.js

```js
module.exports= {

	lintOnSave: false, // 禁用 eslint
	
	devServer: {
		open: true,
		// 配置代理服务器
		proxy:{
			"/api": {
				target: "http://39.98.123.211",
				changeOrigin: true,
				pathRewrite: {
					"^/api" : ""
				}
			}
		}
	},
}

/* 注意： 配置后需要重新启动 */
```

