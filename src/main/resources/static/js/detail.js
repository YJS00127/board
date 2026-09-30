// 상세 게시글 출력
function boardDetailOutput(id){
    const title = document.getElementById("title")
    const writer = document.getElementById("writer")
    const content = document.getElementById("content")
    const createdAt = document.getElementById("createdAt")

    fetch(`/api/board/detail/${id}`)
        .then(response => {
            if(!response.ok){
                alert("등록되지 않거나 삭제된 게시글입니다.")
                location.href="/board"
            }
        return response.json()})
        .then(boardDetail => {
            title.textContent = boardDetail.title
            writer.textContent = boardDetail.writer
            content.textContent = boardDetail.content
            createdAt.textContent = boardDetail.createdAt.slice(0,16)
        })
}


// 수정, 삭제 전 게시글 비밀번호 확인
function pwChk(type){
    const pwInput = prompt("비밀번호를 입력하세요");
    if(pwInput===null) return;

    if(type === 'delete'){
        fetch(`/api/board/`+id, {
            method: "DELETE",
            body: JSON.stringify({
                pw: pwInput
            }),
            headers: {
                'Content-Type': 'application/json'}
        })
            .then(response => response.json())
            .then(result => {
                if(result>0){
                    alert("게시글이 삭제되었습니다.")
                    location.href = "/board"
                } else{
                    alert("비밀번호가 틀렸습니다.")
                }
            })
    } else if(type === 'updatePwChk'){
        fetch(`/api/board/pw-chk/`+id, {
            method: "POST",
            body: JSON.stringify({
                pw: pwInput
            }),
            headers: {
               'Content-Type' : 'application/json'
            }
        })
            .then(response => response.json())
            .then(result => {
                if(result){
                    alert("비밀번호가 일치합니다. 수정 화면으로 이동합니다.")
                    location.href = `/board/update/` + id
                } else{
                    alert("비밀번호가 틀렸습니다.")
                }
            })
    }
}

let id = location.pathname.split("/").pop();
boardDetailOutput(id);