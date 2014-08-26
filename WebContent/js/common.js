zzj=function(){
	this.version = "zzj version 1.0";
};
/**
 * 页面加载完成后执行的方法
 * author:JUM
 */
$(document).ready(function(){
	zzj.loading.colse();
	if($(".body-shadow").length>0)$(".body-shadow").hide();	
});
//窗口的可视高度  
zzj.getWinHeight=function(){
	var windowHeight=document.all ? document.getElementsByTagName("html")[0].offsetHeight : window.innerHeight; 
	return windowHeight;
};
//页面的内容高度  
zzj.getPageHeight=function(){
	var windowHeight=zzj.getWinHeight();
	var pageHeight=Math.max(windowHeight, document.getElementsByTagName("body")[0].scrollHeight); 
	return pageHeight;
};
/**
 * 分页跳转
 * input:skipToPage=去往的页码,pageSize:每页显示条数
 * author:JUM
 * example:zzj.gotoPage(1,1);
 * <form id="contentList" action="contentList.action" method="post">
 *	<input type="hidden" id="pageSize" name="pageSize"/>
 *	<input type="hidden" id="skipToPage" name="skipToPage"/>
 * </form>
 */
zzj.gotoPage=function(skipToPage,pageSize){
	var a=document.getElementById("skipToPage");
	var b=document.getElementById("pageSize");
	a.value=skipToPage;
	b.value=pageSize;
	a.parentNode.submit();
};
/**
 * 通过表单ID进行表单提交
 * input:formId=form表单的ID
 * author:JUM
 * example:zzj.submitForm("contentList"); zzj.submitForm("contentList","contentList.action");
 * <form id="contentList" action="contentList.action" method="post">
 * </form>
 */
zzj.submitForm=function(formId,actionName){
	if(null!=actionName && actionName!='undefined')$("#"+formId).attr('action',actionName);
	zzj.loading.start();
	if($("#"+formId).length>0)$("#"+formId).submit();
};
/**
 * 手动设置页面加载信息开启和关闭
 * author:JUM
 */
zzj.loading={
	/**
	 * 开启页面加载信息
	 * example:zzj.loading.start();
	 */
	start: function(){
		document.body.style.overflow='hidden';
		if($(".body-shadow").length>0)$(".body-shadow").show();	
	},
	/**
	 * 关闭页面加载信息
	 * example:zzj.loading.close();
	 */
	colse: function(){
		document.body.style.overflow='auto';
		if($(".body-shadow").length>0)$(".body-shadow").hide();
	}
};
/**
 * 复选框选择
 */
zzj.checkBox={
	/**
	 * 全选或全不选
	 * input: checkBox=触发方法的复选框对象;childCheckBox=所有需要被操作的子复选框NAME
	 * example:zzj.checkBox.selectAll(this,'contentUniqueNames')
	 */
	selectAll:function(checkBox,childCheckBox){
		var select = document.getElementsByName(childCheckBox);
		if(checkBox.checked==true){
			for(var i=0;i<select.length;i++){
				select[i].checked=true;
			}
		}else{
			for(var i=0;i<select.length;i++){
				select[i].checked=false;
			}
		}
	},
	/**
	 * 树形结构全选或全不选
	 * input: checkBox=触发方法的复选框对象;childCheckBox=所有需要被操作的子复选框NAME
	 * example:zzj.checkBox.selectTreeAll(this,'contentUniqueNames')
	 */
	selectTreeAll:function(checkBox,childCheckBox,select){
		if(!select){
			select = document.getElementsByName(childCheckBox);
		}
		var rid=checkBox.value;
		for(var i=0;i<select.length;i++){
			var pid=select[i].title;
			if(pid==rid){
				if(checkBox.checked==true){
					select[i].checked=true;
				}else{
					select[i].checked=false;
				}
				this.selectTreeAll(select[i],childCheckBox,select);
			}
		}
	},
	/**
	 * 反选
	 * input:childCheckBox=所有需要被操作的子复选框NAME
	 * example:zzj.checkBox.selectInvert('contentUniqueNames')
	 */
	selectInvert:function(childCheckBox){
		var select = document.getElementsByName(childCheckBox);
		for(var i=0;i<select.length;i++){
			if(select[i].checked==true){
				select[i].checked=false;
			}else{
				select[i].checked=true;
			}
		}
	},
	/**
	 *	选择的复选框的个数
	 * input:childCheckBox=所有需要被验证的子复选框NAME
	 * example:zzj.checkBox.selectInvert('contentUniqueNames')
	 */
	selectChecked:function(childCheckBox){
		var select = document.getElementsByName(childCheckBox);
		var k=0;
		for(var i=0;i<select.length;i++){
			if(select[i].checked==true){
				k++;
			}
		}
		return k;
	}	
};
/**
 * 字符处理
 * author:JUM
 */
zzj.CH={
	/**
	 * 删除左右两边的空格
	 * input: text=需要处理的文本
	 * example:zzj.CH.trim(" 呵 呵 ");
	 * return newtext="呵 呵";
	 */
	trim: function(text){
		var newtext = text.replace(/(^\s*)|(\s*$)/g, "");
	    return newtext;
	},
	/**
	 * 删除左边的空格
	 * input: text=需要处理的文本
	 * example:zzj.CH.trim(" 呵 呵 ");
	 * return newtext="呵 呵 ";
	 */
	ltrim: function(text){
		var newtext=text.replace(/(^\s*)/g,"");
	    return newtext;
	},
	/**
	 * 删除右边的空格
	 * input: text=需要处理的文本
	 * example:zzj.CH.trim(" 呵 呵 ");
	 * return newtext=" 呵 呵";
	 */
	rtrim: function(text){
		var newtext=text.replace(/(\s*$)/g,"");
		return newtext;
	},
	/**
	 * 判断是否为数字
	 * input: text=需要判断的文本
	 * example:zzj.CH.isNumber(123);
	 * return ture:是数字;false:不是数字
	 */	
	isNumber: function(text){
		var reg1 =/^\d+$/;
		if(text.match(reg1) == null) {
			return false;
		}else{
			return true;
		}
	},
	/**
	 * JS自定义 replaceAll 方法
	 * input: text=需要处理的文本,search=需要转换的字符,replace=转换后的字符
	 * example:zzj.CH.replaceAll("1 1 1","1","ss");
	 * return newtext="ss ss ss"
	 */
	replaceAll: function(text,search,replace){ 
		var regex = new RegExp(search,"g");
		var newtext=text.replace(regex,replace);
 		return newtext;  
	},
	/**
	 * 将数字转换成英文大写字母
	 * input: id=需要转换的数字
	 * example: zzj.CH.intToLetter(1);
	 * return str="A";
	 */
	intToLetter:function(id){
	    var k = (--id)%26;
	    var str = "";
	    while(Math.floor((id=id/26))!=0){
	        str = String.fromCharCode(k+65)+str;
	        k=(--id)%26;
	    }
	    str = String.fromCharCode(k+65)+str;
	    return str;
	}
};