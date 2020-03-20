// JavaScript Document
window.onload = function demo() {
	//找到全选框
	oCheckBoxAll = document.getElementById("checkAll");
	//找到全选框
	oCheck = document.getElementsByClassName("checkCss");
	//定义总共数量以及总金额
	var totalCount = 0;
	var totalMoney = 0;
	//找到显示数量和金额的元素
	var priceTotal = document.getElementById("priceTotal");
	var countTotal = document.getElementById("countTotal");
	//找到数量输入框的值
	var oInputCount = document.getElementsByClassName("inputCountCss");
	//获取表格元素的值
	var otab = document.getElementById("gwcTable");
	var otr = document.getElementsByTagName("tr");
	//加减按钮元素
	var oBtn_jian = document.getElementsByClassName("reduceCss");
	var oBtn_jia = document.getElementsByClassName("addCss");
	//删除
	var oDel = document.getElementsByClassName("a");
	//获取结算按钮id
	var orderBtn = document.getElementById("btnOrder");
	//判断全选按钮是否勾选
	var flag = 0; 
	//单击全选按钮函数
	function Fcheck() {
		if(oCheckBoxAll.checked == true) {
			for(i = 0; i < oCheck.length; i++) {
				if(!oCheck[i].checked) { //判断单选框是否已经选中,不然价格和数量会重复加
					oCheck[i].checked = true;
					flag+=1;
					totalCount += parseInt(oInputCount[i].value);
					totalMoney += parseInt(otr[i + 1].cells[4].innerText);
				}
			}
		} else {
			for(i = 0; i < oCheck.length; i++) {
				oCheck[i].checked = false;
				flag = 0;
			}
			totalCount = 0;
			totalMoney = 0;
		}
		Spantotal();
	}
	//显示总金额和总件数函数
	function Spantotal() {
		countTotal.innerHTML = totalCount;
		priceTotal.innerHTML = totalMoney;
	}
	//单选按钮函数
	function check() {
		for(i = 0; i < oCheck.length; i++) {
			if(this == oCheck[i]) {
				index = i;
				break;
			}
		}
		if(oCheck[index].checked) {
			totalCount += parseInt(oInputCount[index].value);
			totalMoney += parseInt(oInputCount[index].value) * parseInt(otr[index + 1].cells[2].innerText);
			flag+= 1;
		} else {
			totalCount -= parseInt(oInputCount[index].value);
			totalMoney -= parseInt(oInputCount[index].value) * parseInt(otr[index + 1].cells[2].innerText);
			flag-= 1;
		}
		if(flag==oCheck.length){
			oCheckBoxAll.checked=true;
		}
		else{
			oCheckBoxAll.checked=false;
		}
		Spantotal();
	}
	//删除函数
	function shanChu() {
		for(i = 0; i < oDel.length; i++) {
			if(this == oDel[i]) {
				index = i;
				break;
			}
		}
		if(oCheck[index].checked == true) { //点了删除按钮,则总金额和总数量减去该商品的数量和金额
			totalCount -= parseInt(oInputCount[index].value);
			totalMoney -= parseInt(otr[index + 1].cells[4].innerText);
		}
		Spantotal();
		demo();
		otr[index + 1].remove();
	}
	//减按钮功能函数
	function jian() {
		for(i = 0; i < oBtn_jian.length; i++) {
			if(this == oBtn_jian[i]){
				index = i;
				break;
			}
		}
		if(oInputCount[index].value != 0) {
			oInputCount[index].value = oInputCount[index].value - 1;
			otr[index+1].cells[4].innerText = parseInt(oInputCount[index].value) * parseInt(otr[index+1].cells[2].innerText);
			if(oCheck[index].checked && oInputCount[index].value != 0) {
				totalCount -= 1;
				totalMoney -= parseInt(otr[index+1].cells[2].innerText);
				Spantotal();
			}
		}
	}
	//加按钮功能函数
	function jia() {
		for(i = 0; i < oBtn_jian.length; i++) {
			if(this == oBtn_jia[i]){
				index = i;
				break;
			}
		}
		oInputCount[index].value = +(oInputCount[index].value) + 1;
		otr[index+1].cells[4].innerText = parseInt(oInputCount[index].value) * parseInt(otr[index+1].cells[2].innerText);
		if(oCheck[index].checked) {
			totalCount += 1;
			totalMoney += parseInt(otr[index+1].cells[2].innerText);
			Spantotal();
		}
	}
	function jiesuan(){
		if(flag!=0){
			window.location.href="success.html";
		}
		else{
			alert("请至少选择一件商品结算");
		}
	}
	oCheckBoxAll.onclick = Fcheck;			//调用全选函数
	for(i = 0; i < oCheck.length; i++)
		oCheck.item(i).onclick = check; 	//调用单选函数
	for(i = 0; i < oDel.length; i++)	
	oDel.item(i).onclick = shanChu;			//调用删除函数
	for(i = 0; i < oBtn_jian.length; i++)	//调用减
		oBtn_jian.item(i).onclick = jian;
	for(i = 0; i < oBtn_jia.length; i++)	//调用加
		oBtn_jia.item(i).onclick = jia;
	orderBtn.onclick=jiesuan;				//调用结算函数
}