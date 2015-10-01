DrawGraph
============

##Overview
用Java写的 函数图像显示器 ： <br />
支持显示 一二三次函数，幂函数，对数函数，椭圆方程，任意三角形 的函数图像。

##How Does It Work
所有函数继承于一个顶级父类 BasicFn ，并由各个子类判断画布上的每个像素点是否在函数图像上，每个子类生成一个300*300的boolean数组代表画布上各个像素点的位置，把这个数组添加到画布的像素点boolean数组中，实现图像的显示。

##How to Use It
从下拉列表中选择一个函数模版，然后在下面输入需要的参数，点击 Draw 按钮显示图像，绘制下一幅图像时前一副图像不会消失；要清理画布，请点击 Clear 按钮。<br />
目前只支持绘制 -10 <= x <= 10 ,-10 <= y <= 10 区间的函数图像，暂不支持图像移动。

##Known BUG
由于判断的问题，绘制斜率接近0的部分时，图线会变粗，绘制斜率较大的部分时，图线会变细或者变成点（椭圆与之相反，因为椭圆的判断函数 ifOnFn() 不一样）。<br />
~~反正比你们在 国庆作业.pdf 中给的示例更好就行~~<br />
发现新的BUG？您可以提交[issue](https://github.com/haruue/DrawGraph_Java/issues)，我们感激不尽！

##Troubleshooting
您可以提交 [issue](https://github.com/haruue/DrawGraph_Java/issues) ，附上你想要画的函数。

##Developed By
Haruue Icymoon <haruue@caoyue.com.cn> <br />
本项目作为 红岩网校 - 移动开发部 的第 1 次作业 的 Level 4 部分。
