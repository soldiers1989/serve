###1、准备工具

Module生成工具 [MVPArms-Module-Template](https://github.com/JessYanCoding/MVPArms-Module-Template)
模板代码生成工具 [MVPArmsTemplate](https://github.com/JessYanCoding/MVPArmsTemplate)

###2、资源文件命名规范
`layout`文件的命名方式：
* `Activity` 的 `layout` 以 `module_activity` 开头
* `Fragment` 的 `layout` 以 `module_fragment` 开头
* `Dialog` 的 `layout` 以 `dialog_module` 开头
* `include` 的 `layout` 以 `layout_module` 开头
* `ListView`、`RecyclerView`、`GridView` 的 `layout 以 item_module`开头

`drawable` 资源名称以小写单词+下划线的方式命名
	模块名 _ 业务功能描述 _ 控件描述 _ 控件状态限定词
如：module_login_btn_pressed,module_tabs_icon_home_normal

状态栏和导航栏工具地址  https://github.com/gyf-dev/ImmersionBar