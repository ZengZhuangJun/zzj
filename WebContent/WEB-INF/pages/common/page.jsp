<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
input.text {
	border: 1px solid #ccc;
}
.pageCommentBar {
	width: 100%;
	font-size: 12px;
	font-family: simsun;
	/*border-top: 1px solid #dcd;*/
}

.pageCommentBar .pageInfo {
	float: left;
}
.pageCommentBar .pageCtl {
	float: right;
	margin-top: 3px;
}

.blueBtn {
	background: #0671d9;
	border: 0;
	cursor: pointer;
	color: #fff;
	height: 20px;
	font-size: 12px;
	line-height: 1.5;
}
.pageCommentBar .pageCtl a:hover {
 	background: #c5ddf4;
}
.pageCommentBar .pageCtl span.pageCurrent {
 	color: #3572ca;
 	background: #c5ddf4;
}
.pageCommentBar .pageCtl .prev img {
	border: 0;
	margin-right: 5px;
	vertical-align: middle;
}
.pageCommentBar .pageCtl .next img {
	border: 0;
	margin-left: 5px;
	vertical-align: middle;
}
.pageCommentBar .pageCtl a, .pageCommentBar .pageCtl span {
	display: inline;
	background: #ebebeb;
	padding: 4px 7px;
	border: 0px #c0bebe solid;
	border-width: 0 0 3px 0;
	color: #111;
	text-decoration: none;
}
</style>
<script>
$(document).ready(function(){
	var a='<s:property value="pageSize" />';
	$("#goPageSize").val(a);	
});
function gotoPage(pageSize){
	var a=$("#gopage").val();
	var b=<s:property value="pageBean.pageCount"/>;
	if(!wcmAppCommon.CH.isNumber(a)){
		alert("跳转的页码必须为数字");
		$("#gopage").val("");
		return;
	}
	(a>b)?a=b:(a<1)?a=1:a;
	wcmAppCommon.gotoPage(a,pageSize);
}
</script>
<s:if test="resultSize>0">
<div class="pageCommentBar">
	<div class="pageInfo">
		<span>共<s:property value="resultSize" />条记录，
		每页
		<select class="text" name="goPageSize" id="goPageSize" onchange="wcmAppCommon.gotoPage(1,this.value);">
			<option value="1">1</option>
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
			<option value="25">25</option>
			<option value="30">30</option>
			<option value="35">35</option>
			<option value="40">40</option>
			<option value="45">45</option>
			<option value="50">50</option>
			<option value="100">100</option>
			<option value="200">200</option>
			<option value="500">500</option>
			<option value="1000">1000</option>
		</select>
		条，
		<s:property value="skipToPage" />/<s:property value="pageBean.pageCount"/></span>
		<span><input class="text" id="gopage" type="text" size="1">
		<input class="blueBtn" type="button" value="GO!" onclick="gotoPage(<s:property value="pageSize" />)"/></span>
	</div>
	<div class="pageCtl">
		<s:if test="pageBean.hasPrePage==1">
			<a href="javascript:wcmAppCommon.gotoPage(1,<s:property value="pageSize" />);">首页</a>
			<a class="prev" href="javascript:wcmAppCommon.gotoPage(<s:property value="skipToPage-1" />,<s:property value="pageSize" />);"><img src="/wcmApp/images/common/prev.gif">上一页</a>
		</s:if>
		<s:else>
			<span  class='pageBack' >首页</span>
			<span  class='pageBack prev' ><img src="/wcmApp/images/common/prev.gif">上一页</span>
		</s:else>
		<s:iterator value="pageBean.pageList" id="list" status="st">
			<s:if test="#list == skipToPage">
				<span class="pageCurrent"><s:property value="#list"/></span>
			</s:if>
			<s:elseif test="#list == '...'">
				<span class="">...</span>
			</s:elseif>
			<s:else>
				<a href="javascript:wcmAppCommon.gotoPage(<s:property value="#list"/>,<s:property value="pageSize" />);"><s:property value="#list"/></a>
			</s:else>
		</s:iterator>
	
		<s:if test="pageBean.hasNextPage==1">
			<a class="next" href="javascript: wcmAppCommon.gotoPage(<s:property value="skipToPage+1" />,<s:property value="pageSize" />);">下一页<img src="/wcmApp/images/common/next.gif"></a>
			<a href="javascript: wcmAppCommon.gotoPage(<s:property value="pageBean.pageCount"/>,<s:property value="pageSize" />);">末页</a>
		</s:if>
		<s:else>
			<span  class='pageBack next' >下一页<img src="/wcmApp/images/common/next.gif"></span>
			<span  class='pageBack' >末页</span>
		</s:else>
		</div>
	</div>
</s:if>