# git使用指南

### 文件的上传与下载

![](<https://user-gold-cdn.xitu.io/2018/3/31/1627a3138484ada9?imageView2/0/w/1280/h/960/format/webp/ignore-error/1>)

### 如何提交本地工作区文件至远程仓库

至于如何安装Git可参考[猴子都能懂的Git入门](https://backlog.com/git-tutorial/cn/intro/intro2_1.html)教程1部分，以下介绍两种方法上传文件至github：

1. 第一种是不使用命令，首先登陆github网站，创建一个新的Repository，而后在该库code页面内可看到`Upload files`按钮，点击将你想要上传的文件夹拖动到方框内等待一段时间即可上传成功。这种做法方便但不适于除个人以外的团队项目

2. 第二种依赖于TortoiseGit，即之前建议安装的git汉化版软件

   - 使用前需设置好个人账号及邮箱：鼠标右键->点击TortoiseGit->设置，点击Git可以看到**名称**与**Email**标签，填入gtihub账号用户名及申请邮箱即可。也可以通过git bash命令行设置：

     鼠标右键点击git bash通过输入命令设置

     ```
     git config --global user.name "你的github用户名"
     git config --global user.email "注册邮箱"
     ```

   - 在git bash中创建本地库

     ```
     mkdir test   		//创建test文件夹
     cd test				//进入该文夹
     git init			//初始化，生成 .git 文件
     ```

     也可以手动创建该文件夹，在该文件夹下右键点击 `git在这里创建版本库`

   - 利用SSH完成git 与gitlab绑定，便于提交代码，可[参考](https://blog.csdn.net/qq_35246620/article/details/69061355)

   - 常用命令

     ```git
     mkdir：         XX (创建一个空目录 XX指目录名)
     pwd：          显示当前目录的路径。
     git init          把当前的目录变成可以管理的git仓库，生成隐藏.git文件。
     git add XX       把xx文件添加到暂存区去。
     git commit –m “XX”  提交文件 –m 后面的是注释。
     git status        查看仓库状态
     git diff  XX      查看XX文件修改了那些内容
     git log          查看历史记录
     git reset  –hard HEAD^ 或者 git reset  –hard HEAD~ 回退到上一个版本
                         (如果想回退到100个版本，使用git reset –hard HEAD\~100 )
     
     cat XX         查看XX文件内容
     git reflog       查看历史记录的版本号id
     git checkout — XX  把XX文件在工作区的修改全部撤销。
     git rm XX          删除XX文件
     
     git remote add origin https://github.com/BlackTomm/Learning-center 关联一个远程库
     git push –u origin master  (第一次要用-u 以后不需要)把当前master分支推送到远程库
     git clone https://github.com/BlackTomm/Learning-center  从远程库中克隆
     
     git checkout –b dev  创建dev分支 并切换到dev分支上
     git branch  查看当前所有的分支
     git checkout master 切换回master分支
     git merge dev    在当前的分支上合并dev分支
     git branch –d dev 删除dev分支
     git branch name  创建分支
     
     git stash 把当前的工作隐藏起来 等以后恢复现场后继续工作
     git stash list 查看所有被隐藏的文件列表
     git stash apply 恢复被隐藏的文件，但是内容不删除
     git stash drop 删除文件
     git stash pop 恢复文件的同时 也删除文件
     
     git remote 查看远程库的信息
     git remote –v 查看远程库的详细信息
     git push origin master  Git会把master分支推送到远程库对应的远程分支上
     ```

     

   

   