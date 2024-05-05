###  多多记账
简约的记账软件，适用多种人群，学生党，上班族，企业家，提供不同的记账方式。<br/>

## 安全
现代世界，互联网发展迅速，数据之间的传输快到飞起，虽然每个人都有保护隐私的想法，但是其权力很多时候都不在自己手中，所以我一直以来都很在乎
用户的隐私安全方面，有关于性能在目前的硬件设施中并不像以前那样，反而很多时候都是性能溢出，既然这样，何必还要服务器泄露隐私，将部分性能用
来在本地保存数据并且实时读取，所以再开发过程中，我一直以本地化保存数据以及数据安全为主，可能后续会添加云端存储的API但是是否使用完全取决
于用户，我认为，自己做出的选择就是最好的选择。

### 优势
无广告 我不会添加任何广告要素，太破坏使用体验 <br/>
免费会员 目前还在开发中 没有任何会员和充值通道，所有功能可以免费使用 <br/>
多主题 待开发计划中 <br/>
开源 软件的所有代码均会开源，这样做保证了软件本身的安全性，同时极大的保证了软件的寿命  <br/>
数据本地化 虽然云端更加方便，但同时隐私数据更容易泄露，所以我决定将软件的数据全部存储在本地，并且可以直接导出保存。<br/>
数据分析和展示 用户可以自己决定要打开的权限，来获得更加详细的记账记录。 <br/>


### 开发进度
1.0.0 基础记账 实现了添加数据自动计算和保存记账记录 <br/>
1.0.1 详细记账 添加时显示时间和分类 <br/>
1.0.2 美观的加载画面 Titanic for Android  <br/>
1.0.3 本地保存数据 <br/>
1.0.4 重新调整了布局，添加了新页面：主页 <br/>
1.0.5 使用sqlite在本地存储数据 <br/>


### 鸣谢
https://github.com/RomainPiel/Titanic

## 修复
1.0.0 打包APK后，实际测试中，无法正常使用ScrollView滚动条，但在模拟测试中却可以。 已解决：但啥都没干，再次运行的时候莫名其妙好了（流汗黄豆）<br/>
1.0.1 修复了代码复用性高，调整了代码结构<br/>
1.0.2 修复了读取数据后无法实时添加数据<br/>
1.0.3 修复了数据排序的问题 <br/>

### Excel设计
支出表: 单笔支出需要记录的数据有 金额 分类 日期 交易方式 账户 商家/收款方 地点 附件/凭证 备注 <br/>
收入表: 单笔收入需要记录的数据有 金额 分类 日期 交易方式 账户 地点 凭证 备注 <br/>
余额表: 通过计算支出和收入后，得出当前的余额 <br/>
资产负债表: 通过计算资产和负债后 确定净资产 <br/>
现金流量表: 记录个人或企业的现金流入和流出情况，以评估资金状况 <br/>
成本表: 记录企业的生产成本和销售成本，用于成本控制和盈利分析 <br/>
财务报表: 汇总以上各表格的信息，展示个人或企业的财务状况和经营业绩 <br/>







### 赞助
开发软件本身很困难，欢迎赞助<br/>

