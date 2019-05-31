/**
 * ajax라 멀티쓰레드처럼 순서대로 안나올수도 있음
 * 처리방법
 * 1.순서를 정해주면됨
 * 2. .done메서드 다 한후에 그 안쪽에서 ajax문 코딩하면 그이후에 나옴
 */

console.log("Reply Module........")

var replyService = (function() {
	// 댓글 추가 기능
	function add(reply, callback, err) {
		console.log("add reply..........")
		
		$.post({
			url : "/replies/new",
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8"
		})
		.done(function(data) {
			if (callback) {
				callback(data);
			}
		})
		.fail(function(xhr, status, er) {
			if (er) {
				error(er);
			}
		})
	}
	
	// 댓글 목록 기능
	function getList(param, callback, error) {
		console.log("getList reply........")
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/" + bno + "/" + page)
		.done(function(data) {
			if (callback) {
				callback(data.replyCnt, data.list);
			}
		})
		.fail(function(xhr, status, er) {
			if (error) {
				error(er);
			}
		})
	}
	
	// 댓글 수정 기능
	function modify(reply, callback, err) {
		console.log("modify reply..........")
		
		$.ajax({
			url : "/replies/" + reply.rno,
			data : JSON.stringify(reply),
			type : "put",
			contentType : "application/json; charset=utf-8"
		})
		.done(function(data) {
			if (callback) {
				callback(data);
			}
		})
		.fail(function(xhr, status, er) {
			if (er) {
				error(er);
			}
		})
	}
	
	// 댓글 삭제 기능
	function remove(rno, replyer, callback, error) {
		console.log("remove reply........")
		
		$.ajax("/replies/" + rno, {
			type : 'delete',
			data : JSON.stringify({rno:rno ,replyer:replyer}),
			contentType : "application/json; charset=utf-8"
		})
		.done(function(data) {
			if (callback) {
				callback(data);
			}
		})
		.fail(function(xhr, status, er) {
			if (error) {
				error(er);
			}
		})
	}

	// 댓글 상세조회 기능
	function get(rno, callback, error) {
		console.log("get reply........")
		
		$.ajax("/replies/" + rno, {
			type : 'get',
			dataType : 'json'
		})
		.done(function(data) {
			if (callback) {
				callback(data);
			}
		})
		.fail(function(xhr, status, er) {
			if (error) {
				error(er);
			}
		})
	}
	
	function displayTime(timeValue) {
		var today = new Date();
		
		var gap = today.getTime() - timeValue; // 현재시간과 파라미터의 값의 차이 (단위:ms)
		
		var dateObj = new Date(timeValue);
		var str = "";
		
		if (gap < 1000 * 60 * 60 * 24) { // 작성일과 현재시간의 차이가 1일 이내
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return leadZero(hh) + ":" + leadZero(mi) + ":" + leadZero(ss);
		}
		else { // 작성일과 현재시간의 차이가 1일 이상
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return yy+ "/" + leadZero(mm) + "/" + leadZero(dd);
		}
	}
	
	function leadZero(number) {
		return number > 9 ? number : "0" + number;
	}
	
	return {add : add, getList : getList, modify : modify, remove : remove, get : get, displayTime : displayTime}
}());
