function pwChk(type, id){
    const pwInput = prompt('비밀번호를 입력해주세요.', '');

    if(pwInput === null) return;

    // if(type === 'delete') {
    //     fetch(`/board/${id}?pw=${encodeURIComponent(pwInput)}`, {
    //         method: "DELETE"
    //     })
    //         .then(response => response.json())
    //         .then(result => {
    //             if (result) {
    //                 alert("게시글이 삭제되었습니다.")
    //                 location.href = "/board";
    //             } else {
    //                 alert("비밀번호가 틀렸습니다.")
    //             }
    //         });
    // }
    if(type === 'delete'){
        fetch(`/api/board/${id}`, {
            method: 'DELETE',
            body: JSON.stringify({
                pw: pwInput
            }),
            headers: {'Content-Type' : 'application/json'}
        })
            .then(response => response.json())
            .then(data => {

            })
    }
    else if(type === 'updatePwChk'){
        fetch(`/board/${id}/pw-chk?pw=${encodeURIComponent(pwInput)}`, {
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


        // Controller도 json으로 바꿔야함

        // if(type === 'delete'){
        //     fetch(`/board/${id}`, {
        //         method: "DELETE",
        //         body: JSON.stringify({
        //             pw: pwInput
        //         }),
        //     })
        //         .then(response => response.json())
        //         .then(result => {
        //             if(result) {
        //                 alert("게시글이 삭제되었습니다.")
        //                 location.href = "/board";
        //             } else{
        //                 alert("비밀번호가 틀렸습니다. 다시 시도해주세요.")
        //             }
        //         });
        // }
}