 [![API](https://img.shields.io/badge/API-14%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=14) ![license](https://img.shields.io/badge/license-Apache-000000.svg) [![Build Status](https://www.travis-ci.org/kong-jing/MultiLevelMenu.svg?branch=master)](https://www.travis-ci.org/kong-jing/MultiLevelMenu)

# MultiLevelMenu
Multi-Level Menu 多级菜单

![](https://github.com/kong-jing/MultiLevelMenu/blob/master/multilevelmenu.gif)

最开始学习到的[Android多级树形选择列表案例 - 手把手教你快速实现](http://www.jianshu.com/p/b76572fb4e60)

了解了这个[更快实现Android多级树形选择列表](http://www.jianshu.com/p/090904d2b689)

使用[DialogPlus](http://blog.csdn.net/ss1168805219/article/details/54954427#属性方法)

使用

``mDialogAdapter =
            new DialogAdapter(listView, DialogActivity.this, mTreeDatas, 0, R.mipmap.tree_ex,
                R.mipmap.tree_ec);``
                
*初始化该树形多级菜单 第一个参数  ListView, 第二个参数  上下文,第三个参数  数据集, 第四个参数  默认展开层级数 0为不展开,第五个参数  展开的图标,第六个参数  闭合的图标*

``mTreeDatas.add(new Node("id", "parentId",new bean()));``

*添加节点数据*

``listView.setAdapter(mDialogAdapter);//绑定适配器``

*绑定数据*

License
-------

    Copyright 2017 Kong Jing

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
