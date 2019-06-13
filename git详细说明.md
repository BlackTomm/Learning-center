# 从只会git add .的菜鸟到掌握git基本功能

# 前言

##来自掘金文章

最近想把代码传到GitHub上,结果我发现的demo的npm全是本地安装，上穿到GitHub要死要死，几百M，然后我就搜了下怎么不上传`node_modules`弄了半天也没成功，于是准备静下心学一下git，也当一个笔记日后好翻阅.
研究前我的只会5个命令

```
$ git init 
$ git add .
$ git commit -m "提交的xxxxx"
$ git pull
$ git push
复制代码
```

# Git是什么

Git是一个开源的分布式版本控制系统，用于敏捷高效地处理任何或小或大的项目。 Git 是 Linus Torvalds 为了帮助管理 Linux 内核开发而开发的一个开放源码的版本控制软件。 Git 与常用的版本控制工具 CVS, Subversion 等不同，它采用了分布式版本库的方式，不必服务器端软件支持。

# Git的工作流程



![img](https://user-gold-cdn.xitu.io/2018/3/31/1627a0d523313449?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



1. 克隆 Git 资源作为工作目录。
2. 在克隆的资源上添加或修改文件。
3. 如果其他人修改了，你可以更新资源。
4. 在提交前查看修改。
5. 提交修改。
6. 在修改完成后，如果发现错误，可以撤回提交并再次修改并提交。

# 基本概念



![img](https://user-gold-cdn.xitu.io/2018/3/31/1627a3138484ada9?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



## 本地仓库(版本库)

版本库：工作区有一个隐藏目录.git，这个不算工作区，而是Git的版本库。

### 本地仓库(版本库)是什么

什么是版本库呢？版本库又名仓库，英文名repository，你可以简单理解成一个目录，这个目录里面的所有文件都可以被Git管理起来，每个文件的修改、删除，Git都能跟踪，以便任何时刻都可以追踪历史，或者在将来某个时刻可以“还原”。

## 暂存区

暂存区：英文叫stage, 或index。一般存放在 ".git目录下" 下的index文件（.git/index）中，所以我们把暂存区有时也叫作索引（index）。

## 工作区

工作区：就是你在电脑里能看到的目录。

# Git的基本使用

我就不详细介绍了，估计大家都会，就是上面辣个图,图上几个命令

```
$ git init，              //初始化本地仓库 .git
$ git status -sb，        //显示当前所有文件的状态
$ git diff                //查看更改，查看difference，显示的格式正是Unix通用的diff格式,
$ git add 文件路径        //用来将变动加到暂存区
$ git commit -m "信息"    //用来正式提交变动，提交至 .git 仓库如果有新的变动，我们只需要依次执行 git add xxx 和 git commit -m 'xxx' 两个命令即可
$ git log                 //查看变更历史
复制代码
```

## 版本回退

当我提交了几个commit，假设我们现在有3个版本(1,2,3)，现在是版本3，发现刚刚的提交错误了，想撤回回到版本2

```
$ git reset --hard               //重置暂存区与工作区，与上一次commit保持一致
复制代码
```

然后你由发现刚刚的提交是正确的,又想回到版本3，再输入下面这个命令，相当于你那个回退没有做

```
$ git reset --hard [commitid]     //重置当前分支的HEAD为指定commit，同时重置暂存区和工作区，与指定commit一致
//commitid 使用git log --stat查看
复制代码
```

------

平行世界B
在平时世界B的你，刚刚把版本回退到了版本2，于是睡觉去了，第二天，发现版本3才是对的，可是使用`git log`已经查看不到`commit`信息了，怎么办？

```
$ git reflog                      //用来记录你的每一次命令,显示当前分支的最近几次提交
复制代码
```

场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令
`$ git checkout -- file`

场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令
`$ git reset HEAD file`
，就回到了场景1，第二步按场景1操作。

场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，`git reset --hard`,不过前提是没有推送到远程仓库。

## 删除文件

```
$ git rm [file1] [file2] ...      //删除工作区文件，并且将这次删除放入暂存区
复制代码
```

另一种情况是删错了，因为版本库里还有呢，所以可以很轻松地把误删的文件恢复到最新版本：

```
$ git checkout -- test.txt
复制代码
```

## 分支(branch)

- 分支在实际中有什么用呢？假设你准备开发一个新功能，但是需要两周才能完成，第一周你写了50%的代码，如果立刻提交，由于代码还没写完，不完整的代码库会导致别人不能干活了。如果等代码全部写完再一次提交，又存在丢失每天进度的巨大风险。
- 现在有了分支，就不用怕了。你创建了一个属于你自己的分支，别人看不到，还继续在原来的分支上正常工作，而你在自己的分支上干活，想提交就提交，直到开发完毕后，再一次性合并到原来的分支上，这样，既安全，又不影响别人工作。
- 你已经知道，每次提交，Git都把它们串成一条时间线，这条时间线就是一个分支。截止到目前，只有一条时间线，在Git里，这个分支叫主分支，即master分支。HEAD严格来说不是指向提交，而是指向master，master才是指向提交的，所以，HEAD指向的就是当前分支。

1. 当我们创建新的分支，例如dev时，Git新建了一个指针叫dev，指向master相同的提交，再把HEAD指向dev，就表示当前分支在dev上：

   ![img](https://user-gold-cdn.xitu.io/2018/3/31/1627b3ca39acd590?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

2. 你看，Git创建一个分支很快，因为除了增加一个dev指针，改改HEAD的指向，工作区的文件都没有任何变化！

不过，从现在开始，对工作区的修改和提交就是针对dev分支了，比如新提交一次后，dev指针往前移动一步，而master指针不变：

![img](https://user-gold-cdn.xitu.io/2018/3/31/1627b3c521906c65?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

\3. 假如我们在dev上的工作完成了，就可以把dev合并到master上。Git怎么合并呢？最简单的方法，就是直接把master指向dev的当前提交，就完成了合并：

![img](https://user-gold-cdn.xitu.io/2018/3/31/1627b3ccb550e4f5?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

\4. 合并完分支后，甚至可以删除dev分支。删除dev分支就是把dev指针给删掉，删掉后，我们就剩下了一条master分支：

![img](https://user-gold-cdn.xitu.io/2018/3/31/1627b3cfd22b4e78?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



### 如何使用分支

```
$ git checkout -b [branch]                //新建一个分支，并切换到该分支
$ git branch                              //命令会列出所有分支，当前分支前面会标一个*号。
$ git add . 
$ git commit -m "提交分支branch"
$ git checkout master                     //切换回master分支
$ git merge [branch]                      //把branch分支合并到master分支
$ git branch -d branch                     //合并完成后删除branch分支

复制代码
查看分支：git branch

创建分支：git branch <name>

切换分支：git checkout <name>

创建+切换分支：git checkout -b <name>

合并某分支到当前分支：git merge <name>

删除分支：git branch -d <name>
复制代码
```

### 分支冲突

比如现在我们的文件是这样的

```
fuck 'webpack'                      //master分支
复制代码
```

我们创建并且切换到parcel分支

```
$ git checkout -b parcel     
复制代码
```

修改文本内容

fuck 'webpack ---> parcel no.1
提交到暂存区

```
$ git add .
$ git commit "嘻嘻"                      //parcel分支
复制代码
```

切回master分支

```
$ git checkout master
复制代码
```

修改文本内容

fuck 'webpack' --> fuck fuck 'webpack'
提交到暂存区

```
$ git add .
$ git commit "哈哈"                      //maser分支
复制代码
```

这样我们的两个分支内容不一样，有了冲突，我们提交试一下



![img](https://user-gold-cdn.xitu.io/2018/3/31/1627b4f76588417f?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



```
$ git merge parcel                      //把parcel分支合并到当前master分支
复制代码
```

然后就冲突了

```
$ git status                        //可以告诉我们冲突的文件：
# On branch master
# Your branch is ahead of 'origin/master' by 2 commits.
#
# Unmerged paths:
#   (use "git add/rm <file>..." as appropriate to mark resolution)
#
#       both modified:     fuck.txt
#
复制代码
```

我们得手动修改当前master分支的内容与parcel分支内容相同

fuck fuck 'webpack' --> parcel no.1
再次提交

```
$ git add .
$ git commit "扑扑"                      //maser分支
复制代码
```

最后，删除parcel分支

```
 git branch -d parcel
复制代码
```

### 分支管理策略

通常，合并分支时，如果可能，Git会用Fast forward模式(默认模式)，但这种模式下，删除分支后，会丢掉分支信息。

如果要强制禁用Fast forward模式，Git就会在合并分支时生成一个新的commit，这样，从分支历史上就可以看出分支信息。

列如

```
$ git checkout -b dev                                 //首先，仍然创建并切换dev分支：
$ git add readme.txt                                  //修改readme.txt文件，并提交一个新的commit
$ git checkout master                                 //现在，我们切换回master分支
$ git merge --no-ff -m "merge with no-ff" dev         //准备合并dev分支，请注意--no-ff参数，表示禁用Fast forward
$ git log --graph --pretty=oneline --abbrev-commit    //合并后，我们用git log看看分支历史：
//合并分支时，加上--no-ff参数就可以用普通模式合并，合并后的历史有分支，能看出来曾经做过合并，而fast forward合并就看不出来曾经做过合并。
复制代码
```

### 团队分支管理

在实际开发中，我们应该按照几个基本原则进行分支管理：

首先，master分支应该是非常稳定的，也就是仅用来发布新版本，平时不能在上面干活；

那在哪干活呢？干活都在dev分支上，也就是说，dev分支是不稳定的，到某个时候，比如1.0版本发布时，再把dev分支合并到master上，在master分支发布1.0版本；

你和你的小伙伴们每个人都在dev分支上干活，每个人都有自己的分支，时不时地往dev分支上合并就可以了。

所以，团队合作的分支看起来就像这样：

![img](https://user-gold-cdn.xitu.io/2018/3/31/1627b60854e84c4a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



团队分支应该真是这样

```
|- master                       //正式版
  |- dev                        //测试版
    |- michael                  //队员michael-adc
    |- bob                      //队员bob-肉
    |- bibi                     //队员bibi-大腿
复制代码
```

### Bug分支

软件开发中，bug就像家常便饭一样。有了bug就需要修复，在Git中，由于分支是如此的强大，所以，每个bug都可以通过一个新的临时分支来修复，修复后，合并分支，然后将临时分支删除。

当你接到一个修复一个代号101的bug的任务时，很自然地，你想创建一个分支issue-101来修复它，但是，等等，当前正在dev上进行的工作还没有提交

```
------------                                              //我们在dev分支上，发现master分支上有代号101号bug
$ git stash                                               //冷冻现在在dev分支上的工作状态 冻结吧！  
$ git checkout master                                     //这个bug发生在master主分支上,我们切回master分支
$ git checkout -b issue-101                               //创建代号101的修复bug分支
修改你的bug
$ git add readme.txt                                      //提交到暂存区
$ git commit -m "fix bug 101"                             //注意填写信息，以免日后查证
$ git checkout master                                     //切换回master分支
$ git merge --no-ff -m "merged bug fix 101" issue-101     //合并分支，注意不使用fast forward模式
$ git branch -d issue-101                                 //删除issue-101分支
$ git checkout dev                                        //bug 改完了，是时候回到dev继续写bug了
$ git stash list                                          //查看刚刚的冻结现场
$ git stash pop                                           //git stash pop，恢复的同时把stash内容也删了：
//一是用git stash apply恢复，但是恢复后，stash内容并不删除，你需要用git stash drop来删除
复制代码
```

### 开发一个新测试功能

开发一个新feature，最好新建一个分支；

如果要丢弃一个没有被合并过的分支，可以通过`git branch -D <name>`强行删除。

### 多人协作

当你从远程仓库克隆时，实际上Git自动把本地的master分支和远程的master分支对应起来了，并且，远程仓库的默认名称是origin。 要查看远程库的信息，用`git remote -v`

#### 推送分支

推送分支，就是把该分支上的所有本地提交推送到远程库。推送时，要指定本地分支，这样，Git就会把该分支推送到远程库对应的远程分支上:

```
$ git push origin master
复制代码
```

如果要推送其他分支，比如dev，就改成：

```
$ git push origin dev
复制代码
```

但是，并不是一定要把本地分支往远程推送，那么，哪些分支需要推送，哪些不需要呢？

- master分支是主分支，因此要时刻与远程同步；
- dev分支是开发分支，团队所有成员都需要在上面工作，所以也需要与远程同步；
- bug分支只用于在本地修复bug，就没必要推到远程了，除非老板要看看你每周到底修复了几个bug；
- feature分支是否推到远程，取决于你是否和你的小伙伴合作在上面开发。

```
多人协作时，大家都会往master和dev分支上推送各自的修改。  
当你的小伙伴从远程库clone时，默认情况下，你的小伙伴只能看到本地的master分支。  
现在，你的小伙伴要在dev分支上开发，就必须创建远程origin的dev分支到本地，于是他用这个命令创建本地dev分支：
$ git checkout -b dev origin/dev
你的小伙伴已经向origin/dev分支推送了他的提交，而碰巧你也对同样的文件作了修改，并试图推送
推送失败，因为你的小伙伴的最新提交和你试图推送的提交有冲突，解决办法也很简单，Git已经提示我们，先用git pull把最新的提交从origin/dev抓下来，然后，在本地合并，解决冲突，再推送：
git pull也失败了，原因是没有指定本地dev分支与远程origin/dev分支的链接，根据提示，设置dev和origin/dev的链接：
$ git branch --set-upstream dev origin/dev
再pull
这回git pull成功，但是合并有冲突，需要手动解决，解决的方法和分支管理中的解决冲突完全一样。解决后，提交，再push：
复制代码
```

因此，多人协作的工作模式通常是这样：

首先，可以试图用`git push origin branch-name`推送自己的修改；

如果推送失败，则因为远程分支比你的本地更新，需要先用`git pull`试图合并；

如果合并有冲突，则解决冲突，并在本地提交；

没有冲突或者解决掉冲突后，再用`git push origin branch-name`推送就能成功！

如果`git pull`提示“no tracking information”，则说明本地分支和远程分支的链接关系没有创建，用命令
`git branch --set-upstream branch-name origin/branch-name`

这就是多人协作的工作模式，一旦熟悉了，就非常简单。

------

查看远程库信息，使用`git remote -v`

本地新建的分支如果不推送到远程，对其他人就是不可见的；

从本地推送分支，使用`git push origin branch-name`，如果推送失败，先用git pull抓取远程的新提交；

在本地创建和远程分支对应的分支，使用`git checkout -b branch-name origin/branch-name`，本地和远程分支的名称最好一致；

建立本地分支和远程分支的关联，使用`git branch --set-upstream branch-name origin/branch-name`；

从远程抓取分支，使用`git pull`，如果有冲突，要先处理冲突。

## 标签管理

### 创建标签

- 命令`git tag <name>`用于新建一个标签，默认为HEAD，也可以指定一个commit id；
- `git tag -a <tagname> -m "blablabla..."`可以指定标签信息；
- `git tag -s <tagname> -m "blablabla..."`可以用PGP签名标签；
- 命令`git tag`可以查看所有标签。
- 还可以创建带有说明的标签，用-a指定标签名，-m指定说明文字：

```
$ git tag -a v0.1 -m "version 0.1 released" 3628164
复制代码
```

### 操作标签

如果标签打错了，也可以删除：

```
$ git tag -d v0.1
复制代码
```

如果要推送某个标签到远程，使用命令

```
$ git push origin <tagname>
复制代码
```

或者，一次性推送全部尚未推送到远程的本地标签：

```
$ git push origin --tags
复制代码
```

如果标签已经推送到远程，要删除远程标签就麻烦一点，先从本地删除：

```
$ git tag -d v0.9
复制代码
```

然后，从远程删除。删除命令也是push，但是格式如下：

```
git push origin :refs/tags/<tagname>
复制代码
```

### 忽略特殊文件

在Git工作区的根目录下创建一个特殊的.gitignore文件，然后把要忽略的文件名填进去，Git就会自动忽略这些文件。 不需要从头写.gitignore文件，GitHub已经为我们准备了各种配置文件，只需要组合一下就可以使用了。所有配置文件可以直接在线浏览：[.gitignore](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fgithub%2Fgitignore)
