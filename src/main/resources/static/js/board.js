function pwChk(type, id){
    const pwInput = prompt('비밀번호를 입력해주세요.', '');

    if(pwInput === null) return;

    if(type === 'delete') {
        fetch(`/board/${id}?pw=${encodeURIComponent(pwInput)}`, {
            method: "DELETE"
        })
            .then(response => response.json())
            .then(result => {
                if (result) {
                    alert("게시글이 삭제되었습니다.")
                    location.href = "/board";
                } else {
                    alert("비밀번호가 틀렸습니다.")
                }
            });
    } else if(type === 'updatePwChk'){
        fetch(`/board/${id}/check-pw?pw=${encodeURIComponent(pwInput)}`, {
            method: "POST"
        })
        .then(response => response.json())
        .then(result => {
            if(result) {
                alert("비밀번호가 일치합니다. 수정 화면으로 이동합니다.")
                location.href = `/board/update/${id}`
            } else {
                alert("비밀번호가 틀렸습니다.")
            }
        })
    }

}