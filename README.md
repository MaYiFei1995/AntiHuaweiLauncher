# AntiHuaweiLauncher

通过监听Home键来实现拉起第三方启动器的启动类

---
更新：
* 191203
  * 已知问题
    * 不支持Android10
    * Emui 9.1.0.336版本的home键监听处理时使用PendingIntent来startActivity时和使用普通intent.startActivity一样仍有5s的延迟
    * 但在Notification使用setContentIntent(pendingIntent)和应用内部使用PendingIntent拉起应用都没有延迟
    * Emui 9.1.0.333版本没有上述问题
* 191114
  * 提高服务稳定性
* 初始版本：
  * 实现基本功能

---

Todo:
* 增加选择配置启动器或手动输入包名的功能
* 保活
