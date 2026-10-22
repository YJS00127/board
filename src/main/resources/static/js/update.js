const id = location.pathname.split('/').pop()

// 수정 전 게시글 데이터
function boardContentOutput(id) {

    const title = document.getElementById("title")
    const writer = document.getElementById('writer')
    const content = document.getElementById('content')

    fetch(`/api/board/detail/${id}`)
        .then(response => response.json())
        .then(boardDetail => {
            title.value = boardDetail.title
            writer.value = boardDetail.writer
            content.value = boardDetail.content
            console.log(boardDetail.files)
            showFiles(boardDetail.files)
        })
}

// 게시글 데이터 수정
const updateContent = () => {
    const formData = new FormData();

    const title = document.getElementById("title").value
    const writer = document.getElementById('writer').value
    const content = document.getElementById('content').value
    const files = document.querySelector(".update-file").files
    console.log(files)


    formData.append("title", title);
    formData.append("writer", writer);
    formData.append("content", content);
    formData.append("files", files);


    fetch(`/api/board/`+id, {
        method: "PUT",
        body: JSON.stringify({
            title,
            writer,
            content
        })
    })
        .then(async response => {
            const result = await response.json();
            if(!response.ok){
                showErrorMsg(result)
                return;
            }
            return result;
        })
        .then(result => {
            if(result>0){
                alert('게시글을 수정하였습니다.');
                // location.href=`/board/detail/`+id;
            }
        })
}

function showErrorMsg(errorMessage){
    const titleError = document.getElementById("error-msg-title")
    const writerError = document.getElementById("error-msg-writer")
    const contentError = document.getElementById("error-msg-content")

    titleError.innerHTML = ``;
    writerError.innerHTML = ``;
    contentError.innerHTML = ``;

        if(errorMessage["title"]){
            titleError.textContent = errorMessage["title"];
        }
        if(errorMessage["writer"]){
            writerError.textContent = errorMessage["writer"];
        }
        if(errorMessage["content"]) {
            contentError.textContent = errorMessage["content"];
        }
}
const updateConten = () => {
}
const updateCancel = () => {
    location.href=`/board/detail/`+id;
}

function showFiles(files){
    const fileList = document.querySelector(".update-file-list")
    for(const file of files){
        fileList.innerHTML += `<p class="update-file-name">${file.originalName}</p>`;
    }
}

boardContentOutput(id)