const id = location.pathname.split("/").pop()

// 상세 게시글 출력
function boardDetailOutput() {
    const title = document.querySelector(".detail-title")
    const writer = document.querySelector(".detail-writer")
    const content = document.querySelector(".detail-content")
    const createdAt = document.querySelector(".detail-created-at")
    const viewCount = document.querySelector(".detail-view-count")

    fetch(`/api/board/detail/${id}`)
        .then(response => {
            if(!response.ok){
                alert("등록되지 않거나 삭제된 게시글입니다.")
                location.href="/board";
                return;
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            title.textContent = data.title;
            writer.textContent = data.writer;
            content.textContent = data.content;
            viewCount.textContent = data.viewCount;
            createdAt.textContent = data.createdAt.slice(0,16);
            showFiles(data.files);
        })
}

// 수정, 삭제 전 게시글 비밀번호 확인
function pwChk(type){
    const pwInput = prompt("비밀번호를 입력하세요");
    if(pwInput === ''){
        alert("비밀번호를 입력해주세요.")
        return;
    } else if(pwInput === null){
        return;
    }

    if(type === 'delete'){
        fetch(`/api/board/`+id, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            },
            body: pwInput
        })
            .then(response => response.json())
            .then(result => {
                if(result>0){
                    alert("게시글이 삭제되었습니다.");
                    location.href = "/board"
                } else{
                    alert("비밀번호가 틀렸습니다.");
                }
            })
    } else if(type === 'updatePwChk'){
        fetch(`/api/board/pw-chk/`+id, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: pwInput,
        })
            .then(response => response.json())
            .then(result => {
                console.log(result)
                if(result){
                    alert("비밀번호가 일치합니다. 수정 화면으로 이동합니다.")
                    location.href = `/board/update/` + id
                } else {
                    alert("비밀번호가 틀렸습니다.")
                }
            })
    }
}

function showFiles(files){
    const fileList = document.querySelector(".detail-file-list")
    for(const file of files){
        fileList.innerHTML += `<p class="detail-file-name">${file.originalName}</p>`;
    }
}
boardDetailOutput()