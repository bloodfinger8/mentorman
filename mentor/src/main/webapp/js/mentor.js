//멘토 지원하기
$('#mentorapplyForm_btn').on('click',function(){
	$('#mentor_company_error').empty();
	$('#mentor_department_error').empty();
	$('#mentor_position_error').empty();

	if($('#mentor_company').val()==''){
		$('#mentor_company_error').text('회사명에 내용을 입력해 주세요').css('color', 'red');
		$('#mentor_company_error').css('font-size','8pt');
		$('#mentor_company').focus();
	}else if($('#mentor_department').val()==''){
		$('#mentor_department_error').text('부서에 내용을 입력해 주세요').css('color', 'red');
		$('#mentor_department_error').css('font-size','8pt');
		$('#mentor_department').focus();
	}else if($('#mentor_position').val()==''){
		$('#mentor_position_error').text('직급에 내용을 입력해 주세요').css('color', 'red');
		$('#mentor_position_error').css('font-size','8pt');
		$('#mentor_position').focus();
	}else {
		if($('#email').val()==''){
			$('#new_mentor_request').submit();
		}else {
			alert('이미 지원한 계정입니다. \n관리자의 승인을 기다려주세요');
			location.href='/mentor/main/index';
		}
	}
});





//지원하기
$('#mentorapply_btn').on('click',function(){
	$('#mentor_name_error').empty();
	$('#mentor_company_error').empty();
	$('#mentor_department_error').empty();
	$('#mentor_position_error').empty();
	$('#mentor_job_code_error').empty();
	$('#mentor_career_error').empty();
	$('#mentoring_code_error').empty();
	$('#mentor_represent_error').empty();
	$('#mentor_info_error').empty();
	$('#mentor_email_error').empty();
	$('#mentor_businesscard_error').empty();


	if($('#mentor_name').val()==''){
		$('#mentor_name_error').text('이름을 입력해주세요').css('color', 'red');
		$('#mentor_name_error').css('font-size','8pt');
		$('#mentor_name').focus();
	}else if($('#mentor_company').val()==''){
		$('#mentor_company_error').text('회사명을 입력해 주세요').css('color', 'red');
		$('#mentor_company_error').css('font-size','8pt');
		$('#mentor_company').focus();
	}else if($('#mentor_department').val()==''){
		$('#mentor_department_error').text('부서를 입력해 주세요').css('color', 'red');
		$('#mentor_department_error').css('font-size','8pt');
		$('#mentor_department').focus();
	}else if($('#mentor_position').val()==''){
		$('#mentor_position_error').text('직급를 입력해 주세요').css('color', 'red');
		$('#mentor_position_error').css('font-size','8pt');
		$('#mentor_position').focus();
	}else if($('#job_code').val()=='0'){
		$('#mentor_job_code_error').text('직무 유형을 선택해주세요').css('color', 'red');
		$('#mentor_job_code_error').css('font-size','8pt');
		$('#job_code').focus();
	}else if($('#mentor_career').val()==''){
		$('#mentor_career_error').text('주요 경력을 입력해주세요').css('color', 'red');
		$('#mentor_career_error').css('font-size','8pt');
		$('#mentor_career').focus();
	}else if($('.mentoring_code').is(':checked')==false){
		$('#mentoring_code_error').text('멘토링 가능 분야를 선택해주세요').css('color', 'red');
		$('#mentoring_code_error').css('font-size','8pt');
		$('#mentor_represent').focus();
	}else if($('#mentor_represent').val()==''){
		$('#mentor_represent_error').text('대표 멘토링 분야를 입력해주세요').css('color', 'red');
		$('#mentor_represent_error').css('font-size','8pt');
		$('#mentor_represent').focus();
	}else if($('#mentor_info').val()==''){
		$('#mentor_info_error').text('멘토 소개를 입력해주세요').css('color', 'red');
		$('#mentor_info_error').css('font-size','8pt');
		$('#mentor_info').focus();
	}else if($('#mentor_email').val()==''){
		$('#mentor_email_error').text('이메일을 입력해주세요').css('color', 'red');
		$('#mentor_email_error').css('font-size','8pt');
		$('#mentor_email').focus();
	}else if($('#mentor_request_name_card').val()==''){
		$('#mentor_businesscard_error').text('명함 이미지를 등록해주세요').css('color', 'red');
		$('#mentor_businesscard_error').css('font-size','8pt');
		$('#mentor_request_name_card').focus();
	}else {
		$('#mentorapplyWriteForm').submit();
	}
});

//지원하기에서 뒤로가기
$('#mentorapply_backBtn').on('click',function(){
	location.href="/mentor/mentor/mentorapplyForm";
});

//멘토에게 질문하기 임시저장 버튼
$('#save_btn').on('click', function(){
	$('#question_title_error').empty();
	$('#question_content_error').empty();

	if($('#question_title').val()==''){
		$('#question_title_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_title_error').css('font-size','8pt');
		$('#question_title').focus();
	}else if($('#question_content').val()==''){
		$('#question_content_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_content_error').css('font-size','8pt');
		$('#question_content').focus();
	}else{
		$('#question_form').submit();
	}

});


function mentor_question_seq(seq, pg){
	$.ajax({
		type: 'post',
		url: '/mentor/mentor/question_flag',
		data: {'seq': seq,'pg': pg},
		dataType: 'text',
		success: function(data){
			location.href=data;
		},
		error: function(){
			alert('에러');
		}
	});
}


$('#modify_btn').on('click', function(){
	$('#question_title_error').empty();
	$('#question_content_error').empty();
	var content = $('#question_content').val();
	if($('#question_title').val()==''){
		$('#question_title_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_title_error').css('font-size','8pt');
		$('#question_title').focus();
	}else if($('#question_content').val()==''){
		$('#question_content_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_content_error').css('font-size','8pt');
		$('#question_content').focus();
	}else if(content.length > 2000){
		var toastTop = app.toast.create({
	           text: '2000자 이내의 내용을 입력해주세요',
	           position: 'top',
	           closeButton: true,
	    });
	    toastTop.open();
	}else{
		$.ajax({
			type: 'post',
			url: '/mentor/mentor/questionModify',
			data: $('#question_form').serialize(),
			dataType: 'text',
			success: function(data){
				if(data=='success'){
					location.href='/mentor/member/myQandA?pg=1';
				}
			},
			error: function(){
				alert('에러');
			}
		});
	}
});


// 팔로우 기능- 재우
$(function(){
	var seq = $('#mentor_seq').val();

	//내가 팔로우한 멘토인지 확인
	if($('#followVal').val() === '1'){
		$('#followA').addClass('button-fill');
	}else{
		$('#followA').removeClass('button-fill');
	}


	$('.mentor_'+seq).on('click' , function(){
		var followBtn = $(this);

		var sendData = {
				'followed_email' : $('#followed_email').val(),
				'follow' : $('#followVal').val()
			};

		$.ajax({
			url : '/mentor/mentor/mentorFollow',
			type : 'POST',
			data : sendData,
			success : function(data) {

				if (data == 1) {
					followBtn.addClass('button-fill');
					var toastIcon = app.toast.create({
						  text: '관심멘토에 등록 되었습니다',
						  position: 'center',
						  closeTimeout: 2000,
						});
					toastIcon.open();

					//socket에 보내자
					if(socket) {
						let socketMsg = "follow," + $('#memNicname').val() +","+$('#member_nickname').val() +","+$('#mentor_seq').val()
						console.log("msgmsg :: " + socketMsg );
						socket.send(socketMsg);
					}

				}else{
					followBtn.removeClass('button-fill');
					var toastIcon = app.toast.create({
						  text: '관심멘토에서 삭제 되었습니다',
						  position: 'center',
						  closeTimeout: 2000,
						});
					toastIcon.open();
				}
				$('#followVal').val(data);
			},
			error : function(err){
				console.log("err");
			}

		});
	});
});

var mentoring = new array();
$(document).ready(function(event){
	$('#mentring_row.a').on('click', function(){
		event.preventDefault();	
		
		var txt = $(this).attr("href");
		
		alert(txt);
	});
});
