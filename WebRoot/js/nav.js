var obj=null;	//定义一个对象用于存储a标签对应的对象。
var obj2=null;	//定义一个对象用于存储li标签对应的对象。
var Ls=document.getElementById('navmain').getElementsByTagName('li');
//第三行代码用于获取id为foo下的标签名称为li的组件的数组，并记录
var As=document.getElementById('navmain').getElementsByTagName("a");
//第五行代码用于获取a标签数组，用于做地址栏的地址比对。
obj = As[0];
obj2 = Ls[0];//默认为第一个，及下标为0
for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
{	obj=As[i];//存取目前的a标签
	obj2=Ls[i];}//存取目前的li标签
}

obj2.className='active'